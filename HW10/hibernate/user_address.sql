create table address
(
	id     BIGINT AUTO_INCREMENT
		PRIMARY KEY,
	street VARCHAR(255) NULL
);

create table user
(
	id bigint auto_increment
		primary key,
	age int null,
	name varchar(255) null,
	address_id bigint null
);

create index address_id_index
	on user (address_id)
;

