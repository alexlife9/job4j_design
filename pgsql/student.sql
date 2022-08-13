create table students(
    id serial primary key, 
	name text
);

create table subjects(
    id serial primary key, 
	name text
);

create table students_subjects(
    id serial primary key, 
    mark float,                          --оценка студента
    student_id int references students(id), 
    subject_id int references subjects(id)
);

insert into students(name) values ('Аня'), ('Ваня'), ('Боря');
insert into subjects(name) values ('Математика'), ('Русский'), ('Информатика');
insert into students_subjects(student_id, subject_id, mark) values (1, 1, 5), (1, 2, 5), (1, 3, 5);
insert into students_subjects(student_id, subject_id, mark) values (2, 1, 5), (2, 2, 4), (2, 3, 4);
insert into students_subjects(student_id, subject_id, mark) values (3, 1, 3), (3, 2, 5), (3, 3, 3);

select avg(mark) from students_subjects; --какой средний балл среди всех студентов
select min(mark) from students_subjects; --какой минимальный среди всех балл студентов
select max(mark) from students_subjects; --какой максимальный среди всех балл студентов

select s.name, avg(ss.mark) --какой средний балл всех студентов по отдельным предметам
from students_subjects as ss 
join subjects s 
on ss.subject_id = s.id
group by s.name;

select s.name, avg(ss.mark)  --какой средний балл каждого студента по всем предметам
from students_subjects as ss 
join students s 
on ss.student_id = s.id 
group by s.name;

/*Ранее мы рассмотрели метод where. Однако, у него есть одно ограничение – его нельзя использовать с агрегатными функциями. 
Для этих целей специально служит оператор having. 
Применяется он аналогично where, только в нем обязательно должна быть агрегатная функция.*/
select s.name, avg(ss.mark)  --ищем предмет, по которому ученики набрали более 4.2 баллов
from students_subjects as ss 
join subjects s 
on ss.subject_id = s.id
group by s.name
having avg(ss.mark) > 4.2;