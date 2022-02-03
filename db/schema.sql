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


