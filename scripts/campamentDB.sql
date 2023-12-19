-- Active: 1694521027087@@127.0.0.1@3306@i12almuf
DROP DATABASE IF EXISTS i12almuf;
CREATE DATABASE IF NOT EXISTS i12almuf;
USE i12almuf;

DROP TABLE IF EXISTS activities_monitors;
DROP TABLE IF EXISTS activities_campaments;
DROP TABLE IF EXISTS monitors_campaments;
DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS monitors;
DROP TABLE IF EXISTS inscriptions;
DROP TABLE IF EXISTS campaments;
DROP TABLE IF EXISTS assistants;
DROP TABLE IF EXISTS users;

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
    attention BOOLEAN,
    email VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS inscriptions(
    i_id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(64),
    date DATE,
    cancelled BOOLEAN,
    price FLOAT,
    schendule VARCHAR(64),
    ass_id INT,
    camp_id INT
);

CREATE TABLE IF NOT EXISTS users(
    email VARCHAR(64) PRIMARY KEY,
    name VARCHAR(64),
    password VARCHAR(64),
    type VARCHAR(64)
);

ALTER TABLE activities_monitors ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_monitors ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE activities_campaments ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (ass_id) REFERENCES assistants(ass_id);
ALTER TABLE assistants ADD FOREIGN KEY (email) REFERENCES users(email);

--
INSERT INTO activities (name, education_level, schedule, max_participants, num_monitors)
VALUES
    ('Baloncesto', 'CHILD', 'MORNING', 20, 3),
    ('Natacion', 'YOUTH', 'AFTERNOON', 15, 2),
    ('Futbol', 'CHILD', 'AFTERNOON', 25, 4),
    ('Voleibol', 'TEENAGER', 'MORNING', 15, 2),
    ('Artes Marciales', 'YOUTH', 'AFTERNOON', 20, 3),
    ('Excursion al Aire Libre', 'CHILD', 'MORNING', 30, 5);

INSERT INTO monitors (monitor_id, name, surname, special_edu)
VALUES
    (1, 'Juan', 'Perez', true),
    (2, 'Luis', 'Martinez', false),
    (3, 'Ana', 'Sanchez', true),
    (4, 'Elena', 'Garcia', false),
    (5, 'Carlos', 'Rodriguez', true),
    (6, 'Laura', 'Fernandez', true);

INSERT INTO campaments (camp_id, start_date, end_date, educate_level, max_assistant)
VALUES
    (1, '2023-10-01', '2023-10-10', 'CHILD', 100),
    (2, '2024-08-15', '2024-08-24', 'YOUTH', 80),
    (3, '2024-06-20', '2025-06-30', 'CHILD', 120),
    (4, '2024-07-05', '2024-07-15', 'TEENAGER', 50),
    (5, '2023-11-15', '2023-11-25', 'CHILD', 75),
    (6, '2024-05-10', '2024-05-20', 'YOUTH', 60);

INSERT INTO activities_monitors (act_id, monitor_id)
VALUES
    ('Baloncesto', 1),
    ('Natacion', 2),
    ('Futbol', 3),
    ('Voleibol', 4),
    ('Artes Marciales', 5),
    ('Excursion al Aire Libre', 6);

INSERT INTO activities_campaments (act_id, camp_id)
VALUES
    ('Baloncesto', 1),
    ('Natacion', 2),
    ('Futbol', 3),
    ('Voleibol', 4),
    ('Artes Marciales', 5),
    ('Excursion al Aire Libre', 6);

INSERT INTO monitors_campaments (monitor_id, camp_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6);

INSERT INTO users (email, name, password, type)
VALUES
    ('maria@example.com', 'Maria', 'password123', 'ASSISTANT'),
    ('pedro@example.com', 'Pedro', 'password456', 'ASSISTANT'),
    ('sofia@example.com', 'Sofia', 'password789', 'ADMIN'),
    ('diego@example.com', 'Diego', 'password123', 'ADMIN'),
    ('alicia@example.com', 'Alicia', 'password456', 'ASSISTANT'),
    ('javier@example.com', 'Javier', 'password789', 'ASSISTANT');

INSERT INTO assistants (ass_id, name, surname, birth_date, attention, email)
VALUES
    (1, 'Maria', 'Gonzalez', '2005-03-15', true, 'maria@example.com'),
    (2, 'Pedro', 'Lopez', '2006-02-20', true, 'pedro@example.com'),
    (3, 'Sofia', 'Rodriguez', '2004-08-10', false, 'sofia@example.com'),
    (4, 'Diego', 'Hernandez', '2003-09-25', true, 'diego@example.com'),
    (5, 'Alicia', 'Martinez', '2005-01-12', false, 'alicia@example.com'),
    (6, 'Javier', 'Diaz', '2004-07-18', true, 'javier@example.com');

INSERT INTO inscriptions (type, date, cancelled, price, schendule, ass_id, camp_id)
VALUES
    ('PARCIAL', '2023-05-10', false, 100.00, 'MORNING', 1, 1),
    ('COMPLETE', '2023-06-05', false, 120.00, 'AFTERNOON', 2, 1),
    ('PARCIAL', '2023-05-20', false, 110.00, 'MORNING', 3, 2),
    ('COMPLETE', '2024-06-01', false, 150.00, 'AFTERNOON', 4, 4),
    ('PARCIAL', '2023-10-05', false, 80.00, 'MORNING', 5, 5),
    ('PARCIAL', '2023-10-05', false, 80.00, 'MORNING', 5, 5),
    ('COMPLETE', '2024-04-15', false, 130.00, 'AFTERNOON', 6, 6);

 SELECT * FROM assistants;
 SELECT * FROM activities;
 SELECT * FROM campaments;
 SELECT * FROM monitors;
 SELECT * FROM inscriptions;
 SELECT * FROM activities_campaments;
 SELECT * FROM activities_monitors;
 SELECT * FROM monitors_campaments;
SELECT * FROM users;

SELECT * FROM campaments WHERE camp_id IN (SELECT camp_id FROM inscriptions WHERE ass_id = 2);

DELETE FROM inscriptions WHERE type = 'PARCIAL' AND camp_id = 2 AND ass_id = 3;

SELECT
    c.*,
    COUNT(i.i_id) AS num_inscriptions
FROM
    campaments c
LEFT JOIN
    inscriptions i ON c.camp_id = i.camp_id
WHERE
    i.cancelled = FALSE OR i.cancelled IS NULL
GROUP BY
    c.camp_id;

SELECT
    c.*,
    COUNT(CASE WHEN i.type = 'COMPLETE' THEN 1 ELSE NULL END) AS num_complete,
    COUNT(CASE WHEN i.type = 'PARCIAL' THEN 1 ELSE NULL END) AS num_parcials
FROM
    campaments c
LEFT JOIN
    inscriptions i ON c.camp_id = i.camp_id
GROUP BY
    c.camp_id;


