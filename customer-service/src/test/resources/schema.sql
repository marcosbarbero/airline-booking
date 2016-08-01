
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
  country_code VARCHAR(2) NOT NULL,
  username VARCHAR(100) NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username_UNIQUE (username ASC),
  CONSTRAINT fk_customer_country1
    FOREIGN KEY (country_code)
    REFERENCES booking.country (code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;