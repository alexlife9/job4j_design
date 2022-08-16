/*Даны две сущности, представленные в таблицах departments и employees. Отношение one-to-many. В таблицах только поле name.

1. Создать таблицы и заполнить их начальными данными*/
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id) --отношение one-to-many
);

insert into departments(name) values ('розница');
insert into departments(name) values ('опт');
insert into departments(name) values ('офис');
insert into departments(name) values ('доставка');
insert into departments(name) values ('регионы');

insert into employees(name, departments_id) values ('алекс', 1);
insert into employees(name, departments_id) values ('рома', 2);
insert into employees(name, departments_id) values ('дима', 4);
insert into employees(name, departments_id) values ('маша', null);
insert into employees(name, departments_id) values ('таня', 3);
insert into employees(name, departments_id) values (null, 5);

/*2. Выполнить запросы с left, rigth, full, cross соединениями */
select *
from employees AS e
left join departments AS d ON e.departments_id = d.id

select *
from employees AS e
right join departments AS d ON e.departments_id = d.id

select *
from employees AS e
full join departments AS d ON e.departments_id = d.id

select *
from employees AS e
cross join departments AS d

/* 3. Используя left join найти департаменты, у которых нет работников */
insert into departments(name) values ('калыма');
insert into employees(name) values ('вася');

select d.name AS департ
from departments AS d
left join employees AS e ON d.id = e.departments_id
where e.departments_id is null
group by d.id;

/*4. Используя left и right join написать запросы, 
которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). */

select *
from employees AS e
left join departments AS d ON e.departments_id = d.id

select *
from departments AS d
right join employees AS e ON e.departments_id = d.id

/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары */
create table teens(
    id serial primary key,
    name varchar(255),
	gender text
);

insert into teens(name, gender) values('алекс', 'муж');
insert into teens(name, gender) values('рома', 'муж');
insert into teens(name, gender) values('дима', 'муж');
insert into teens(name, gender) values('маша', 'жен');
insert into teens(name, gender) values('таня', 'жен');
insert into teens(name, gender) values('лена', 'жен');

select t1.name AS имя, t1.gender AS пол, t2.name AS имя, t2.gender AS пол
from teens AS t1 cross join teens AS t2
where t1.gender = 'муж' AND t2.gender = 'жен';
