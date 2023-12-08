--liquibase formatted sql

--changeset Reshetov:1
--comment first migration

CREATE TABLE userdb
(
    id SERIAL,
    user_name varchar(256),
    age integer,
    password varchar(256),
    PRIMARY KEY(id)
);

INSERT INTO userdb (user_name, age, password)
VALUES  ('user1', 33, 'pass1'),
        ('user2', 34, 'pass2')

CREATE TABLE house
(
    id SERIAL,
    address varchar(256),
    user_id integer,
    CONSTRAINT pk_user_id
    FOREIGN KEY (user_id)
    REFERENCES userdb(id)
);

INSERT INTO house (address, user_id)
VALUES  ('address1', 1),
        ('address2', 2)


--rollback truncate table user