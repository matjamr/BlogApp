drop table user;

create table user (
    id int auto_increment primary key,
    name varchar(40) not null,
    surname varchar(40) not null,
    email varchar(40) not null,
    description varchar(200) default null,
    date_of_account_creation datetime not null,
    date_of_last_login datetime default null

)
