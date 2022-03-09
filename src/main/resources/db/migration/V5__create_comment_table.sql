create table comment (
  id int primary key auto_increment,
  content varchar(200),
  post_id int,
  foreign key(post_id) references post(id)
);