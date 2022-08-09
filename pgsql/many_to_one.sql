create table genre_cinema(
    id serial primary key,
    name_genre varchar(255)
);

create table actor(
    id serial primary key,
    name_actor varchar(255),
    position_id int references genre_cinema(id)
);

insert into genre_cinema(name_genre) values ('comedy');
insert into actor(name_actor, position_id) VALUES ('­ЁЄг«Ё­', 1);

select * from actor;

select * from genre_cinema where id in (select id from actor);