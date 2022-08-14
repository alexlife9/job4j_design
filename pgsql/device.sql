create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iph', 100), ('xia', 70), ('sam', 50);
insert into people(name) values ('alex'), ('petr'), ('roma');
insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (3, 1);

select avg(price) --средняя цена всех устройств
from devices d;

/*Используя группировку вывести для каждого человека среднюю цену его устройств.*/
select p.name, avg(d.price) --выбираем колонки - имя человека и среднюю цену его устройств
from people AS p --выбор делаем из таблицы Люди. даем ей сокращенное название
join devices_people AS dp ON p.id = dp.people_id --присоединяем к выбранной выше таблице вспомогательную таблицу ПРИ условии что у них одинаковые поля
join devices AS d ON d.id = dp.device_id --а также присоединяем еще одну таблицу ПРИ такой же проверке условий
group by p.name  --делаем группировку по имени человека, потому что в начале запроса мы выбрали именно этот столбец - select p.name

/*Дополнить запрос выше условием, что средняя стоимость устройств должна быть больше 60.*/
select p.name, avg(d.price)
from people AS p
join devices_people AS dp ON p.id = dp.people_id
join devices AS d ON d.id = dp.device_id
group by p.name
having avg(d.price) > 60;


