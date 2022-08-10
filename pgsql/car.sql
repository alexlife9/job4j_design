create table truck(
	id serial primary key,
	model VARCHAR(20),
    color VARCHAR(20),
    price DECIMAL(8, 1),
    year int
);

insert into track(model, color, price, year) values(MAN, black, 500.8, 2020);

update truck set year = '2007';

delete from truck;