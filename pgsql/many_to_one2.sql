/*Допустим у нас есть музыкальные композиции и авторы. 
Эти таблицы описываются связью many-to-one. 
Автор может иметь много записей.*/

create table author ( --таблица автора
    id serial primary key,
    name text
);

create table compose ( --таблица композиций
    id serial primary key,
    name text,
    author_id int references author(id) -- эта строчка создает связь между таблицами author и compose. В данном случае связь указана many-to-one.
);

/*Если мы захотим вставить данные в таблицу compose нам нужно сначала взять данные из таблицы author. 
references - указывает на ограничение, что данное поле должно быть в таблице author.
В этом примере связь реализуется сразу в таблице.*/