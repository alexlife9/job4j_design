/*допустим у нас есть таблица меломанов.*/

create table music_lover (
    id serial primary key,
    name text
);

/* чтобы добавить ограничения и связать таблицы music_lover и compose, применяем связь many-to-many
Для этого нам нужно добавить вспомогательную таблицу - music_lover_compose*/
create table music_lover_compose (
    id serial primary key,
    music_lovel_id int references music_lover(id),
    compose_id int references compose(id)
);