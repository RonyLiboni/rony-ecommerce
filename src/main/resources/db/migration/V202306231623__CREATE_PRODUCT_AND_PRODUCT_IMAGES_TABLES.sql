CREATE TABLE IF NOT EXISTS products (
  id BIGINT NOT NULL AUTO_INCREMENT,
  sku VARCHAR(36) NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  price DECIMAL(8,2) NOT NULL,  
  `description` VARCHAR(255) NOT NULL,
  category_id BIGINT NOT NULL,
  CONSTRAINT product_pk PRIMARY KEY (id),
  CONSTRAINT product_sku_unique UNIQUE (sku),
  CONSTRAINT product_category_id_fk FOREIGN KEY (category_id) REFERENCES categories (id),
  INDEX product_category_id_index (category_id)
);

CREATE TABLE IF NOT EXISTS product_images (
  product_id BIGINT NOT NULL,
  url VARCHAR(255) NOT NULL,
  `key` VARCHAR(255) NOT NULL,
  image_order TINYINT NOT NULL DEFAULT 10,
  CONSTRAINT product_image_product_id_fk FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT product_image_key_unique UNIQUE (`key`),
  INDEX product_image_product_id_index (product_id)
);