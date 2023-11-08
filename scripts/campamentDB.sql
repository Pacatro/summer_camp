-- Active: 1694082650616@@127.0.0.1@3306@i12gafen
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
    id INT PRIMARY KEY AUTO_INCREMENT,
    act_id VARCHAR(64),
    monitor_id INT
);

CREATE TABLE IF NOT EXISTS activities_campaments(
    id INT PRIMARY KEY AUTO_INCREMENT,
    act_id VARCHAR(64),
    camp_id INT
);

CREATE TABLE IF NOT EXISTS monitors_campaments(
    id INT PRIMARY KEY AUTO_INCREMENT,
    monitor_id INT,
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

-- Insertar datos en la tabla 'activities'
INSERT INTO activities (name, education_level, schedule, max_participants, num_monitors)
VALUES
    ('Actividad1', 'Infantil', 'MORNING', 20, 3),
    ('Actividad2', 'Juvenil', 'AFTERNOON', 15, 2),
    ('Actividad3', 'Infantil', 'AFTERNOON', 25, 4);

-- Insertar datos en la tabla 'monitors'
INSERT INTO monitors (monitor_id, name, surname, special_edu)
VALUES
    (1, 'Juan', 'Perez', true),
    (2, 'Luis', 'Martínez', false),
    (3, 'Ana', 'Sánchez', true);

-- Insertar datos en la tabla 'campaments'
INSERT INTO campaments (camp_id, start_date, end_date, educate_level, max_assistant)
VALUES
    (1, '2023-07-01', '2023-07-10', 'Infantil', 100),
    (2, '2023-08-15', '2023-08-24', 'Juvenil', 80),
    (3, '2023-06-20', '2023-06-30', 'Infantil', 120);

-- Insertar datos en la tabla 'activities_monitors'
INSERT INTO activities_monitors (act_id, monitor_id)
VALUES
    ('Actividad1', 1),
    ('Actividad2', 2),
    ('Actividad3', 3);

-- Insertar datos en la tabla 'activities_campaments'
INSERT INTO activities_campaments (act_id, camp_id)
VALUES
    ('Actividad1', 1),
    ('Actividad2', 2),
    ('Actividad3', 3);

-- Insertar datos en la tabla 'monitors_campaments'
INSERT INTO monitors_campaments (monitor_id, camp_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insertar datos en la tabla 'assistants'
INSERT INTO assistants (ass_id, name, surname, birth_date, attention)
VALUES
    (1, 'Maria', 'Gonzalez', '2005-03-15', true),
    (2, 'Pedro', 'López', '2006-02-20', true),
    (3, 'Sofia', 'Rodriguez', '2004-08-10', false);

-- Insertar datos en la tabla 'inscriptions'
INSERT INTO inscriptions (ass_id, type, date, cancelled, price, schendule, camp_id)
VALUES
    (1, 'Parcial', '2023-05-10', false, 100.00, 'MORNING', 1),
    (2, 'Completa', '2023-06-05', false, 120.00, 'AFTERNOON', 1),
    (3, 'Parcial', '2023-05-20', false, 110.00, 'MORNING', 2);


SELECT * FROM assistants;
SELECT * FROM campaments;

SELECT * FROM inscriptions WHERE type = 'Parcial';
SELECT * FROM inscriptions WHERE type = 'Completa';

SELECT * FROM campaments WHERE camp_id = (SELECT camp_id FROM activities_campaments WHERE act_id = 'Actividad1');