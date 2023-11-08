-- Active: 1694521027087@@127.0.0.1@3306@i12gafen
DROP DATABASE IF EXISTS i12gafen;
CREATE DATABASE IF NOT EXISTS i12gafen;
USE i12gafen;

DROP TABLE IF EXISTS activities_monitors;
DROP TABLE IF EXISTS activities_campaments;
DROP TABLE IF EXISTS monitors_campaments;
DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS monitors;
DROP TABLE IF EXISTS inscriptions;
DROP TABLE IF EXISTS campaments;
DROP TABLE IF EXISTS assistants;

CREATE TABLE IF NOT EXISTS activities(
    name VARCHAR(64) PRIMARY KEY,
    education_level VARCHAR(64),
    schedule VARCHAR(64),
    max_participants INT,
    num_monitors INT
);

CREATE TABLE IF NOT EXISTS monitors(
    monitor_id INT PRIMARY KEY,
    name VARCHAR(64),
    surname VARCHAR(64),
    special_edu BOOLEAN
);

CREATE TABLE IF NOT EXISTS campaments(
    camp_id INT PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    educate_level VARCHAR(64),
    max_assistant INT
);

CREATE TABLE IF NOT EXISTS activities_monitors(
    act_id VARCHAR(64) PRIMARY KEY,
    monitor_id INT
);

CREATE TABLE IF NOT EXISTS activities_campaments(
    act_id VARCHAR(64) PRIMARY KEY,
    camp_id INT
);

CREATE TABLE IF NOT EXISTS monitors_campaments(
    monitor_id INT PRIMARY KEY,
    camp_id INT
);

CREATE TABLE IF NOT EXISTS assistants(
    ass_id INT PRIMARY KEY,
    name VARCHAR(64),
    surname VARCHAR(64),
    birth_date DATE,
    attention BOOLEAN
);

CREATE TABLE IF NOT EXISTS inscriptions(
    ass_id INT PRIMARY KEY,
    type VARCHAR(64),
    date DATE,
    cancelled BOOLEAN,
    price FLOAT,
    schendule VARCHAR(64),
    camp_id INT
);

ALTER TABLE activities_monitors ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_monitors ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE activities_campaments ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (ass_id) REFERENCES assistants(ass_id);

INSERT INTO activities (name, education_level, schedule, max_participants, num_monitors)
VALUES
    ('Natación', 'Infantil', 'Mañana', 20, 2),
    ('Senderismo', 'Adolescente', 'Tarde', 15, 1),
    ('Manualidades', 'Juvenil', 'Noche', 10, 3);

INSERT INTO monitors (monitor_id, name, surname, special_edu)
VALUES
    (1, 'Juan', 'Pérez', true),
    (2, 'María', 'López', false),
    (3, 'Miguel', 'González', true);

INSERT INTO campaments (camp_id, start_date, end_date, educate_level, max_assistant)
VALUES
    (1, '2023-07-15', '2023-07-22', 'Infantil', 50),
    (2, '2023-08-10', '2023-08-17', 'Juvenil', 40),
    (3, '2023-09-05', '2023-09-12', 'Adolescente', 30);

INSERT INTO activities_monitors (act_id, monitor_id)
VALUES
    ('Natación', 1),
    ('Senderismo', 2),
    ('Manualidades', 3);

INSERT INTO activities_campaments (act_id, camp_id)
VALUES
    ('Natación', 1),
    ('Senderismo', 2),
    ('Manualidades', 3);

INSERT INTO monitors_campaments (monitor_id, camp_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO assistants (ass_id, name, surname, birth_date, attention)
VALUES
    (1, 'Ana', 'Martínez', '2005-03-12', true),
    (2, 'David', 'Gómez', '2006-08-27', false),
    (3, 'Olivia', 'Johnson', '2004-11-05', true);

INSERT INTO inscriptions (ass_id, type, date, cancelled, price, schendule, camp_id)
VALUES
    (1, 'Completa', '2023-07-05', FALSE, 350.00, 'MORNING', 1),
    (2, 'Completa', '2023-07-10', FALSE, 300.00, 'AFTERNOON', 1),
    (3, 'Parcial', '2023-09-01', FALSE, 320.00, 'MORNING', 3);

SELECT * FROM assistants;
SELECT * FROM campaments;

SELECT * FROM inscriptions WHERE type = 'Parcial';
SELECT * FROM inscriptions WHERE type = 'Completa';