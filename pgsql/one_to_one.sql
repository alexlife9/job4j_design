create table drivers_license(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table drivers_license_people(
    id serial primary key,
    drivers_license_id int references drivers_license(id) unique,
    people_id int references people(id) unique
);