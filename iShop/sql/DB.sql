CREATE TABLE hr_test.t_group (
	GROUP_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	GROUP_NAME VARCHAR(100) NOT NULL
);

CREATE TABLE hr_test.t_product (
	PRODUCT_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	PRODUCT_NAME VARCHAR(100) NOT NULL,
	GROUP_ID INT NOT NULL,
	PRODUCT_PRICE DOUBLE,
	FOREIGN KEY (GROUP_ID) 
		REFERENCES t_group(GROUP_ID)
		ON DELETE CASCADE
);

insert into hr_test.t_group (group_id, group_name)
values (1, 'Phones');

insert into hr_test.t_group (group_id, group_name)
values (2, 'MP3');

insert into hr_test.t_group (group_id, group_name)
values (3, 'GPS');

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (1, 'Samsung Galaxy S4', 1, 5000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (2, 'iPhone', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (3, 'HTC one', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (4, 'Garmin NUVI', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (5, 'Palmann 43B', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (6, 'Nokia', 1, 5000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (7, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (8, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (9, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (10, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (11, 'Nokia', 1, 5000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (12, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (13, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (14, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (15, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (16, 'Nokia', 1, 5000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (17, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (18, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (19, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (20, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (21, 'Nokia', 1, 5000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (22, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (23, 'Nokia', 1, 7000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (24, 'Nokia', 3, 3000);

insert into hr_test.t_product (product_id, product_name, group_id, product_price)
values (25, 'Nokia', 3, 3000);