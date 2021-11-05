DROP DATABASE IF EXISTS sql_pbz_2;
CREATE DATABASE sql_pbz_2;

USE sql_pbz_2;

DROP TABLE IF EXISTS documents;
CREATE TABLE documents(
	id SMALLINT NOT NULL PRIMARY KEY UNIQUE,
    type VARCHAR(50) NOT NULL,
    data_of_creation DATE NOT NULL,
    content TEXT(10000) NOT NULL,
    event TEXT(10000) NOT NULL,
    performer SMALLINT NOT NULL,
    date_of_completion DATE NOT NULL,
    label TEXT(1000) NOT NULL
);
INSERT INTO documents VALUES
	(1, "приказ", "2008-11-11", "Первый этап строительства торгового центра", "Снесение детского сада, Расчистка территории", 1, "2015-12-15", ""),
    (2, "распоряжение", "2013-09-23", "Совершенствование охраны труда", "Установить порядок выпуска приказов", 3, "2019-02-06", "приказы по случаю нарушения охраны труда");
    
DROP TABLE IF EXISTS performers;
CREATE TABLE performers(
	id SMALLINT NOT NULL PRIMARY KEY UNIQUE,
    position VARCHAR(50) NOT NULL,
    subdivision VARCHAR(70) NOT NULL,
    fio VARCHAR(70) NOT NULL
);
INSERT INTO performers VALUES
	(1, "инженер", "строительное", "Дудкин Андрей Романович"),
    (2, "прораб", "строительное", "Багдасарян Рафаэль Мкртычевич"),
    (3, "адвокат", "юридическое", "Крюкова Ольга Петровна");