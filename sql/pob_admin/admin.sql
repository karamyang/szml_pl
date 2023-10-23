create table admin
(
	id bigint auto_increment
		primary key,
	username varchar(255) not null,
	password varchar(255) not null,
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	update_time timestamp null,
	telephone varchar(255) not null,
	email varchar(255) not null
)
charset=utf8mb4;

