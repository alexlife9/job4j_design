/* В таблице будем хранить данные о продуктах */

/*
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
*/

--ПРИМЕР на row уровень:

/*
/* после создания таблицы добавим код триггерной функции для расчета скидки: */
create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';
*/

/* подробнее про Триггерные функции, которые определяют логику выполнения тут: postgrespro.ru/docs/postgresql/13/plpgsql-trigger */

/* Триггер уровня строки будет активирован отдельно для каждой строки. 
С помощью специальных ключевых слов OLD и NEW триггеру будут доступны старые и новые значения полей строки, для которой он вызван.

Особенности этих ключевых слов:
- ключевые слова OLD и NEW позволяют получить доступ к столбцам в строках, затронутых триггером;
- в триггере INSERT можно использовать только NEW.column_name;
- в триггере UPDATE можно использовать OLD.column_name для ссылки на столбцы строки перед ее обновлением 
и NEW.column_name для ссылки на столбцы строки после ее обновления;
- в триггере DELETE можно использовать только OLD.column_name.
*/

/*
/* добавляем триггер, который будет срабатывать после каждой вставки новой строки и 
если количество товара меньше или равно 5 – изменять поле price и уменьшать его на 20%.*/
create trigger discount_trigger   --создаем триггер и присваиваем ему имя discount_trigger
    after insert on products      --триггер будет срабатывать после события вставки данных в таблицу products
    for each row                  --эта запись регламентирует, что триггер будет выполнен для каждой строки в таблице
    execute procedure discount()  --для выполнения триггера вызывается функция discount()
;
*/

/*
/* добавляем продукт в таблицу: */
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115); -- и при этом товара будет больше 5шт
/* триггер не сработает потому что выше мы прописали условие срабатывания <= 5*/
*/

/*
/* добавляем еще один продукт в таблицу: */
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50); --  и при этом товара будет меньше 5шт
*/

/* тригер сработал. цена у продуктов изменилась.
select * 
from products;
*/

/* Есть возможность отключить триггер. Для этого используется следующий синтаксис:
alter table имя_таблицы disable trigger имя_триггера; */


/*
/*для нашего ранее рассмотренного триггера можем записать: */
alter table products disable trigger discount_trigger;

/* и теперь повторно вставим запись, которую мы уже добавляли: */
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

/* и посмотрим на текущее содержимое: */
select * 
from products;
/* В таблицу добавилась третья строчка с тем же товаром, но с ценой без применения тригера. 
При этом запись этого товара с измененной ценой разумеется никуда не делась */
*/


/* триггер можно удалить, используется следующий синтаксис: 
drop trigger имя_триггера on имя_таблицы; */

/*
/* для нашего случая такой запрос будет иметь вид: */
drop trigger discount_trigger on products;
*/



--ПРИМЕР на statement уровень:

/*
/* функция триггера : */
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5; -- в функции сравниваем id с id из таблицы inserted:
        return new;
    END;
$$
LANGUAGE 'plpgsql';

/* создаем триггер: */
create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
	
/* Когда указывается REFERENCING, для триггера собираются переходные отношения, представляющие собой множества строк, 
включающие все строки, которые были добавлены, удалены или изменены текущим оператором SQL. 
Это позволяет триггеру наблюдать общую картину того, что сделал оператор, а не только одну строку за другой.

REFERENCING - это ключевое слово непосредственно предшествует объявлению одного или двух имён, 
по которым можно будет обращаться к переходным отношениями, образуемым при выполнении целевого оператора.

OLD TABLE/NEW TABLE - это предложение указывает, будет ли следующее имя относиться к переходному отношению 
с образом-до-изменения или к переходному отношению с образом-после-изменения.

Соответственно, на statement уровне (т.е на уровне запроса) нам необходимо создать идентификатор для таблицы вставленных записей, 
чтобы получить доступ к вставленной строке.  В данном случае это будет отношение inserted - new table as inserted. */
*/



--ЗАДАНИЕ. Создать 3 триггера:

/* 1) Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог 50% на товар. 
Действовать он должен не на каждый ряд, а на запрос (statement уровень) */

create or replace function tax50() --функция
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.50
        where id = (select id from inserted50);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger50 --сам триггер
    AFTER insert on products --срабатывает после вставки данных
    referencing new table as inserted50
    for each statement
    execute procedure tax50();

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_1', 9, 100); --добавляем новый продукт

select * 
from products; --проверка результата



/* 2) Триггер должен срабатывать до вставки данных и высчитывать налог 50% на товар. Здесь используем row уровень. */
create or replace function check_tax() --функция
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.5
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger check_tax_trigger  --сам триггер
    BEFORE insert on products     --срабатывает ДО вставки данных для каждой новой строки
    for each row                 
    execute procedure check_tax()
;

--останавливаем ненужные триггеры для точной проверки корректности выполнения задания №2
alter table products disable trigger tax_trigger50;
alter table products disable trigger tax_trigger;

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_2', 7, 2000); --добавляем новый продукт

select * 
from products; --проверка результата



/* 3) Нужно написать триггер на row уровне, который при вставке продукта в таблицу products, 
будет заносить имя, цену и текущую дату в таблицу history_of_price. */
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_history_price() -- функция
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (new.name, new.price, now());
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_price_trigger  --сам триггер
    AFTER insert on products     --срабатывает ПОСЛЕ вставки данных для каждой новой строки
    for each row                 
    execute procedure insert_history_price()
;

insert into products (name, producer, count, price) VALUES ('product_10', 'producer_5', 6, 5000); --добавляем новый продукт

select *
from history_of_price; --проверяем успешность записи




