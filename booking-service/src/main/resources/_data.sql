-- -----------------------------------------------------
-- Initializing data
-- -----------------------------------------------------

-- country
INSERT INTO booking.country (code, name) VALUES ('BR', 'Brazil');
INSERT INTO booking.country (code, name) VALUES ('US', 'United States');

-- airport
INSERT INTO booking.airport(iata_code, name, country_code) VALUES ('GRU', 'São Paulo–Guarulhos International Airport', 'BR');
INSERT INTO booking.airport(iata_code, name, country_code) VALUES ('GIG', 'Rio de Janeiro–Galeão International Airport', 'BR');

INSERT INTO booking.airport(iata_code, name, country_code) VALUES ('GFL', 'Floyd Bennett Memorial Airport', 'US');
INSERT INTO booking.airport(iata_code, name, country_code) VALUES ('ALB', '	Albany International Airport', 'US');

-- route
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (1,'GRU','GIG');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (2,'GRU','GFL');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (3,'GRU','ALB');

INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (4,'GIG','GRU');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (5,'GIG','GFL');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (6,'GIG','ALB');

INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (7,'GFL','GRU');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (8,'GFL','GIG');

INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (9,'ALB','GRU');
INSERT INTO booking.route(id, origin_airport_iata_code, dest_airport_iata_code) VALUES (10,'ALB','GIG');

-- schedule
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (1, 1, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (2, 1, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (3, 2, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (4, 2, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (5, 3, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (6, 3, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (7, 4, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (8, 4, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (9, 5, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (10, 5, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (11, 6, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (12, 6, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (13, 7, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (14, 7, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (15, 8, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (16, 8, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (17, 9, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (18, 9, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (19, 10, timestampadd(hour, 1, current_timestamp()), timestampadd(hour, 5, current_timestamp()));
INSERT INTO booking.schedule(id, route_id, departure_time_gmt, arrival_time_gmt) VALUES (20, 10, timestampadd(hour, 2, current_timestamp()), timestampadd(hour, 5, current_timestamp()));

-- flight
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (1, 1, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (2, 2, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (3, 3, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (4, 4, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (5, 5, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (6, 6, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (7, 7, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (8, 8, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (9, 9, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (10, 10, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (11, 11, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (12, 12, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (13, 13, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (14, 14, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (15, 15, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (16, 16, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (17, 17, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (18, 18, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (19, 19, left(random_uuid(), 8), 'ACTIVE');
INSERT INTO booking.flight (id, schedule_id, code, status) VALUES (20, 20, left(random_uuid(), 8), 'ACTIVE');

-- aircraft
INSERT INTO booking.aircraft(id, model) VALUES (1, 'Boeing 737');
INSERT INTO booking.aircraft(id, model) VALUES (2, 'Boeing 747');
INSERT INTO booking.aircraft(id, model) VALUES (3, 'Boeing 767');
INSERT INTO booking.aircraft(id, model) VALUES (4, 'Boeing 777');
INSERT INTO booking.aircraft(id, model) VALUES (5, 'Boeing 787');

-- travel_class
INSERT INTO booking.travel_class(id, name) VALUES(1, 'First class');
INSERT INTO booking.travel_class(id, name) VALUES(2, 'Business class');
INSERT INTO booking.travel_class(id, name) VALUES(3, 'Economy class');

-- aircraft_class
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (1, 1, 1);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (2, 1, 2);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (3, 1, 3);

INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (4, 2, 1);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (5, 2, 2);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (6, 2, 3);

INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (7, 3, 1);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (8, 3, 2);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (9, 3, 3);

INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (10, 4, 1);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (11, 4, 2);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (12, 4, 3);

INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (13, 5, 1);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (14, 5, 2);
INSERT INTO booking.aircraft_class(id, aircraft_id, travel_class_id) VALUES (15, 5, 3);

-- flight_class
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (1, 1, 1, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (2, 1, 2, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (3, 1, 3, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (4, 2, 4, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (5, 2, 5, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (6, 2, 6, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (7, 3, 7, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (8, 3, 8, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (9, 3, 9, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (10, 4, 10, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (11, 4, 11, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (12, 4, 12, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (13, 5, 13, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (14, 5, 14, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (15, 5, 15, 3500);


INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (16, 6, 1, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (17, 6, 2, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (18, 6, 3, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (19, 7, 4, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (20, 7, 5, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (21, 7, 6, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (22, 8, 7, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (23, 8, 8, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (24, 8, 9, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (25, 9, 10, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (26, 9, 11, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (27, 9, 12, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (28, 10, 13, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (29, 10, 14, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (30, 10, 15, 3500);


INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (31, 11, 1, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (32, 11, 2, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (33, 11, 3, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (34, 12, 4, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (35, 12, 5, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (36, 12, 6, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (37, 13, 7, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (38, 13, 8, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (39, 13, 9, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (40, 14, 10, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (41, 14, 11, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (42, 14, 12, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (43, 15, 13, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (44, 15, 14, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (45, 15, 15, 3500);


INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (46, 16, 1, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (47, 16, 2, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (48, 16, 3, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (49, 17, 4, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (50, 17, 5, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (51, 17, 6, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (52, 18, 7, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (53, 18, 8, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (54, 18, 9, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (55, 19, 10, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (56, 19, 11, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (57, 19, 12, 3500);

INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (58, 20, 13, 5000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (59, 20, 14, 4000);
INSERT INTO booking.flight_class (id, flight_id, aircraft_class_id, price_in_cents) VALUES (60, 20, 15, 3500);