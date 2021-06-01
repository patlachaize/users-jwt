DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;
CREATE USER if not exists 'adm'@'localhost' IDENTIFIED BY 'adm';
GRANT ALL PRIVILEGES ON users.* TO 'adm'@'localhost';
USE users
CREATE TABLE users (
    id integer auto_increment,
    login VARCHAR(20) ,
    password VARCHAR(120),
    PRIMARY KEY(id)
);
insert into users values (1,'Pierre', 'aaa');
insert into users values (2,'Sophie', 'bbb');
select * from users;
