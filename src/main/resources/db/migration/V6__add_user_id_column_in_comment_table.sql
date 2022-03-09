alter table COMMENT add column user_id int not null;
alter table COMMENT add foreign key(user_id) references user(id);