create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('fish', 365, null);
insert into fauna(name, avg_age, discovery_date) values('snakes', 180, date '119-01-01');
insert into fauna(name, avg_age, discovery_date) values('butterfly', 30, date '485-01-01');
insert into fauna(name, avg_age, discovery_date) values('dinosaur', 9999, date '01-01-01'); -- так и не понял как создать запись "40млн лет до нашей эры"
insert into fauna(name, avg_age, discovery_date) values('dog', 3800, date '245-01-01');

select * from fauna where name like '%fish%'; --извлечение данных, у которых имя name содержит подстроку fish
select * from fauna where avg_age between 100 and 500;; --извлечение данных, у которых сред. продолжительность жизни находится в диапазоне от 100 до 500
select * from fauna where discovery_date is null; --извлечение данных, у которых дата открытия не известна (null)
select * from fauna where discovery_date < to_date('300','yyyy'); --извлечение данных видов, у которых дата открытия раньше 300 года
