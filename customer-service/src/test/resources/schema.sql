
-- -----------------------------------------------------
-- Schema booking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS booking;
USE booking ;

-- -----------------------------------------------------
-- Table booking.country
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.country (
  code VARCHAR(2) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (code))
;


-- -----------------------------------------------------
-- Table booking.customer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.customer (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  phone VARCHAR(45),
  PRIMARY KEY (id),
  UNIQUE INDEX username_UNIQUE (username ASC)
);