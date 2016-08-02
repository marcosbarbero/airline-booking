-- -----------------------------------------------------
-- Schema booking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS booking;
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
  UNIQUE INDEX username_UNIQUE (username ASC))
;


-- -----------------------------------------------------
-- Table booking.airport
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.airport (
  iata_code VARCHAR(3) NOT NULL,
  name VARCHAR(150) NOT NULL,
  country_code VARCHAR(2) NOT NULL,
  PRIMARY KEY (iata_code),
  CONSTRAINT fk_airport_country1
    FOREIGN KEY (country_code)
    REFERENCES booking.country (code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.route
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.route (
  id INT NOT NULL AUTO_INCREMENT,
  origin_airport_iata_code VARCHAR(3) NOT NULL,
  dest_airport_iata_code VARCHAR(3) NOT NULL,
  UNIQUE INDEX un_origin_dest(origin_airport_iata_code, dest_airport_iata_code),
  PRIMARY KEY (id),
  CONSTRAINT fk_direction_airport1
    FOREIGN KEY (origin_airport_iata_code)
    REFERENCES booking.airport (iata_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_direction_airport2
    FOREIGN KEY (dest_airport_iata_code)
    REFERENCES booking.airport (iata_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.schedule
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.schedule (
  id INT NOT NULL AUTO_INCREMENT,
  route_id INT NOT NULL,
  departure_time_gmt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  arrival_time_gmt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_schedule_direction1
    FOREIGN KEY (route_id)
    REFERENCES booking.route (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.flight
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.flight (
  id INT NOT NULL AUTO_INCREMENT,
  schedule_id INT NOT NULL,
  code VARCHAR(45) NOT NULL,
  status VARCHAR(10) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_flight_schedule1
    FOREIGN KEY (schedule_id)
    REFERENCES booking.schedule (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.travel_class
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.travel_class (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table booking.aircraft
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.aircraft (
  id INT NOT NULL AUTO_INCREMENT,
  model VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table booking.aircraft_class
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.aircraft_class (
  id INT NOT NULL AUTO_INCREMENT,
  aircraft_id INT NOT NULL,
  travel_class_id INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX un_aicraft_class (aircraft_id, travel_class_id),
  CONSTRAINT fk_travel_class_has_aircraft_travel_class1
    FOREIGN KEY (travel_class_id)
    REFERENCES booking.travel_class (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_travel_class_has_aircraft_aircraft1
    FOREIGN KEY (aircraft_id)
    REFERENCES booking.aircraft (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.flight_class
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.flight_class (
  id INT NOT NULL AUTO_INCREMENT,
  flight_id INT NOT NULL,
  aircraft_class_id INT NOT NULL,
  price_in_cents BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_flight_class_price_flight1
    FOREIGN KEY (flight_id)
    REFERENCES booking.flight (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_flight_class_price_aircraft_class1
    FOREIGN KEY (aircraft_class_id)
    REFERENCES booking.aircraft_class (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.booking
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.booking (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  flight_class_id INT NOT NULL,
  status VARCHAR(10) NOT NULL,
  creation_date TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_booking_customer1
    FOREIGN KEY (customer_id)
    REFERENCES booking.customer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_booking_flight_class_price1
    FOREIGN KEY (flight_class_id)
    REFERENCES booking.flight_class (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table booking.payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS booking.payment (
  id INT NOT NULL AUTO_INCREMENT,
  booking_id INT NOT NULL,
  credit_card_number VARCHAR(255) NOT NULL,
  credit_card_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_payment_booking1
    FOREIGN KEY (booking_id)
    REFERENCES booking.booking (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;