-- Customers
INSERT INTO customers (customer_id, email, phone) VALUES('ATTAWAY_HECKTOR', 'example@mail.com', '755.223.5969');
INSERT INTO customers (customer_id, email, phone) VALUES('MAYNARD_TORBJORG', 'example@mail.com', '256.399.4665');
INSERT INTO customers (customer_id, email, phone) VALUES('MORISON_LINA', 'example@mail.com', '635.968.2611');
INSERT INTO customers (customer_id, email, phone) VALUES('VAN_ALTENA_AGNI', NULL, '630.333.3333');
INSERT INTO customers (customer_id, email, phone) VALUES('KAPPEL_TERZO', 'example@mail.com', '328.993.3774');
INSERT INTO customers (customer_id, email, phone) VALUES('FOSTER_BUSINGE', 'example@mail.com', '399.377.3854');
INSERT INTO customers (customer_id, email, phone) VALUES('IGNATOV_GISELLA', NULL, '630.333.4444');
INSERT INTO customers (customer_id, email, phone) VALUES('STERN_TORO', 'example@mail.com', '647.399.2610');
INSERT INTO customers (customer_id, email, phone) VALUES('HUANG_KESTAS', 'example@mail.com', '219.355.6407');
INSERT INTO customers (customer_id, email, phone) VALUES('OTXOA_GISBERT', 'example@mail.com', '766.388.3663');

-- Materials
INSERT INTO materials (material_id, specifications, composition, quantity, price) VALUES ('Steel Sheet', '1/4 inch sheet', 'Carbon steel', 5, 100.00);
INSERT INTO materials (material_id, specifications, composition, quantity, price) VALUES ('Aluminum Sheet', '1/8 inch sheet', '6061 alloy', 10, 80.55);
INSERT INTO materials (material_id, specifications, composition, quantity, price) VALUES ('Stainless Steel Pipe', '1/2 inch tube', '304 grade', 3, 150.00);

-- Equipment
INSERT INTO equipment (equipment_id, equipment_type, quantity, price) VALUES('Welding Machine', 'Lincoln Power MIG 255', 1, 400.00);
INSERT INTO equipment (equipment_id, equipment_type, quantity, price) VALUES('Weldingrod', 'E6013', 3, 10.55);
INSERT INTO equipment (equipment_id, equipment_type, quantity, price) VALUES('Weldingrod', 'E7018', 2, 15.00);
INSERT INTO equipment (equipment_id, equipment_type, quantity, price) VALUES('Wire', 'Steel', 1, 20.00);
INSERT INTO equipment (equipment_id, equipment_type, quantity, price) VALUES('Electric Generator', 'Generac GP3500iO', 1, 800.00);

-- Procedures
INSERT INTO procedures (procedure_id) VALUES ('MIG');
INSERT INTO procedures (procedure_id) VALUES ('TIG');
INSERT INTO procedures (procedure_id) VALUES ('Stick');
INSERT INTO procedures (procedure_id) VALUES ('Flux Core');

-- Orders
INSERT INTO orders (customer_fk, material_fk, procedure_fk) VALUES (3, 2, 1);
INSERT INTO orders (customer_fk, material_fk, procedure_fk) VALUES (1, 2, 1);

-- Order_Equipment
INSERT INTO order_equipment (order_fk, equipment_fk, expended) VALUES (1, 1, 0);
INSERT INTO order_equipment (order_fk, equipment_fk, expended) VALUES (1, 2, 1);
INSERT INTO order_equipment (order_fk, equipment_fk, expended) VALUES (2, 3, 0);

-- Worklogs
INSERT INTO worklog (worklog_id, name, start_time, end_time)
VALUES ('LOG1', 'Owner', '2022-02-14 10:00:00', '2022-02-14 12:00:00');
INSERT INTO worklog (worklog_id, name, start_time, end_time)
VALUES ('LOG2', 'Owner', '2022-02-14 12:00:00', '2022-02-14 14:00:00');
INSERT INTO worklog (worklog_id, name, start_time, end_time)
VALUES ('LOG3', 'Owner', '2022-02-14 14:00:00', '2022-02-14 16:00:00');

-- Completed Work Orders
INSERT INTO completed (completed_id, worklog_fk, order_fk, fulfilled_date) VALUES
('COM1', 1, 1, '2022-02-15 10:00:00');
INSERT INTO completed (completed_id, worklog_fk, order_fk, fulfilled_date) VALUES
('COM2', 2, 1, '2022-02-15 11:00:00');
INSERT INTO completed (completed_id, worklog_fk, order_fk, fulfilled_date) VALUES
('COM3', 3, 2, '2022-02-15 12:00:00');