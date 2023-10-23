create table role_permission
(
	id bigint auto_increment
		primary key,
	role_id bigint null,
	permission_id bigint null
)
charset=utf8mb4;

