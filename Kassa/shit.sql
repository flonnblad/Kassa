DROP VIEW lager;
DROP TABLE lagerF;
DROP TABLE artikelL;
DROP TABLE kvitto;
DROP TABLE artikel;
DROP TABLE artikelK;


CREATE TABLE artikelK (
    nr CHAR(3) PRIMARY KEY,
    namn VARCHAR(20)
    );

CREATE TABLE artikel (
    artnr CHAR(7),
    artKlass CHAR(3),
    ean CHAR(10) NOT NULL,
    namn VARCHAR(20) NOT NULL,
    pris INT DEFAULT 0 CHECK (pris > 0),
    PRIMARY KEY (artnr, artKlass),
    FOREIGN KEY (artKlass) REFERENCES artikelK
	);

CREATE TABLE kvitto (
    kvittonr INT PRIMARY KEY,
    datum CHAR(10) NOT NULL
    );

CREATE TABLE artikelL (
    kvittonr INT,
    artnr CHAR(7),
    artklass CHAR(3),
    antal INT,
    PRIMARY KEY (kvittonr, artnr, artKlass),
    FOREIGN KEY (kvittonr) REFERENCES kvitto,
    FOREIGN KEY (artnr, artKlass) REFERENCES artikel
    );

CREATE TABLE lagerF (
    artnr CHAR(7),
    artklass CHAR(3),
    datum CHAR(10),
    antal INT,
    PRIMARY KEY (datum, artnr, artklass),
    FOREIGN KEY (artnr, artklass) REFERENCES artikel
    );

CREATE VIEW lager AS
    SELECT artnr, artklass, SUM(antal) as lager FROM lagerF GROUP BY artnr, artklass;
