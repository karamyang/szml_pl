create table product_record
(
	id bigint auto_increment
		primary key,
	product_id bigint not null comment '商品id',
	admin_id bigint not null comment '用户id
',
	record int not null comment '操作',
	create_time timestamp null comment '创建时间',
	record_description varchar(255) not null
);

