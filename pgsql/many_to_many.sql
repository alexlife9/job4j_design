create table cinema(
    id serial primary key,
    name_cinema varchar(255)
);

create table actor_action(
    id serial primary key,
    name_actor varchar(255)
);

 create table actor_cinema(
     id serial primary key,
     cinema_id int references cinema(id),
     actor_id int references actor_action(id)
 );