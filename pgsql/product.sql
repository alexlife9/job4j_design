create table type(
id serial primary key,
	name_type text
);

create table product(
id serial primary key,
	name text,        					 --название продукта
	type_id int references type(id),      --категория продукта
	expired_date date, 					 --срок годности
	price float      					 --цена
);

insert into type(name_type) values ('сыр'), ('молочка'), ('мясо');

insert into product(name, type_id, expired_date, price) values('брынза', 1, '2022-08-30', 200.35);
insert into product(name, type_id, expired_date, price) values('плавленный', 1, '2022-08-25', 300.35);
insert into product(name, type_id, expired_date, price) values('моцарелла', 1, '2022-08-20', 400.35);

insert into product(name, type_id, expired_date, price) values('йогурт', 2, '2022-09-02', 19.50);
insert into product(name, type_id, expired_date, price) values('кефир', 2, '2022-09-15', 29.99);
insert into product(name, type_id, expired_date, price) values('сметана', 2, '2022-09-11', 89.99);

insert into product(name, type_id, expired_date, price) values('курятина', 3, '2022-08-15', 750.50);
insert into product(name, type_id, expired_date, price) values('говядина', 3, '2022-08-21', 950.50);
insert into product(name, type_id, expired_date, price) values('свинина', 3, '2022-08-25', 450.50);
*/

/*вывести общую таблицу со всеми столбиками полностью заполненную заданными значениями */
select *
from product AS p
join type AS t ON p.type_id = t.id;

/*1. Написать запрос получения всех продуктов с типом "СЫР". */
select t.name_type, p.name
from type AS t
join product AS p ON p.type_id = t.id
where t.name_type = 'сыр';

/*2. Написать запрос получения всех продуктов, у кого в имени есть слово "сметана". */
select *
from product
where name LIKE 'сметана';

/*3. Написать запрос, который выводит все продукты, срок годности которых истечет через 7 дней. */
select *
from product
where expired_date < current_date + interval '7 day';

/*4. Написать запрос, который выводит самый дорогой продукт. */
select *
from product
where price = (select max(price) from product);

/*5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество. */
select t.name_type, COUNT(p.name)             -- COUNT() - функция возвращающая количество записей (строк) таблицы.
from type AS t
join product AS p ON p.type_id = t.id
group by t.name_type;

/*6. Написать запрос получение всех продуктов с типом "сыр" и "молочка". */
select t.name_type, p.name
from type AS t
join product AS p ON p.type_id = t.id
where t.name_type IN ('сыр', 'молочка');

/*7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
Под количеством подразумевается количество продуктов определенного типа. 
Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, 
то количество продуктов типа "СЫР" будет 2. */
select t.name_type 
from type AS t 
join product AS p ON p.type_id = t.id
group by t.name_type
having count(t.name_type) < 10;

/*8. Вывести все продукты и их тип.*/
select p.name AS Имя_продукта, t.name_type AS Тип_продукта
from product AS p, type AS t
where p.type_id=t.id;
