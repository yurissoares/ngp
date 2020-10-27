create table tb_user (
  user_id bigint unsigned not null auto_increment,
  nome varchar(80) not null,
  email varchar(50) not null,
  senha TEXT not null,
  
  primary key(user_id)
);