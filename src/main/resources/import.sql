--
-- Sample dataset.
--

-- =================================================================================================
-- Car manufacturers
insert into car_manufacturer(name) values ('BWM');
insert into car_manufacturer(name) values ('Mercedes');
insert into car_manufacturer(name) values ('Audi');

-- =================================================================================================
-- Car manufacturers models

insert into car_model(name, manufacturer_id) values ('116', 1);
insert into car_model(name, manufacturer_id) values ('325', 1);
insert into car_model(name, manufacturer_id) values ('523', 1);

insert into car_model(name, manufacturer_id) values ('124', 2);
insert into car_model(name, manufacturer_id) values ('221', 2);
insert into car_model(name, manufacturer_id) values ('140', 2);

insert into car_model(name, manufacturer_id) values ('A6', 3);
insert into car_model(name, manufacturer_id) values ('A8', 3);
insert into car_model(name, manufacturer_id) values ('S4', 3);

-- =================================================================================================
-- Car's body types

insert into body_type(name) values ('седан');
insert into body_type(name) values ('универсал');
insert into body_type(name) values ('хетчбэк');
insert into body_type(name) values ('минивэн');
insert into body_type(name) values ('внедорожник');
insert into body_type(name) values ('купе');
insert into body_type(name) values ('кабриолет');
insert into body_type(name) values ('микроавтобус');
insert into body_type(name) values ('фургон');

-- =================================================================================================
-- Car's engine types

insert into engine_type(name) values ('бензин');
insert into engine_type(name) values ('дизель');
insert into engine_type(name) values ('газ (бензин)');
insert into engine_type(name) values ('гибрид (бензин)');
insert into engine_type(name) values ('гибрид (дизель)');
insert into engine_type(name) values ('электромобиль');

-- =================================================================================================
-- Locations

insert into location(name) values ('Брестская обл.');
insert into location(name) values ('Витебская обл.');
insert into location(name) values ('Гомельская обл.');
insert into location(name) values ('Гродненская обл.');
insert into location(name) values ('Минская обл.');

-- =================================================================================================
-- Cars

insert into car(created_date, color, drive, seller_type, transmission, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2000-01-01 00:00:00', 'красный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№617686', '2000-01-01', 1, 1, 1, 2);
insert into car(created_date, color, drive, seller_type, transmission, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2000-01-01 00:00:00', 'матовый черный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№127686', '2000-01-01', 1, 1, 1, 2);
insert into car(created_date, color, drive, seller_type, transmission, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2000-01-01 00:00:00', 'голубой металлик', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614546', '2000-01-01', 1, 1, 1, 2);
insert into car(created_date, color, drive, seller_type, transmission, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2000-01-01 00:00:00', 'синий', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2000-01-01', 1, 1, 1, 2);

-- =================================================================================================
-- Users

insert into user(name) values ('Вася');
insert into user(name) values ('Петя');
insert into user(name) values ('Физрук');

-- =================================================================================================
-- Subscriptions

insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission, drive, location_id, seller_type) values ('Подписка 1', 1, '2000-01-01', '2005-01-01', 100000, 200000, 10000, 15000, 1, 1, 1, null, null, 1, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission, drive, location_id, seller_type) values ('Подписка 2', 2, '1999-01-01', '2002-01-01', 100000, 400000, 5000, 10000, 7, 1, 1, null, null, 2, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission, drive, location_id, seller_type) values ('Подписка 3', 2, '2005-01-01', '2010-01-01', null, null, null, null, 8, null, null, null, null, 1, null);

