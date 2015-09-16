package de.xancake.io.db.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import de.xancake.io.db.sql.config.DBConfiguration_I;

/**
 * Ein ConnectionPool verwaltet Datenbankverbindungen an einer zentralen Stelle und stellt sie einheitlich zur Verfügung.
 * Dabei können nach Bedarf weitere Datenbankverbindungen geöffnet werden, wenn keine verfügbar ist und die Kapazität
 * ausreicht. Verbindungen müssen nach der Benutzung wieder dem Pool freigegeben werden.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class ConnectionPool {
	private DBConfiguration_I myDatabaseConfiguration;
	private int myMaxConnections;
	private Queue<Connection> myAvailableConnections;
	private Set<Connection> myInUseConnections;
	private boolean isAutoCommit;
	
	/**
	 * Initialisiert einen neuen ConnectionPool mit den Verbindungsdaten für eine Datenbank.
	 * Die Connections werden mit auto-commit erzeugt und das Limit für den ConnectionPool
	 * liegt bei {@link Integer#MAX_VALUE}.
	 * @param configuration Die Datenbankkonfiguration
	 */
	public ConnectionPool(DBConfiguration_I configuration) {
		this(configuration, true, Integer.MAX_VALUE);
	}
	
	/**
	 * Initialisiert einen neuen ConnectionPool mit den Verbindungsdaten für eine Datenbank.
	 * Das Limit für den ConnectionPool liegt bei {@link Integer#MAX_VALUE}.
	 * @param configuration Die Datenbankkonfiguration
	 * @param autoCommit Ob die erzeugten Connections mit {@link Connection#setAutoCommit(boolean) auto-commit}
	 *        erzeugt werden sollen oder nicht
	 */
	public ConnectionPool(DBConfiguration_I configuration, boolean autoCommit) {
		this(configuration, autoCommit, Integer.MAX_VALUE);
	}
	
	/**
	 * Initialisiert einen neuen ConnectionPool mit den Verbindungsdaten für eine Datenbank.
	 * Die Connections werden mit auto-commit erzeugt.
	 * @param configuration Die Datenbankkonfiguration
	 * @param max Die maximal mögliche Anzahl an Verbindungen die der ConnectionPool gleichzeitig verwalten darf
	 */
	public ConnectionPool(DBConfiguration_I configuration, int max) {
		this(configuration, true, max);
	}
	
	/**
	 * Initialisiert einen neuen ConnectionPool mit den Verbindungsdaten für eine Datenbank und einem Limit für zu
	 * erzeugende Datenbankverbindungen. Zusätzlich kann angegeben werden, ob die erzeugten Connections auto-commit
	 * auf der Datenbank durchführen sollen, oder nicht.
	 * @param configuration Die Datenbankkonfiguration
	 * @param autoCommit Ob die erzeugten Connections mit {@link Connection#setAutoCommit(boolean) auto-commit}
	 *        erzeugt werden sollen oder nicht
	 * @param max Die maximal mögliche Anzahl an Verbindungen die der ConnectionPool gleichzeitig verwalten darf
	 */
	public ConnectionPool(DBConfiguration_I configuration, boolean autoCommit, int max) {
		if(max < 1) {
			throw new IllegalArgumentException("The pool size has to be greater than 0!");
		}
		myDatabaseConfiguration = Objects.requireNonNull(configuration, "Die Datenbankkonfiguration darf nicht leer sein!");
		myMaxConnections = max;
		myAvailableConnections = new LinkedList<>();
		myInUseConnections = new HashSet<>();
		isAutoCommit = autoCommit;
	}
	
	/**
	 * Gibt eine Connection aus dem Pool zurück und sorgt dafür, dass die selbe Connection von keinem anderen
	 * Aufruf ermittelt werden kann, bis die Connection {@link #release(Connection) wieder freigegeben} wurde.
	 * Wenn der Pool über keine verfügbare Connection mehr verfügt, wird eine neue angelegt und zurückgegeben,
	 * insofern die {@link #getConnectionsMax() Kapazität} nicht erreicht ist. Wenn die Kapazität erreicht
	 * ist, blockiert der Aufruf bis ein anderer Thread eine Connection freigegeben hat.
	 * @return Eine Connection aus dem Pool
	 * @throws SQLException Wenn ein Fehler beim Erzeugen eine Connection aufgetreten ist
	 * @throws InterruptedException Wenn der aufrufende Thread interruted wurde, während er auf die Freigabe einer Connection wartet
	 */
	public synchronized Connection acquire() throws SQLException, InterruptedException {
		Connection con = null;
		
		if(myAvailableConnections.size() > 0) {
			con = myAvailableConnections.poll();
		} else {
			if(myAvailableConnections.size() + myInUseConnections.size() < myMaxConnections) {
				con = DriverManager.getConnection(myDatabaseConfiguration.getHost(), myDatabaseConfiguration.getUser(), myDatabaseConfiguration.getPassword());
				con.setAutoCommit(isAutoCommit);
			} else if(myAvailableConnections.size() + myInUseConnections.size() == myMaxConnections) {
				while(myAvailableConnections.size() == 0) {
					wait();
				}
				con = myAvailableConnections.poll();
			}
		}
		
		myInUseConnections.add(con);
		return con;
	}
	
	/**
	 * Gibt die übergebene Connection wieder frei und fügt sie den verfügbaren Connections wieder hinzu.
	 * Wenn die Connection geschlossen wurde, wird sie aus den 'in Benutzung'-markierten Connections
	 * ausgetragen, aber nicht wieder den verfügbaren Connections hinzugefügt.
	 * Wenn die Connection nicht als 'in Benutzung' in diesem Pool markiert wurde, passiert nichts.
	 * @param connection Die freizugebende Connection
	 * @throws SQLException Falls ein Fehler bei der Abfrage auftritt, ob die Connection geschlossen ist
	 */
	public synchronized void release(Connection connection) throws SQLException {
		if(myInUseConnections.remove(connection)) {
			if(!connection.isClosed()) {
				// Wenn die Connection vorher in Benutzung war und nicht geschlossen wurde wieder dem Pool hinzufügen
				myAvailableConnections.offer(connection);
				notifyAll();
			}
		}
	}
	
	/**
	 * Gibt die Anzahl der zur Verfügung stehenden Connections zurück.
	 * @return Die Anzahl der zur Verfügung stehenden Connections
	 */
	public int getConnectionsAvailableCount() {
		return myAvailableConnections.size();
	}
	
	/**
	 * Gibt die Anzahl der sich in Benutzung befindlichen Connections zurück.
	 * @return Die Anzahl der sich in Benutzung befindlichen Connections
	 */
	public int getConnectionsInUseCount() {
		return myInUseConnections.size();
	}
	
	/**
	 * Gibt die Anzahl aller derzeit von diesem ConnectionPool verwalteten Connections zurück.
	 * @return Die Anzahl aller Connections
	 * @see #getConnectionsAvailableCount()
	 * @see #getConnectionsInUseCount()
	 */
	public int getConnectionsCount() {
		return getConnectionsAvailableCount() + getConnectionsInUseCount();
	}
	
	/**
	 * Gibt die Anzahl der maximal erlaubten Connections für diesen ConnectionPool zurück.
	 * @return Die Anzahl der maximal erlaubten Connections
	 */
	public int getConnectionsMax() {
		return myMaxConnections;
	}
	
	/**
	 * Gibt zurück, ob dieser ConnectionPool für auto-commit konfiguriert wurde.
	 * Das heißt, dass die Connections, die der Pool erzeugt für {@link Connection#setAutoCommit(boolean) auto-commit}
	 * konfiguriert sind. Deshalb muss darauf geachtet werden, manuell zu {@link Connection#commit() committen}
	 * oder {@link Connection#rollback() zurück zu rollen}, wenn eine Connection manuell mittels {@link #acquire()}
	 * ermittelt wird. Die Hilfsmethoden {@link #executeStatement(String, ResultSetCallback)} und
	 * {@link #executePreparedStatement(String, PreparedStatementCallback, ResultSetCallback)} erledigen dies selbstständig.
	 * @return {@code true}, wenn der ConnectionPool für auto-commit konfiguriert wurde, ansonsten {@code false}
	 */
	public boolean isAutoCommit() {
		return isAutoCommit;
	}
	
	/**
	 * Schließt alle verfügbaren Connections dieses ConnectionPools und trägt sie aus.
	 * Connections die noch in Benutzung sind, werden nur ausgetragen aber nicht geschlossen,
	 * darum müssen sich die aktuellen Inhaber kümmern.
	 * <p>Sollen auch die noch verwendeten Verbindungen 'gewalttätig' geschlossen werden,
	 * empfiehlt sich {@link #closeConnectionsInUse()}.
	 * @throws SQLException Falls ein Fehler beim Schließen der Connections auftritt
	 * @see #closeConnectionsInUse()
	 */
	public synchronized void close() throws SQLException {
		while(!myAvailableConnections.isEmpty()) {
			myAvailableConnections.poll().close();
		}
		myInUseConnections.clear();
	}
	
	/**
	 * Schließt alle in Benutzung stehenden Connections dieses ConnectionPools und trägt sie aus.
	 * Diese Methode sollte nicht leichtfertig aufgerufen werden, da das Schließen von Connections
	 * die gerade in Benutzung sind sehr wahrscheinlich zu Abbrüchen/Exceptions bei den aktuellen
	 * Inhabern führen wird.
	 * <p>Zum Schließen der nicht verwendeten (verfügbaren) Connections des Pools empfiehlt sich
	 * {@link #close()}. 
	 * @throws SQLException Falls ein Fehler beim Schließen der Connections auftritt
	 * @see #close()
	 */
	public synchronized void closeConnectionsInUse() throws SQLException {
		for(Connection connection : myInUseConnections) {
			connection.close();
		}
		myInUseConnections.clear();
	}
	
	/**
	 * Hilfsmethode zum Ausführen eines {@link PreparedStatement}s. Die Methode kümmert sich eigenständig um das
	 * {@link #acquire() Ermitteln} und {@link #release(Connection) Freigeben} einer Connection und bietet
	 * Einhängepunkte für die Konfiguration der Parameter des PreparedStatements sowie dem Ermitteln
	 * von Daten aus dem {@link ResultSet} an.
	 * <p>Das erzeugte {@link PreparedStatement} und das {@link ResultSet} werden ebenfalls automatisch geschlossen
	 * @param sql Der SQL-Befehl der ausgeführt werden soll
	 * @param statementCallback Das Callback für das {@link PreparedStatement} zur Konfiguration
	 * @param resultSetCallback Das Callback zum Auslesen des {@link ResultSet}s
	 * @return Das Rückgabeobjekt des {@link ResultSetCallback}s
	 * @throws SQLException Wenn ein Fehler beim Ausführen des SQL-Statements auftritt
	 * @throws InterruptedException Wenn der aufrufende Thread beim {@link #acquire() Warten auf eine Verbindung} interrupted wird
	 * @see PreparedStatementCallback
	 * @see ResultSetCallback
	 */
	public <T> T executePreparedStatement(String sql, PreparedStatementCallback statementCallback, ResultSetCallback<T> resultSetCallback) throws SQLException, InterruptedException {
		Connection con = acquire();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			statementCallback.callback(stmt);
			try(ResultSet rs = stmt.executeQuery()) {
				T value = null;
				if(resultSetCallback != null) {
					value = resultSetCallback.callback(rs);
				}
				if(!isAutoCommit()) {
					con.commit();
				}
				return value;
			}
		} catch(SQLException e) {
			if(!isAutoCommit()) {
				con.rollback();
			}
			throw e;
		} finally {
			release(con);
		}
	}
	
	/**
	 * Hilfsmethode zum Ausführen eines {@link Statement}s. Die Methode kümmert sich eigenständig um das
	 * {@link #acquire() Ermitteln} und {@link #release(Connection) Freigeben} einer Connection und bietet
	 * Einhängepunkte für das Ermitteln von Daten aus dem {@link ResultSet} an.
	 * <p>Das erzeugte {@link Statement} und das {@link ResultSet} werden ebenfalls automatisch geschlossen
	 * @param sql Der SQL-Befehl der ausgeführt werden soll
	 * @param resultSetCallback Das Callback zum Auslesen des {@link ResultSet}s
	 * @return Das Rückgabeobjekt des {@link ResultSetCallback}s
	 * @throws SQLException Wenn ein Fehler beim Ausführen des SQL-Statements auftritt
	 * @throws InterruptedException Wenn der aufrufende Thread beim {@link #acquire() Warten auf eine Verbindung} interrupted wird
	 * @see ResultSetCallback
	 */
	public <T> T executeStatement(String sql, ResultSetCallback<T> resultSetCallback) throws SQLException, InterruptedException {
		Connection con = acquire();
		try(Statement stmt = con.createStatement()) {
			try(ResultSet rs = stmt.executeQuery(sql)) {
				T value = null;
				if(resultSetCallback != null) {
					value = resultSetCallback.callback(rs);
				}
				if(!isAutoCommit()) {
					con.commit();
				}
				return value;
			}
		} catch(SQLException e) {
			if(!isAutoCommit()) {
				con.rollback();
			}
			throw e;
		} finally {
			release(con);
		}
	}
	
	/**
	 * Schnittstelle die als Callback für
	 * {@link ConnectionPool#executePreparedStatement(String, PreparedStatementCallback, ResultSetCallback)}
	 * verwendet wird um Parameter eines {@link PreparedStatement}s zu konfigurieren.
	 */
	public interface PreparedStatementCallback {
		/**
		 * Wird vom {@link ConnectionPool} aufgerufen, wenn ein {@link PreparedStatement} konfiguriert werden soll.
		 * @param stmt Das zu konfigurierende {@link PreparedStatement}
		 * @throws SQLException Wenn ein Fehler bei der Konfiguration aufgetreten ist
		 */
		void callback(PreparedStatement stmt) throws SQLException;
	}
	
	/**
	 * Schnittstelle die als Callback für
	 * {@link ConnectionPool#executeStatement(String, ResultSetCallback)} und
	 * {@link ConnectionPool#executePreparedStatement(String, PreparedStatementCallback, ResultSetCallback)}
	 * verwendet wird um die Ergebnisse eines {@link ResultSet}s auszulesen.
	 * @param <T> Der Rückgabetyp des Callbacks
	 */
	public interface ResultSetCallback<T> {
		/**
		 * Wird vom {@link ConnectionPool} aufgerufen, wenn ein {@link ResultSet} ausgelesen werden soll.
		 * @param rs Das auszulesende {@link ResultSet}
		 * @return Die Rückgabe des Callbacks (Informationen des {@link ResultSet}s in Objekt(e) konvertiert
		 * @throws SQLException Wenn ein Fehler beim Auslesen aufgetreten ist
		 */
		T callback(ResultSet rs) throws SQLException;
	}
	
	@Override
	protected void finalize() throws Throwable {
		close();
	}
}
