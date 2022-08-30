/* Пример создания и использования хранимых функций */

create table products_fp (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* хранимая функция будет иметь следующий вид: */
create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void --добавляется обязательный блок return, поскольку функция обязана возвращать значение
language 'plpgsql'
as
$$
    begin
        insert into products_fp (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

/* Функции, в отличие от процедур, вызываются через обычный SELECT, т.е. следующим образом: */
select f_insert_data('product_1', 'producer_1', 25, 50);

/* добавим функцию редактирования записей в таблице: */
create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products_fp set count = count - u_count where id = u_id;
            select into result count from products_fp where id = u_id;
        end if;
        if tax > 0 THEN
            update products_fp set price = price + price * tax;
            select into result sum(price) from products_fp;
        end if;
        return result;
    end;
$$;

/* при этом в качестве возвращаемого значения при обновлении количества товара мы вернем обновленное количество товара. 
При обновлении цен мы возвращаем общую сумму всех цен товаров. Как и ранее, выполним запрос на обновление записи в таблице:*/
select f_update_data(10, 0, 1);

/* добавим еще несколько записей*/
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

select f_update_data(0, 0.2, 0);


/* добавим процедуру удаления записей в таблице: */
/* добавим функцию удаления записей в таблице: */