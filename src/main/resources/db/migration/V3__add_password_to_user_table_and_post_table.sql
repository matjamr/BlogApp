alter table USER add column password varchar(20);

create table post (
    id int primary key auto_increment,
    title varchar(40) not null,
    content varchar(200) not null,
    creator_id int not null
)