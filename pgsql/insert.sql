insert into role(name) values('admin');
insert into role(name) values('user1');
insert into role(name) values('user2');

insert into users(name, role_id) values('alex', 1);
insert into users(name, role_id) values('roma', 2);
insert into users(name, role_id) values('petr', 3);

insert into rules(name) values('full');
insert into rules(name) values('no_full');

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (3, 1);

insert into status(name) values ('work');
insert into status(name) values ('done');

insert into category_item(name) values ('high');
insert into category_item(name) values ('medium');
insert into category_item(name) values ('low');

insert into item(name, users_id, category_item_id, status_id)
                values('first_item', 1, 2, 1);
insert into item(name, users_id, category_item_id, status_id)
                values('two_item', 1, 3, 1);	

insert into comments_item(name, item_id) 
            values ('why so long???', 2);
insert into comments_item(name, item_id) 
            values ('not much left', 1);	
			
insert into attach_files(name, item_id) values('files.txt', 1);

		
		
		
		
		
		
		 