CREATE DATABASE course_management;

-- while using 'course_management' database

CREATE TABLE Person (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL,
    role int NOT NULL
);