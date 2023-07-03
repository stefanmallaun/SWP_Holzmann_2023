CREATE TABLE IF NOT EXISTS person(
    username VARCHAR(100) PRIMARY KEY,
    passwd VARCHAR(250),
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    role VARCHAR(50) NOT NULL
);