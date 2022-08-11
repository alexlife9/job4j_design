select * from students;  -- извлекает все поля
select name, course, speciality from students; --извлекает указанные поля

/*Для фильтрации используется оператор where*/

/*Операторы = и != служат для проверки на равенство*/
select * from students where course = 2; -- получим студентов 2-го курса
select * from students where course != 4; -- получим студентов всех курсов кроме 2-го

/*Для проверки на null не используют = или !=. Для этого есть конструкция is null или is not null*/
select * from students where name is null; 
select * from students where name is not null;

/*Операторы <, >, <=, >= можно использовать для сравнения чисел и дат*/
select * from students where enroll_date > '01.09.2018'; -- получим поступивших после указанной даты
select * from students where course >= 3; -- получим студентов старше 2 курса

/*Оператор сравнения строк LIKE
Если нужно проверить просто равенство строки, то можно использовать =
Если нужно проверить, что строка начинается или кончается с определенного префикса/суффикса, 
то можно использовать оператор LIKE.
.. LIKE '%abc' – строка должна кончаться с 'abc'
.. LIKE 'abc%' – строка должна начинаться с 'abc'
.. LIKE '%abc%' – строка должна содержать 'abc'
*/
select * from students where name like 'D%'; --строка в колонке name начинается с D

/*Логические операторы AND, OR
В Java для объединения логических условий мы используем && и ||
В SQL используется и является их аналогом AND и OR*/
select * from students where name like 'D%' and course > 2; -- начинается на D И курс больше второго
select * from students where name like 'D%' or course > 2;  -- начинается на D ИЛИ курс больше второго

/*Работа с датами
Часто бывает нужным проверить на какое-то условие дату. Для работы с датами есть свой синтаксис.*/
select current_date; -- получение текущей даты
select current_date > '10.09.2020'; -- проверка, что дата позже 10.09.2020
select current_date + interval '1 month'; -- прибавление какой-то единицы времени (day, week, month, year, hour) 

/*Упорядочивание выборки ORDER BY
Данный оператор нужен для того, чтобы выборка была упорядочена. Мы пишем
.. order by поле ASC|DESC ..
ASC – ascending (по возрастанию), DESC – descending (по убыванию)*/
select * from students order by speciality asc; --сортируем таблицу студентов по колонке speciality по возрастанию
select * from students order by speciality desc; 

/*Выборка уникальных элементов SELECT DISTINCT
Если нам нужно получить только уникальные элементы, то нужно указать distinct после select.*/
select distinct course from students; --показать только уникальные значения колонки course в таблице студентов

/*Выборка определенного числа элементов LIMIT
Данный метод работает аналогично методу limit() в стримах. Оставляет указанное ему число строк.*/
select * from students limit 5;
