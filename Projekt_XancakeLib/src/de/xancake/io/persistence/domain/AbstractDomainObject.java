package de.xancake.io.persistence.domain;

import java.io.IOException;
import java.util.Objects;
import de.xancake.io.persistence.bind.TypeBinding;
import de.xancake.io.persistence.broker.PersistenceBroker;

public abstract class AbstractDomainObject<T> implements DomainObject {
	private PersistenceBroker _broker;
	private Persistable<T> _persistable;
	
	/**
	 * Lädt das Domänen-Objekt mit der übergebenen ID aus der Persistenz.
	 * @param broker Der Broker
	 * @param binding Das Binding, welches den Typen beschreibt
	 * @param id Die ID des zu ladenden Objekts
	 * @throws IOException Wenn ein Fehler beim Laden des Objekts auftritt
	 */
	public AbstractDomainObject(PersistenceBroker broker, TypeBinding<T> binding, int id) throws IOException {
		_broker = Objects.requireNonNull(broker);
		_persistable = new PersistableImpl<>(binding, id);
		_persistable.load(_broker);
	}
	
	/**
	 * Erzeugt ein neues Domänen-Objekt mit dem übergebenen Persistenz-Objekt.
	 * @param broker Der Broker
	 * @param binding Das Binding, welches den Typen beschreibt
	 * @param object Das Persistenz-Objekt
	 */
	public AbstractDomainObject(PersistenceBroker broker, TypeBinding<T> binding, T object) {
		_broker = Objects.requireNonNull(broker);
		_persistable = new PersistableImpl<>(binding, object);
	}
	
	protected Persistable<T> getPersistable() {
		return _persistable;
	}
	
	protected T getObject() {
		return _persistable.getObject();
	}
	
	protected PersistenceBroker getBroker() {
		return _broker;
	}
	
	@Override
	public Integer getId() {
		return getPersistable().getId();
	}
	
	@Override
	public void store() throws IOException {
		getPersistable().store(getBroker());
	}
	
	@Override
	public void delete() throws IOException {
		getPersistable().delete(getBroker());
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getObject().toString();
	}
}
