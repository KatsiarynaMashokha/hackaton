SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams(
    id int PRIMARY KEY auto_increment,
    teamName VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS members(
    memberId int PRIMARY KEY auto_increment,
    teamId INTEGER,
    name VARCHAR,
    age INTEGER
);