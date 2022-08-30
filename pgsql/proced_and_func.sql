create table products(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count int default 0,
    price int
);

insert into products(name, producer, count, price) values ('product_1', 'producer_1', 10, 100);
insert into products(name, producer, count, price) values ('product_2', 'producer_2', 20, 200);
insert into products(name, producer, count, price) values ('product_3', 'producer_3', 30, 300);
insert into products(name, producer, count, price) values ('product_4', 'producer_4', 40, 400);

--1.создаем процедуру добавления данных. Вызывается так: call insert_data(параметры)
create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

--2.создаем процедуру обновления данных. 
create or replace procedure update_data(u_count integer, day text, tax float, u_id integer) 
                             -- процедура принимает 4 параметра – количество товара на изменение, день недели, налог, id записи на изменение
language 'plpgsql'                                               
as $$
    BEGIN
        if u_count > 0 THEN -- 1 условие
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN     -- 2 условие
            update products set price = price + price * tax; -- то цена будет увеличена на tax
        end if;
		if day = 'saturday' THEN  -- 3 условие
            update products set count = count * 1.5; -- то кол-во увеличить в 1,5 раза
        end if;
		
    END;
$$;

CALL update_data(100, 'saturday', 0.5, 5) --это ОБНОВЛЕНИЕ ВСЕХ ранее созданных записей!
--1 условие не сработало ни для одной записи, потому что указали id = 5. а такой записи нет.
--2 условие сработало на ВСЕ записи. Была цена 100, стала 150 (100+100*0,5). И так далее на все 4 записи.
--3 условие сработало на ВСЕ записи. Было кол-во 10, стало 15 (10*1,5). И так далее на все 4 записи.
-- итого сейчас в таблице вот такие значения:
--1   15   150
--2   30   300
--3   45   450
--4   60   600

--добавим еще одно обновление:
CALL update_data(7, 'monday', 3, 1)
--1 условие сработало для записи id = 1. Кол-во стало 8 (15-7). Обновленная запись переместилась вниз таблицы
--2 условие сработало на ВСЕ записи. Была цена 300, стала 1200 (300+300*3). И так далее на все 4 записи.
--3 условие НЕ сработало, потому что день недели не тот что указан в условиях процедуры.
-- итого сейчас в таблице вот такие значения:
--2   30   1200
--3   45   1800
--4   60   2400
--1   8    600

--3.создаем процедуру удаления данных. 
create or replace procedure delete_data(d_name varchar) --удаляем по названию продукта
language 'plpgsql'
as $$
	BEGIN
	delete from products 
	where name = d_name and count < 40; --удаляем продукт с искомым именем и кол-вом меньше 40
	END
$$;

--CALL delete_data('product_2') -- запускаем процедуру удаления
--product_2 успешно удален, потому что его кол-во было 30. При этом product_1 остался на месте потому что имя другое несмотря на кол-во = 8.
-- итого сейчас в таблице вот такие значения:
--3   45   1800
--4   60   2400
--1   8    600

--4.создаем хранимую функцию добавления данных. 
create or replace function f_insert_data
(ins_name varchar, ins_producer varchar, ins_count int, ins_price int)
returns void
language 'plpgsql'
as $$
    begin
        insert into products (name, producer, count, price)
        values (ins_name, ins_producer, ins_count, ins_price);
    end;
$$;

select f_insert_data('product_5', 'producer_5', 80, 300); --запускаем функцию, которая добавит еще один продукт с заданными параметрами
-- итого сейчас в таблице вот такие значения:
--3   45   1800
--4   60   2400
--1   8    600
--5   80   300

--5.создаем хранимую функцию обновления данных. 
create or replace function f_update_data(upd_count int, tax float, upd_id int)
returns int
language 'plpgsql'
as $$
    declare
        result int;
    BEGIN
        if upd_count > 0 then   -- 1 условие
            update products set count = count + upd_count --увеличиваем кол-во
            where id = upd_id;
            select into result count --возвращаем обновленное кол-во товаров
            from products where id = upd_id;
        end if;
        if tax > 0 then         -- 2 условие
            update products set price = price + price * tax; --прибавляем налог, который укажем в параметрах при вызове данной функции
            select into result sum(price) from products; --возвращаем общую сумму товаров
        end if;
        return result;
    END;
$$;

select f_update_data(100, 0, 1); --запускаем функцию обновления данных с возвратом полученного результата
--1 условие сработало для product_1, потому что id=1. Кол-во увеличилось на 100.
--2 условие сработало для product_1 и осталось 600 (600+600*0)
-- итого сейчас в таблице вот такие значения:
--3   45   1800
--4   60   2400
--5   80    300
--1   108   600

select f_update_data(200, 1, 5); --для закрепления инфы еще один запуск функции с другими параметрами
--в Data Output видим возвращенное значение 102000 (3600+4800+1200+600)
--1 условие сработало ТОЛЬКО для product_5, потому что id=5. Кол-во увеличилось на 200. Запись переместилась вниз.
--2 условие сработало для ВСЕХ записей. Цена была 1800. Стала 3600 (1800+1800*1). И так далее на все 4 записи.
-- итого сейчас в таблице вот такие значения:
--3   45   3600
--4   60   4800
--1   108  1200
--5   280   600

--5.создаем хранимую функцию удаления данных. 
create or replace function f_delete_data(d_name varchar, d_count int) --удаляем по имени и кол-ву
returns void
language 'plpgsql'
as $$
    BEGIN
        delete from products where name like d_name; --имя в таблице содержит указанное в параметрах
        if d_count < 70 then               --если кол-во этого продукта меньше 70
            delete from products where count < d_count;
        end if;
    END;
$$;

select f_insert_data('product_3', 'producer_5', 30, 5000); --добавим запись с одинаковым именем продукта
select f_insert_data('product_3', 'producer_5', 80, 7000); --добавим запись с одинаковым именем продукта
-- итого сейчас в таблице вот такие значения:
--3   45   3600
--4   60   4800
--1   108  1200
--5   280   600
--3   30   5000
--3   80   7000

select f_delete_data('product_3', 75); -- удаляем product_3, которого меньше 75
-- итого сейчас в таблице вот такие значения:
--4   60   4800
--1   108  1200
--5   280   600
--3   80   7000  ??? этой записи тоже нет!!  Почему? Ведь условие <75. А тут 80!



