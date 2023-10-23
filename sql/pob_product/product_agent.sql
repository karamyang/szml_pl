create table product_agent
(
	admin_id bigint not null,
	product_id bigint not null,
	username varchar(255) not null,
	create_time timestamp null,
	update_time timestamp null,
	primary key (admin_id, product_id)
);

