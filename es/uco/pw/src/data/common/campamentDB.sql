DROP DATABASE IF EXISTS i12gafen;
CREATE DATABASE IF NOT EXISTS i12gafen;
USE i12gafen;

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
    i_id INT PRIMARY KEY,
    type VARCHAR(64),
    date DATE,
    price FLOAT,
    campament_id INT,
    ass_id INT
);

ALTER TABLE activities_monitors ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_monitors ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE activities_campaments ADD FOREIGN KEY (act_id) REFERENCES activities(name);
ALTER TABLE activities_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (monitor_id) REFERENCES monitors(monitor_id);
ALTER TABLE monitors_campaments ADD FOREIGN KEY (camp_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (campament_id) REFERENCES campaments(camp_id);
ALTER TABLE inscriptions ADD FOREIGN KEY (ass_id) REFERENCES assistants(ass_id);