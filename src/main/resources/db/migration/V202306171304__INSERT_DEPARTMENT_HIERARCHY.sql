
INSERT INTO departments(`name`) VALUES ('Games');

INSERT INTO sub_departments (`name`, department_id) SELECT 'Playstation', id FROM departments WHERE `name` = 'Games';
INSERT INTO sub_departments (`name`, department_id) SELECT 'Xbox', id FROM departments WHERE `name` = 'Games';
INSERT INTO sub_departments (`name`, department_id) SELECT 'Nintendo', id FROM departments WHERE `name` = 'Games';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Acessories for Playstation', id FROM sub_departments WHERE `name` = 'Playstation';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Acessories for Xbox', id FROM sub_departments WHERE `name` = 'Xbox';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Acessories for Nintendo', id FROM sub_departments WHERE `name` = 'Nintendo';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Games for Playstation', id FROM sub_departments WHERE `name` = 'Playstation';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Games for Xbox', id FROM sub_departments WHERE `name` = 'Xbox';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Games for Nintendo', id FROM sub_departments WHERE `name` = 'Nintendo';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Controllers for Playstation', id FROM sub_departments WHERE `name` = 'Playstation';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Controllers for Xbox', id FROM sub_departments WHERE `name` = 'Xbox';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Controllers for Nintendo', id FROM sub_departments WHERE `name` = 'Nintendo';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Playstation Consoles', id FROM sub_departments WHERE `name` = 'Playstation';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Xbox Consoles', id FROM sub_departments WHERE `name` = 'Xbox';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Nintendo Consoles', id FROM sub_departments WHERE `name` = 'Nintendo';


INSERT INTO departments(`name`) VALUES ('Computers');

INSERT INTO sub_departments (`name`, department_id) SELECT 'Laptops', id FROM departments WHERE `name` = 'Computers';
INSERT INTO sub_departments (`name`, department_id) SELECT 'Monitors', id FROM departments WHERE `name` = 'Computers';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Acessories', id FROM sub_departments WHERE `name` = 'Laptops';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Smart Monitors', id FROM sub_departments WHERE `name` = 'Monitors';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Dell', id FROM sub_departments WHERE `name` = 'Laptops';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Gamer Monitors', id FROM sub_departments WHERE `name` = 'Monitors';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Acer', id FROM sub_departments WHERE `name` = 'Laptops';
INSERT INTO categories(`name`, sub_department_id) SELECT 'Office Monitors', id FROM sub_departments WHERE `name` = 'Monitors';

INSERT INTO categories(`name`, sub_department_id) SELECT 'Macbook', id FROM sub_departments WHERE `name` = 'Laptops';