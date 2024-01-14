CREATE DATABASE course_management;

-- while using 'course_management' database

CREATE TYPE roles AS enum('ADMINISTRATOR','TEACHER','STUDENT');

CREATE TABLE Users (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL,
    role roles NOT NULL
);