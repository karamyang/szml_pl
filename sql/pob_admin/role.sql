create table role
(
	id bigint auto_increment
		primary key,
	name varchar(255) not null,
	description varchar(255) null,
	create_time datetime not null,
	update_time datetime null
)
charset=utf8mb4;

