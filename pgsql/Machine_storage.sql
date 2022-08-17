create table car_bodies(
	id serial primary key,
    name varchar(255)
);

create table car_engines(
	id serial primary key,
    name varchar(255)
);

create table car_transmissions(
	id serial primary key,
    name varchar(255)
);

create table cars(
	id serial primary key,
    name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('седан');
insert into car_bodies(name) values('хэтчбек');
insert into car_bodies(name) values('пикап');

insert into car_engines(name) values('двс');
insert into car_engines(name) values('электро');
insert into car_engines(name) values('комбо');

insert into car_transmissions(name) values('механика');
insert into car_transmissions(name) values('вариатор');
insert into car_transmissions(name) values('автомат');

insert into cars(name, body_id, engine_id, transmission_id) values('1.ауди', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('2.бмв', 2, 2, 3);
insert into cars(name, body_id, engine_id) values('3.лада', 1, 2);
insert into cars(name, body_id, transmission_id) values('4.мерс', 2, 3);
insert into cars(name, engine_id, transmission_id) values('5.шкода', 3, 1);

/*1. Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать. 
В таком случае значение может быть null при выводе (например, название двигателя null) */
select c.name AS марка, cb.name AS кузов, ce.name AS движок, ct.name AS КПП
from cars AS c
left join car_bodies AS cb ON c.body_id = cb.id
left join car_engines AS ce ON c.engine_id = ce.id
left join car_transmissions AS ct ON c.transmission_id = ct.id;

/*2. Вывести кузовы, которые не используются НИ в одной машине. 
"Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies. 
Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", 
а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек". 
Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом */
select cb.name AS кузов                  --выбираем колонку кузов
from car_bodies AS cb                    --в таблице кузовов 
left join cars AS c ON c.body_id = cb.id --присоединяем к ней таблицу тачек и проверяем условие присоединения
where c.id is null;                       --выводим только те строки из полной таблицы тачек ГДЕ поле Кузовов пустое.

/*3. Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2 */
select ce.name AS движок                  
from car_engines AS ce                     
left join cars AS c ON c.engine_id = ce.id
where c.id is null;

/*4. Вывести коробки передач, которые не используются НИ в одной машине */
select ct.name AS движок                  
from car_transmissions AS ct                     
left join cars AS c ON c.transmission_id = ct.id
where c.id is null;
