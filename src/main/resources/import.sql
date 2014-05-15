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
-- Drive
insert into drive(name) values ('передний');
insert into drive(name) values ('задний');
insert into drive(name) values ('полный');

-- =================================================================================================
-- Transmission
insert into transmission(name) values ('механика');
insert into transmission(name) values ('автомат');

-- =================================================================================================
-- Seller Type
insert into seller_type(name) values ('владелец');
insert into seller_type(name) values ('компания');

-- =================================================================================================
-- Locations
insert into location(name) values ('Брестская обл.');
insert into location(name) values ('Витебская обл.');
insert into location(name) values ('Гомельская обл.');
insert into location(name) values ('Гродненская обл.');
insert into location(name) values ('Минская обл.');

-- =================================================================================================
-- Cars
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2000-01-01 00:00:00', 'красный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№617686', '2001-01-01', 1, 1, 1, 2);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2001-01-01 00:00:00', 'матовый черный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№127686', '2005-01-01', 1, 1, 1, 2);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2002-01-01 00:00:00', 'голубой металлик', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614546', '1999-01-01', 1, 1, 1, 2);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2003-01-01 00:00:00', 'синий', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 2);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'черный', 1, 1, 1, 1500, 10000, null,'375298216893', 21000, '№614586', '2014-02-01', 3, 2, 2, 3);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'белый', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 1);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'серебристый металлик', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 1);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'черный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 1);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'синий', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 1);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'shadow line', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 3);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'черный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 2);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'белый', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 4);
insert into car(processed, created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values (1, '2014-05-12 15:00:00', 'серебристый металлик', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 5);
insert into car(created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2014-05-12 15:00:00', 'синий', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 1);
insert into car(created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2014-05-12 15:00:00', 'shadow line', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 3);
insert into car(created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2014-05-12 15:00:00', 'черный', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 2);
insert into car(created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2014-05-12 15:00:00', 'белый', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 4);
insert into car(created_date, color, drive_id, seller_type_id, transmission_id, displacement, mileage, modification, phone, price, site_id, year, body_type_id, engine_type_id, location_id, model_id) values ('2014-05-12 15:00:00', 'серебристый металлик', 1, 1, 1, 2500, 230000, null,'375298216893', 9500, '№614586', '2002-01-01', 2, 1, 1, 5);


-- =================================================================================================
-- Users
insert into user(name) values ('Вася');
insert into user(name) values ('Петя');
insert into user(name) values ('Физрук');

-- =================================================================================================
-- Subscriptions
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 1', 1, '2000-01-01', '2005-01-01', 100000, 200000, 10000, 15000, 1, 1, 1, null, null, 1, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 2', 2, '1999-01-01', '2002-01-01', 100000, 400000, 5000, 10000, 7, 2, 1, null, null, 2, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 3', 2, '2005-01-01', '2010-01-01', null, null, null, null, 8, null, null, null, null, 1, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 4', 2, '2005-01-01', '2010-01-01', null, null, null, null, 1, null, null, null, null, 1, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 5', 3, '2005-01-01', '2010-01-01', null, null, null, null, 4, null, null, null, null, 4, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 6', 1, '2005-01-01', '2010-01-01', null, null, null, null, 3, null, null, null, null, 2, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 7', 2, '2005-01-01', '2010-01-01', null, null, null, null, 2, null, null, null, null, 3, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 8', 2, '2005-01-01', '2010-01-01', null, null, null, null, 2, null, null, null, null, 1, null);
insert into subscription(name, user_id, year_from, year_to, mileage_from, mileage_to, price_from, price_to, model_id, body_type_id, engine_type_id, transmission_id, drive_id, location_id, seller_type_id) values ('Подписка 9', 1, '2000-01-01', '2005-01-01', 100000, 200000, 10000, 15000, 1, 1, 1, null, null, 1, null);


-- =================================================================================================
-- car_subscriptions table
insert into cars_subscriptions(car_id, subscription_id) values (1, 8);
insert into cars_subscriptions(car_id, subscription_id) values (2, 8);
insert into cars_subscriptions(car_id, subscription_id) values (3, 8);
insert into cars_subscriptions(car_id, subscription_id) values (4, 8);
insert into cars_subscriptions(car_id, subscription_id) values (5, 6);
insert into cars_subscriptions(car_id, subscription_id) values (6, 1);
insert into cars_subscriptions(car_id, subscription_id) values (7, 1);
insert into cars_subscriptions(car_id, subscription_id) values (8, 1);

insert into cars_subscriptions(car_id, subscription_id) values (9, 1);
insert into cars_subscriptions(car_id, subscription_id) values (9, 4);
insert into cars_subscriptions(car_id, subscription_id) values (11, 8);
insert into cars_subscriptions(car_id, subscription_id) values (9, 9);
