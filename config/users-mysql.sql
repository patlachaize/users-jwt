DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;
CREATE USER if not exists 'adm'@'localhost' IDENTIFIED BY 'adm';
GRANT ALL PRIVILEGES ON users.* TO 'adm'@'localhost';
USE users
CREATE TABLE users (
    id integer auto_increment,
    login VARCHAR(20) ,
    password VARCHAR(20),
    PRIMARY KEY(id)
);
insert into users values (1,'Jean', 'aaa');
insert into users values (2,'Sylvie', 'bbb');
insert into users values (3,'Marc', 'ccc');
select * from users;
