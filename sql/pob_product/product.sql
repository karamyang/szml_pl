create table product
(
	id bigint not null comment '商品Id'
		primary key,
	product_name varchar(255) not null comment '商品名称',
	type varchar(255) not null comment '商品类型',
	category_id bigint not null comment '商品分类',
	right_id varchar(255) not null comment '权益Id',
	exchange_type varchar(255) not null comment '兑换类型',
	cash decimal(12,2) null comment '现金兑换价格',
	integral bigint null comment '积分兑换价格',
	mixed varchar(255) null comment '混合兑换价格',
	stock bigint not null comment '商品库存',
	restriction bigint null comment '限制次数',
	img_url varchar(255) null comment '商品图片',
	description varchar(255) not null comment '商品描述',
	detail varchar(255) not null comment '商品细节',
	black_list_id varchar(255) null comment '城市黑名单',
	white_list_id varchar(255) null comment '城市白名单',
	online_time timestamp null on update CURRENT_TIMESTAMP comment '上线时间',
	line_time timestamp null on update CURRENT_TIMESTAMP comment '下线时间',
	status int not null comment '商品状态',
	create_user_id bigint not null comment '商品创建者',
	manage_user_id bigint not null comment '商品管理者',
	not_shipments varchar(255) null comment '不发货地区',
	create_time timestamp null comment '创建时间',
	update_time timestamp null comment '修改时间',
	constraint name
		unique (product_name)
)
charset=utf8mb4;

