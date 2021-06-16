CONNECT system/root
drop user utilisateurs cascade;
CREATE USER utilisateurs identified by utilisateurs;
GRANT connect, resource, unlimited tablespace to utilisateurs;
CONNECT utilisateurs/utilisateurs

CREATE TABLE users (
    id number(3),
    login VARCHAR2(20) ,
    password VARCHAR2(20),
    PRIMARY KEY(id)
);
create sequence seq_users;
insert into users values (seq_users.nextval,'Jean', 'aaa');
insert into users values (seq_users.nextval,'Sylvie', 'bbb');
insert into users values (seq_users.nextval,'Marc', 'ccc');
select * from users;
