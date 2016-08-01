-- -----------------------------------------------------
-- Initializing data
-- -----------------------------------------------------

-- country
INSERT INTO booking.country (code, name) VALUES ('BR', 'Brazil');
INSERT INTO booking.country (code, name) VALUES ('US', 'United States');

-- customer
INSERT INTO booking.customer (id, country_code, username, full_name, phone) VALUES (1, 'BR', 'user.name', 'Customer Full Name', '+5511111111');