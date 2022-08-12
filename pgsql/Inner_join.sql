create table curse(				
	id serial primary key,
	name_curse varchar(255),
	status_curse varchar(255)
);

create table users( 		
	id serial primary key,
	name varchar(255),
	curse_id int references curse(id)
);

insert into curse(name_curse, status_curse) values('intern', 'done');
insert into curse(name_curse, status_curse) values('junior', 'work');

insert into users(name, curse_id) values('alex', 1);
insert into users(name, curse_id) values('alex', 2);
insert into users(name, curse_id) values('petr', 1);
insert into users(name, curse_id) values('petr', 2);

select *        --тут просто объединяем обе таблицы и выводим вообще все их данные
from users AS u --as можно не писать, но так код читабельнее
join curse AS c ON u.curse_id = c.id;

select u.name AS имя, u.curse_id AS курс    --тут выводим меняем поля на новые названия и выводим эти выбранные поля
from users AS u
join curse AS c ON u.curse_id = c.id;

select u.name AS кто, c.name_curse AS курс  --тут выводим именно название курса, а не его номер как в предыдущем примере
from users AS u
join curse AS c ON u.curse_id = c.id;
