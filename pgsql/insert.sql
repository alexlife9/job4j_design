insert into roles(name) values('admin');
insert into roles(name) values('user1');
insert into roles(name) values('user2');

insert into users(name, role_id) values('alex', 1);
insert into users(name, role_id) values('roma', 2);
insert into users(name, role_id) values('petr', 3);

insert into role_rights(role_id) values(1);
insert into role_rights(role_id) values(2);
insert into role_rights(role_id) values(3);
insert into role_rights(role_id) values(4);
insert into role_rights(role_id) values(5);

insert into status(complete) values('at_work');
insert into status(complete) values('done');

insert into category_item(name) values('urgent');
insert into category_item(name) values('unimportantly');

insert into item(name, users_id, category_id, status_id) values('first_item', 1, 2, 1);

insert into comments_item(name, item_id) values('why so long???', 1);

insert into attach_files(name, item_id) values('files.txt', 1);