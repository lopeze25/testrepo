DROP TABLE IF EXISTS order_equipment;
DROP TABLE IF EXISTS completed;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS materials;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS procedures;
DROP TABLE IF EXISTS worklog;


CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(45) NOT NULL,
  email varchar(40),
  phone varchar(20) NOT NULL,
  PRIMARY KEY (customer_pk)
);

CREATE TABLE materials (
  material_pk int unsigned NOT NULL AUTO_INCREMENT,
  material_id varchar(45) NOT NULL,
  specifications varchar(40) NOT NULL, 
  composition varchar(40) NOT NULL,
  price decimal(9,2) NOT NULL,
  quantity int unsigned NOT NULL,
  PRIMARY KEY (material_pk)
);

CREATE TABLE equipment (
  equipment_pk int unsigned NOT NULL AUTO_INCREMENT,
  equipment_id varchar(40) NOT NULL,
  equipment_type varchar (40) NOT NULL,
  quantity int unsigned NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (equipment_pk),
  UNIQUE KEY (equipment_id, equipment_type)
);

CREATE TABLE procedures (
  procedure_pk int unsigned NOT NULL AUTO_INCREMENT,
  procedure_id varchar(40) NOT NULL,
  PRIMARY KEY (procedure_pk)
);

CREATE TABLE worklog (
  worklog_pk int unsigned NOT NULL AUTO_INCREMENT,
  worklog_id varchar(40) NOT NULL,
  name varchar(45) NOT NULL, 
  start_time datetime NOT NULL,
  end_time datetime NOT NULL,
  PRIMARY KEY (worklog_pk)
);

CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  material_fk int unsigned NOT NULL,
  procedure_fk int unsigned NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (material_fk) REFERENCES materials (material_pk) ON DELETE CASCADE,
  FOREIGN KEY (procedure_fk) REFERENCES procedures (procedure_pk) ON DELETE CASCADE
);

CREATE TABLE order_equipment (
  order_fk int unsigned NOT NULL,
  equipment_fk int unsigned NOT NULL,
  expended boolean NOT NULL,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE,
  FOREIGN KEY (equipment_fk) REFERENCES equipment (equipment_pk) ON DELETE CASCADE
);

CREATE TABLE completed (
  completed_pk int unsigned NOT NULL AUTO_INCREMENT,
  completed_id varchar(40) NOT NULL,
  worklog_fk int unsigned NOT NULL,
  order_fk int unsigned NOT NULL,
  fulfilled_date datetime,
  PRIMARY KEY (completed_pk),
  FOREIGN KEY (worklog_fk) REFERENCES worklog (worklog_pk) ON DELETE CASCADE,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE
);

CREATE TABLE images (
  image_pk int unsigned NOT NULL AUTO_INCREMENT,
  procedure_fk int unsigned NOT NULL,
  image_id varchar(40) NOT NULL,
  width int NOT NULL,
  height int NOT NULL,
  mime_type enum('image/jpeg', 'image/png'),
  name varchar(256),
  data mediumblob NOT NULL,
  PRIMARY KEY (image_pk),
  FOREIGN KEY (procedure_fk) REFERENCES procedures (procedure_pk)
);