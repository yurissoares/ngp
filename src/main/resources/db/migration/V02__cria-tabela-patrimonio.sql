create table tb_patrimonio (
	patrimonio_id bigint unsigned not null auto_increment,
  	nome varchar(80) not null,
  	marca_id bigint unsigned not null,
  	descricao varchar(240),
  	num_tombo varchar(100),

  	primary key(patrimonio_id)
);

alter table tb_patrimonio add constraint fk_tb_patrimonio_tb_marca
foreign key (marca_id) references tb_marca (marca_id);

