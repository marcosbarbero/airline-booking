-- -----------------------------------------------------
-- Initializing data
-- -----------------------------------------------------

-- country
DELETE FROM booking.country;
INSERT INTO booking.country (code, name) VALUES ('BR', 'Brazil');
INSERT INTO booking.country (code, name) VALUES ('US', 'United States');

-- customer
DELETE FROM booking.customer;
INSERT INTO booking.customer (id, username, full_name, phone) VALUES (1, 'user.name', 'Customer Full Name', '+5511111111');