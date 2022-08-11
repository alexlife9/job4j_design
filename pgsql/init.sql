create table role(					--роли
	id serial primary key,
	name varchar(255)
);

create table users( 				--пользователи
	id serial primary key,
	name varchar(255),
	role_id int references role(id) --связь many-to-one -> много ролей может иметь один юзер
);

create table rules( 			--права(условия) ролей
	id serial primary key,
	name varchar(255)
);

create table role_rules(      --вспомогательная таблица для связи по схеме many-to-many
	id serial primary key,
	role_id int references role(id), --связь many-to-many -> роли имеют много условий
	rules_id int references rules(id)            --И условия имеют много ролей
);

create table status( 			   	--Состояние заявки
	id serial primary key,
	name varchar(255)
);

create table category_item(			 --категория заявки
	id serial primary key,
	name text
);

create table item( 				--заявка
	id serial primary key,
	name text,
	users_id int references users(id),       --связь many-to-one -> много юзеров могут работать с этой заявкой
	category_item_id int references category_item(id), --связь many-to-one -> эту заявку можно соотнести к разным категориям
	status_id int references status(id)        --связь many-to-one -> этой заявке можно присвоить много статусов
);

create table comments_item( 		-- комментарии заявок
	id serial primary key,
	name text,
	item_id int references item(id) --связь one-to-many -> этот коммент можно дать многим заявкам 
);

create table attach_files(			 --приложенные Файлы
	id serial primary key,
	name varchar(255),
	item_id int references item(id) --связь one-to-many -> этот один файл можно приложить к многим заявкам
); 