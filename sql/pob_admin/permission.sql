create table permission
(
	id bigint auto_increment
		primary key,
	value varchar(255) not null,
	uri varchar(255) not null,
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	permission_name varchar(255) not null,
	update_time timestamp null
)
charset=utf8mb4;

