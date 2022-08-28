create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

/*
 Запрос, в котором определим список студентов, у которых находится 2 и более книг одного и того же автора. 
 При этом в результирующей таблице должно быть отражено имя студента, количество книг, имя автора.*/
select s.name, count(a.name), a.name from students as s
    join orders o on s.id = o.student_id
    join books b on o.book_id = b.id
    join authors a on b.author_id = a.id
    group by (s.name, a.name) having count(a.name) >= 2;
	
/*
Представленный выше запрос хоть и не выглядит слишком громоздким, но писать такое многократно (с множеством объединений таблиц 
с помощью join) может привести к тому, что в какой-то момент можем ошибиться и не получить нужного результата. 
Поэтому такой запрос удобно поместить в представление и получать результат запроса уже по имени этого представления.

Представление создается следующим образом:
create view имя_представления as запрос_select

CREATE VIEW создает представление запроса. Создаваемое представление не является материализованным. 
Соответственно, указанный запрос будет выполняться при каждом обращении к представлению.

Возвращаясь к выше представленному запросу, создать представление для него можем следующим образом: */	
create view show_students_with_2_or_more_books
    as select s.name as student, count(a.name), a.name as author from students as s --для имени студента и имени автора для этих столбцов добавлены ALIAS
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
         group by (s.name, a.name) having count(a.name) >= 2;
/*Обратите внимание, что для имени студента и имени автора для этих столбцов добавлены ALIAS. 
Связано это с тем, что при использовании в качестве запроса представления, в результатах выборки не может быть двух столбцов 
с одинаковым именем, иначе мы получим ошибку выполнения запроса.*/

/*Выполнить запрос с помощью данного представления можно следующим образом: */
select * from show_students_with_2_or_more_books;

/*Изменить представление можно с помощью ALTER VIEW. 
Например, чтобы переименовать представление, можно использовать следующую строку:
alter view старое_имя rename to новое_имя */

/* С помощью ALTER VIEW можно изменить различные свойства представления. 
При этом выполнить ALTER VIEW может только владелец представления.

Удалить представление можно следующим образом:
drop view имя_представления

DROP VIEW удаляет существующее представление. 
Как и команду ALTER, выполнить DROP VIEW может только владелец представления.*/


