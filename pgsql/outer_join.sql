/*на на результат присоединения таблиц с помощью join влияет:
    Тип join
    Условие соединения
Если не указать тип соединения по умолчанию он будет inner - внутренний.

Еще есть 3 типа внешних соединений:
left outer join - левое внешнее соединение
right outer join - правое внешнее соединение
full outer join - полное внешнее соединение

Как это понять? Например, прописываем соединение таблиц:

select .. from табл1 <тип соединения> join табл2 on <условие>

Внешнее соединение работает по следующему алгоритму: берется исходная таблица и для каждой ее записи находиться запись, 
которая бы удовлетворяла условию. Если она не будет найдена, то по столбцам будут стоять null.
«Внешней» здесь будет таблица, К КОТОРОЙ присоединяют.*/

create table owners(
    id serial primary key,
    name varchar(255)
);

create table devices(
    id serial primary key,
    name varchar(255),
    owner_id int references owners(id)
);

insert into owners(name) values ('Owner 1');
insert into owners(name) values ('Owner 2');
insert into owners(name) values ('Owner 3');

insert into devices(name, owner_id) values ('Device 1', 1);
insert into devices(name, owner_id) values ('Device 2', 2);
insert into devices(name, owner_id) values ('Device 3', 3);
insert into devices(name, owner_id) values ('Device 4', null);
insert into devices(name, owner_id) values ('Device 5', null);
insert into devices(name, owner_id) values ('Device 6', 1);


/* left outer join
В данном случае «внешней» будет таблица devices. 
Следовательно, выбираются записи из таблицы devices и для нее подбираются записи в таблице owners.
Очевидно, что в результате мы получим n записей, где n это число записей в таблице devices, 
т.е. получим записи из таблицы devices, только с присоединенными записями из таблицы owners.  
*/

select *
from devices AS d
left join owners AS o ON d.owner_id = o.id;

/*Информативность данного запроса заключается в том, что мы можем получить устройства, 
у которых нет владельца. Для этого достаточно дополнить запрос фильтром: 
*/
select *
from devices AS d
left join owners AS o ON d.owner_id = o.id
where o.id is null;

/*но есть нюанс.
если написать такой запрос: 
*/
select *
from owners AS o
left join devices AS d ON o.id = d.owner_id;
/*то получим 4 записи, хотя по идее должны были получить 3 записи, т.к. в таблице owner у нас 3 записи, 
но т.к. сейчас для каждой записи from owners подбираются записи из join devices, 
а для владельца с id = 1 подобралось 2 записи из таблицы join devices, 
то в результате мы получили на одну запись больше. 
Т.к. связь между таблицами many-to-one, то предпочтительней первый вариант запроса, 
т.е. выполнять присоединение таблицы, которая one, к таблице, которая many.*/



/* right outer join
Это соединение аналогично левому соединению только выбирается «правая» таблица в качестве внешней. 
«Лево» или «право» различается относительно join. Синтаксически:

select .. from ЛЕВАЯ <тип> join ПРАВАЯ on <условие>

Таким образом, при правом соединении выбираются записи из правой таблицы и для них подбираются записи из левой таблицы, 
которые удовлетворяют условию. Разумеется, при левом соединении наоборот.

Следовательно, следующие ПАРЫ запросов будут работать одинаково, 
отличаться будет возможно только порядок столбцов в результирующей выборке:
*/
select * from devices d left join  owners o  on d.owner_id = o.id;
select * from owners o  right join devices d on d.owner_id = o.id;

select * from owners o  left join  devices d on o.id = d.owner_id;
select * from devices d right join owners o  on d.owner_id = o.id;



/* full join
Данный тип внешнего соединения дает результат левого + правого соединений, т.е. представляет собой комбинацию этих двух соединений. 
Работает он так: выполняется левое соединение, выполняется правое соединение 
и оба результата этих запросов попадают в результирующую выборку. Синтаксис такой:

select .. from табл1 full join табл2 on <условие>
*/
select * 
from devices AS d 
full join owners AS o on d.owner_id = o.id;


/*
cross join
Этот вид join используется редко. Результатом этого запроса является декартово множество, т.е. все пары элементов. 
Например, если в таблице 1 N записей, а в таблице 2 M записей, то мы получим в результате N * M записей. 
Хорошим примером декартова множества является таблица умножения. Синтаксис такой:

select .. from табл1 cross join табл2 ..

ON в этом случае не пишется!

Если напишем такой запрос для наших таблиц, то мы получим для каждого устройства всех возможных его владельцев.
*/
select * 
from devices AS d 
cross join owners AS o;



/*Пример cross join для таблицы умножения. Получить все возможные пары, а также их произведение:
*/
create table numbers(
    num int unique
);

insert into numbers(num) values (1);
insert into numbers(num) values (2);
insert into numbers(num) values (3);

select n1.num AS a, n2.num AS b, (n1.num * n2.num) AS "a*b=" 
from numbers n1 cross join numbers n2;

/* !!!Таким образом, запрос CROSS  JOIN может служить для генерации данных на уровне БД */
