create table product_category
(
	id bigint auto_increment comment '主键id'
		primary key,
	category_name varchar(255) not null comment '分类名称',
	parent_id bigint null comment '父节点',
	category_level int not null comment '分类级别',
	status int not null comment '状态',
	img_url varchar(255) null comment '图片',
	leaf int not null comment '树子节点',
	priority int not null comment '优先级',
	create_time timestamp null comment '创建时间',
	update_time timestamp null comment '更新时间'
);

