CREATE TABLE authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

CREATE TABLE IF NOT EXISTS type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS rule (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS accident (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000),
    type_id INT NOT NULL REFERENCES type(id)
);

CREATE TABLE IF NOT EXISTS accident_rule (
    id SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accident(id),
    rule_id INT NOT NULL REFERENCES rule(id)
);

INSERT INTO type (name) VALUES
        ('Превышение скорости'),
        ('Нарушение правил парковки'),
        ('Движение на запрещающий сигнал светофора'),
        ('ДТП');

INSERT INTO rule (name) VALUES ('Статья №1'), ('Статья №2'), ('Статья №3');



insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
(select id from authorities where authority = 'ROLE_ADMIN'));


