DROP DATABASE IF EXISTS sql_pbz_2;
CREATE DATABASE sql_pbz_2;

USE sql_pbz_2;

DROP TABLE IF EXISTS performers;
CREATE TABLE performers(
	performer_id INT NOT NULL UNIQUE PRIMARY KEY,
    position VARCHAR(50) NOT NULL,
    subdivision VARCHAR(70) NOT NULL,
    fio VARCHAR(70) NOT NULL
);
INSERT INTO performers VALUES
	(1, "инженер", "строительное", "Дудкин Андрей Романович"),
    (2, "прораб", "строительное", "Багдасарян Рафаэль Мкртычевич"),
    (3, "адвокат", "юридическое", "Крюкова Ольга Петровна");

DROP TABLE IF EXISTS documents;
CREATE TABLE documents(
	document_id INT NOT NULL UNIQUE PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    date_of_creation DATE NOT NULL,
    content TEXT(10000) NOT NULL,
    event TEXT(10000) NOT NULL,
    performer INT NOT NULL,
    date_of_completion DATE NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (performer) REFERENCES performers (performer_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO documents VALUES
	(1, "приказ", "2008-11-11", "Первый этап строительства торгового центра", "Снесение детского сада, Расчистка территории", 1, "2015-12-15", false),
    (2, "распоряжение", "2013-09-23", "Совершенствование охраны труда", "Установить порядок выпуска приказов", 3, "2019-02-06", true);
    