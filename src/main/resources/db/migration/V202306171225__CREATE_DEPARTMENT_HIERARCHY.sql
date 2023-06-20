CREATE TABLE IF NOT EXISTS departments (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  CONSTRAINT department_pk PRIMARY KEY (id),
  CONSTRAINT department_name_unique UNIQUE (`name`)
);

CREATE TABLE IF NOT EXISTS sub_departments (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  department_id BIGINT NOT NULL, 
  CONSTRAINT sub_department_pk PRIMARY KEY (id),
  CONSTRAINT sub_department_department_id_fk FOREIGN KEY (department_id) REFERENCES departments (id),
  CONSTRAINT sub_department_name_unique UNIQUE (`name`)
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  sub_department_id BIGINT NOT NULL,
  CONSTRAINT product_category_pk PRIMARY KEY (id),
  CONSTRAINT product_categories_sub_department_id_fk FOREIGN KEY (sub_department_id) REFERENCES sub_departments (id),
  CONSTRAINT product_categories_name_unique UNIQUE (`name`)
);