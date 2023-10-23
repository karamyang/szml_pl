create table admin_role
(
	id bigint auto_increment
		primary key,
	admin_id bigint not null,
	role_id bigint not null
)
charset=utf8mb4;

