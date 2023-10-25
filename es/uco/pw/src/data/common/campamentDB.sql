DROP DATABASE IF EXISTS i12gafen;
CREATE DATABASE IF NOT EXISTS i12gafen;

CREATE TABLE assistants(
    ass_id INT PRIMARY KEY,
    name VARCHAR(64),
    surname VARCHAR(64),
    birth_date DATE,
    attention BOOLEAN
);

CREATE TABLE monitors(
    mon_id INT PRIMARY KEY,
    name VARCHAR(64),
    surname VARCHAR(64),
    special_edu BOOLEAN
);

CREATE TABLE campaments(
    camp_id INT PRIMARY KEY,
    init_date DATE,
    end_date DATE,
    educate_level VARCHAR(64),
    max_assistant INT
);

CREATE TABLE activities(
    name VARCHAR(64) PRIMARY KEY,
    educate_level VARCHAR(64),

)