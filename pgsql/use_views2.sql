create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('юзер1');
insert into students (name) values ('юзер2');
insert into students (name) values ('юзер3');
insert into students (name) values ('юзер4');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('aut1');
insert into authors (name) values ('aut2');
insert into authors (name) values ('aut3');
insert into authors (name) values ('aut4');
insert into authors (name) values ('aut5');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('bo1', 1);
insert into books (name, author_id) values ('bo2', 1);
insert into books (name, author_id) values ('bo3', 2);
insert into books (name, author_id) values ('bo4', 2);
insert into books (name, author_id) values ('bo5', 3);
insert into books (name, author_id) values ('bo6', 3);
insert into books (name, author_id) values ('bo7', 4);
insert into books (name, author_id) values ('bo8', 4);
insert into books (name, author_id) values ('bo9', 5);
insert into books (name, author_id) values ('bo10', 5);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (1, 2);
insert into orders (book_id, student_id) values (1, 3);
insert into orders (book_id, student_id) values (2, 1);
insert into orders (book_id, student_id) values (2, 3);
insert into orders (book_id, student_id) values (2, 4);
insert into orders (book_id, student_id) values (3, 2);
insert into orders (book_id, student_id) values (3, 3);
insert into orders (book_id, student_id) values (3, 4);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (4, 3);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (6, 3);
insert into orders (book_id, student_id) values (6, 4);
insert into orders (book_id, student_id) values (7, 1);
insert into orders (book_id, student_id) values (7, 2);
insert into orders (book_id, student_id) values (7, 3);
insert into orders (book_id, student_id) values (8, 1);
insert into orders (book_id, student_id) values (8, 2);
insert into orders (book_id, student_id) values (9, 2);
insert into orders (book_id, student_id) values (10, 1);
insert into orders (book_id, student_id) values (10, 2);
insert into orders (book_id, student_id) values (10, 3);
insert into orders (book_id, student_id) values (10, 4);

/*
 Запрос, в котором определим список студентов, у которых находятся книги с названием содержащие "o1". 
 При этом в результирующей таблице должно быть отражено имя студента, имя автора и название этой книги. 
 Отсортировать по имени студентов в обратном порядке */
select s.name AS юзер, a.name AS автор_книги, b.name AS название_книги
from students as s
    join orders o on s.id = o.student_id
    join books b on (o.book_id = b.id)
    join authors a on b.author_id = a.id
	where b.name LIKE '%o1'
	order by s.name DESC
;

/* создаем представление запроса: */
create view show_students_with_books_with_01 AS
	select s.name AS юзер, a.name AS автор_книги, b.name AS название_книги
	from students as s
		join orders o on s.id = o.student_id
		join books b on (o.book_id = b.id)
		join authors a on b.author_id = a.id
		where b.name LIKE '%o1'
		order by s.name DESC
;

/* вызываем созданное представление: */
select *
from show_students_with_books_with_01
;
