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

/* Добавим хранимую процедуру, которая позволит вставлять данные в эту таблицу. 
Создается процедура с помощью CREATE OR REPLACE PROCEDURE. 
Мы объявим процедуру insert_data, которая будет принимать 4 параметра с разными типами и при этом добавлять новую запись в БД. 
Такая процедура будет иметь вид: */
/*
create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;
*/

/* чтобы использовать созданную процедуру используют CALL (вызов процедуры), и при этом надо передать параметры. 
Выглядит это следующим образом: */
/*
call insert_data('product_2', 'producer_2', 15, 32); --В результате вызова процедуры в таблицу будет добавлена новая строка
*/

/* добавим процедуру для обновления данных в таблице. 
При этом процедура будет принимать 3 параметра – количество товара, налог, а также id записи. 
Если количество товара, передаваемое в метод больше 0, то мы уменьшаем на это количество товара у записи с передаваемым id. 
Если же налог больше 0, то надо увеличить price у всех записей на сумму налога. Окончательно процедура будет иметь вид: */
/*
create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;
*/

call update_data(10, 0, 1); --обновим ранее созданную запись с помощью следующего вызова нашей процедуры 
							--(в параметрах указываем число, на которое уменьшим count - 10 и id записи - 1).

/*Добавим еще несколько записей в таблицу:*/
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

/*И теперь увеличим все цены на сумму налога следующим вызовом процедуры:*/
call update_data(0, 0.2, 0);

/*Обновить процедуру (например, переименовать) как и обычно можно с помощью ALTER PROCEDURE. 
Для нашей процедуры - это будет иметь вид: */
--alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

/*Удалить процедуру можно с помощью DROP. Это будет выглядеть следующим образом: */
--drop procedure update_data(u_count integer, tax float, u_id integer);

/*Зачистка таблицы products:*/
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;


