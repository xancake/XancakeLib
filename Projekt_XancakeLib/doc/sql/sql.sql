-- http://www.w3schools.com/sql/default.asp

CREATE TABLE Kategorie (
  kID    INTEGER     NOT NULL,
  kBez   varchar(40),
  CONSTRAINT katPK
    PRIMARY KEY (kID)
);

CREATE TABLE Artikel (
  aID    INTEGER      NOT NULL,
  aBez   varchar(20),
  aPreis decimal(8,2),
  kID    INTEGER      NOT NULL,
  CONSTRAINT artPK
    PRIMARY KEY (aID),
  CONSTRAINT katFK
    FOREIGN KEY (kID)
    REFERENCES Kategorie (kID)
);

ALTER TABLE Artikel ADD aRandomAttribute varchar(20);
ALTER TABLE Artikel [MODIFY|MODIFY COLUMN|ALTER COLUMN] aRandomAttribute INTEGER;
ALTER TABLE Artikel DROP COLUMN aRandomAttribute;

INSERT INTO Kategorie (kID, kBez) VALUES (1001, 'Nahrung');
INSERT INTO Kategorie (kID, kBez) VALUES (1002, 'Kleidung');
INSERT INTO Kategorie (kID, kBez) VALUES (1003, 'Freizeit');
INSERT INTO Kategorie (kID, kBez) VALUES (1004, 'Sport');
INSERT INTO Kategorie (kID, kBez) VALUES (1005, 'Kinder');

INSERT INTO Artikel (aID, kID, aBez, aPreis) VALUES (1001, 1001, 'Bratpfanne', 13.95);
INSERT INTO Artikel (aID, kID, aBez, aPreis) VALUES (1002, 1002, 'Braunes T-Shirt', 6.50);
INSERT INTO Artikel (aID, kID, aBez, aPreis) VALUES (1003, 1003, 'Campingstuhl', 8.59);
INSERT INTO Artikel (aID, kID, aBez, aPreis) VALUES (1004, 1004, 'Fußball', 9.99);
INSERT INTO Artikel (aID, kID, aBez, aPreis) VALUES (1005, 1005, 'Babybrei', 8.95);

UPDATE Artikel SET aBez='Babymilch' WHERE aID=1005;

DELETE FROM Artikel WHERE aID=1005;

SELECT * FROM table1 AS t1 WHERE t1.attribute1='100'
SELECT t1.attribute1, t1.attribute2, t2.attribute1 FROM table1 AS t1 INNER JOIN table2 AS t2 ON t1.key=t2.key WHERE t1.attribute1 LIKE '%001';
SELECT kategorie, SUM(preis) AS 'katpreis' FROM Artikel WHERE katpreis<50 GROUP BY kategorie ORDER BY katpreis DESC;

DROP TABLE Artikel;
DROP TABLE Kategorie;

