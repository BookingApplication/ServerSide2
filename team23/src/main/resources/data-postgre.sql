INSERT INTO role (id, name) VALUES (1, 'ROLE_GUEST');
INSERT INTO role (id, name) VALUES (2, 'ROLE_HOST');
INSERT INTO role (id, name) VALUES (3, 'ROLE_ADMIN');

--password je 123--
INSERT INTO administrator (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, profile_picture)
VALUES   (1, 'admin1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin1', 'Admin1', 'Bulevar Oslobodjenja 2, Novi Sad', '111222333', '2024-01-09 04:39:22', true, '123456', '2024-01-09 04:39:22', 'img1.jpg'),
         (2, 'admin2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin2', 'Admin2', 'Cara Dusana 11, Novi Sad', '444555666', '2024-01-09 04:39:22', true, '654321', '2024-01-09 04:39:22', 'img2.jpg'),
         (3, 'admin3@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin3', 'Admin3', 'Mileve Maric 15, Novi Sad', '777888999', '2024-01-09 04:39:22', true, '987654', '2024-01-09 04:39:22', 'img3.jpg');

INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, profile_picture)
VALUES   (4, 'guest1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest1', 'Guest1', 'Futoska 17 Novi Sad', '111222333', '2024-01-09 04:39:22', true, '111222', '2024-01-09 04:39:22', 'img4.jpg'),
         (5, 'guest2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest2', 'Guest2', 'Bulevar Oslobodjenja 88 Novi Sad', '444555666', '2024-01-09 04:39:22', true, '222333', '2024-01-09 04:39:22', 'img5.jpg'),
         (6, 'guest3@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest3', 'Guest3', 'Temerinska 42 Novi Sad', '555666777', '2024-01-09 04:39:22', true, '333444', '2024-01-09 04:39:22', 'img6.jpg'),
         (7, 'guest4@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest4', 'Guest4', 'Milosa Bajica 7 Novi Sad', '888999000', '2024-01-09 04:39:22', true, '444555', '2024-01-09 04:39:22', 'img7.jpg'),
         (8, 'guest5@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest8', 'Guest8', 'Hajduk Veljkova 11 Novi SAd', '111222333', '2024-01-09 04:39:22', true, '888999', '2024-01-09 04:39:22', 'img8.jpg'),
         (9, 'guest6@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest9', 'Guest9', 'Jovana Ducica 43 Novi Sad', '444555666', '2024-01-09 04:39:22', true, '999000', '2024-01-09 04:39:22', 'img9.jpg');

INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, profile_picture)
VALUES   (10, 'host1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host1', 'Host1', 'Bulevar Mihajla Pupina 19 Novi Sad', '7778889999', '2024-01-09 04:39:22', true, '333444', '2024-01-09 04:39:22', 'img10.jpg'),
         (11, 'host2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host2', 'Host2', 'Bulevar Oslobodjenja 43 Novi Sad', '1011121314', '2024-01-09 04:39:22', true, '444555', '2024-01-09 04:39:22', 'img11.jpg'),
         (12, 'host3@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host16', 'Host16', 'Klisanski put 167 Novi Sad', '1112223333', '2024-01-09 04:39:22', true, '161718', '2024-01-09 04:39:22', 'img12.jpg'),
         (13, 'host4@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host17', 'Host17', 'Narodnog Fronta 79 Novi Sad', '4445556666', '2024-01-09 04:39:22', true, '171819', '2024-01-09 04:39:22', 'img13.jpg'),
         (14, 'host5@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host18', 'Host18', 'Vojvodjanska 5 NOvi Sad', '5556667777', '2024-01-09 04:39:22', true, '181920', '2024-01-09 04:39:22', 'img14.jpg');

insert into user_role (user_id, role_id) values(1,3);
insert into user_role (user_id, role_id) values(2,3);
insert into user_role (user_id, role_id) values(3,3);
insert into user_role (user_id, role_id) values(4, 1);
insert into user_role (user_id, role_id) values(5, 1);
insert into user_role (user_id, role_id) values(6, 1);
insert into user_role (user_id, role_id) values(7, 1);
insert into user_role (user_id, role_id) values(8, 1);
insert into user_role (user_id, role_id) values(9, 1);
insert into user_role (user_id, role_id) values(10, 2);
insert into user_role (user_id, role_id) values(11, 2);
insert into user_role (user_id, role_id) values(12, 2);
insert into user_role (user_id, role_id) values(13, 2);
insert into user_role (user_id, role_id) values(14, 2);

INSERT INTO accommodation (id, host_id, status, min_guests, max_guests,reservation_deadline, accommodation_type, location, name, description, is_price_set_per_guest, is_reservation_manual)
VALUES
    (1, 10, 2, 2, 10, 3, 'motel', 'Bulevar Oslobodjenja 12 Novi Sad', 'Motel Novi Sad 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (2, 10, 2, 1, 5,  3,'motel', 'Bulevar Oslobodjenja 15 Novi Sad', 'Motel Novi Sad 2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, true),
    (3, 10, 2, 2, 4, 3, 'motel', 'Bulevar Oslobodjenja 42 Novi Sad', 'Motel Novi Sad 3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (4, 10, 2, 2, 4, 3, 'motel', 'Cara Lazara 77 Novi Sad', 'Motel Novi Sad 4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, true),
    (5, 10, 2, 3, 7, 3, 'cottage', 'Bulevar Oslobodjenja 150 Novi Sad', 'Cottage Novi Sad 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (6, 10, 2, 3, 7, 3, 'cottage', 'Narodnog Fronta 20 Novi Sad', 'Cottage Novi Sad 2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, true),
    (7, 11, 2, 3, 7, 3, 'cottage', 'Cara Lazara 57 Novi Sad', 'Cottage Novi Sad 3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, true),
    (8, 11, 2, 3, 7, 3, 'cottage', 'Cara Lazara 18 Novi Sad', 'Cottage Novi Sad 4','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.' , false, true),
    (9, 11, 2, 0, 7, 3, 'chalet', 'Futoska 44 Novi Sad', 'Chalet Novi Sad 1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.' ,false, true),
    (10, 12, 2, 3, 6, 3, 'chalet', 'Mileve Maric 8 Novi Sad', 'Chalet Novi Sad 2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, true),
    (11, 12, 2, 3, 6, 3, 'apartment', 'Mileve Maric 18 Novi Sad', 'Apartment Novi Sad 1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (12, 12, 2, 0, 7, 3, 'apartment', 'Futoska 5 Novi Sad', 'Apartment Novi Sad 2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (13, 12, 2, 0, 7, 3, 'apartment', 'Futoska 21 Novi Sad', 'Apartment Novi Sad 3','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (14, 13, 2, 0, 7, 3, 'aparthotel', 'Futoska 32 Novi Sad', 'Aparthotel Novi Sad 1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (15, 13, 0, 0, 7, 3, 'aparthotel', 'Futoska 55 Novi Sad', 'Aparthotel Novi Sad 2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (16, 13, 0, 2, 5, 3, 'aparthotel', 'Bulevar Oslobodjenja 40 Novi Sad', 'Aparthotel Novi Sad 3','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (17, 13, 0, 2, 5, 3, 'motel', 'Bulevar Oslobodjenja 30 Novi Sad', 'Motel Novi Sad 5','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (18, 13, 0, 2, 5, 3,'motel', 'Fruskogorska 17 Novi Sad', 'Motel Novi Sad 6','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (19, 14, 1, 6, 9, 3, 'apartment', 'Fruskogorska 20 Novi Sad', 'Apartment Novi Sad 4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', false, false),
    (20, 14, 1, 1, 9, 3, 'apartment', 'Fruskogorska 25 Novi Sad', 'Apartment Novi Sad 5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', true, false),
    (21, 14, 1, 4, 15, 3, 'apartment', 'Fruskogorska 28 Novi Sad', 'Apartment Novi Sad 6', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', true, true);

INSERT INTO accommodation_amenities (accommodation_id, amenities)
VALUES
    (1, 'HAIR_DRYER'),
    (1, 'ROOM_SERVICE'),
    (1, 'AIR_CONDITIONING'),
    (1, 'LUGGAGE_STORAGE'),
    (2, 'FREE_WIFI'),
    (2, 'ROOM_SERVICE'),
    (2, 'AIR_CONDITIONING'),
    (2, 'LUGGAGE_STORAGE'),
    (3, 'FREE_WIFI'),
    (3, 'BUSINESS_CENTER'),
    (3, 'ROOM_SERVICE'),
    (3, 'AIR_CONDITIONING'),
    (4, 'FREE_WIFI'),
    (4, 'ROOM_SERVICE'),
    (4, 'AIR_CONDITIONING'),
    (4, 'LUGGAGE_STORAGE'),
    (5, 'HAIR_DRYER'),
    (5, 'ROOM_SERVICE'),
    (5, 'AIR_CONDITIONING'),
    (5, 'LUGGAGE_STORAGE'),
    (6, 'FREE_WIFI'),
    (6, 'ROOM_SERVICE'),
    (6, 'AIR_CONDITIONING'),
    (6, 'LUGGAGE_STORAGE'),
    (7, 'FREE_WIFI'),
    (7, 'BUSINESS_CENTER'),
    (7, 'ROOM_SERVICE'),
    (7, 'AIR_CONDITIONING'),
    (8, 'FREE_WIFI'),
    (8, 'ROOM_SERVICE'),
    (8, 'AIR_CONDITIONING'),
    (8, 'LUGGAGE_STORAGE'),
    (9, 'HAIR_DRYER'),
    (9, 'ROOM_SERVICE'),
    (9, 'AIR_CONDITIONING'),
    (9, 'LUGGAGE_STORAGE'),
    (10, 'FREE_WIFI'),
    (10, 'ROOM_SERVICE'),
    (10, 'AIR_CONDITIONING'),
    (10, 'LUGGAGE_STORAGE'),
    (11, 'FREE_WIFI'),
    (11, 'BUSINESS_CENTER'),
    (11, 'ROOM_SERVICE'),
    (11, 'AIR_CONDITIONING'),
    (12, 'FREE_WIFI'),
    (12, 'ROOM_SERVICE'),
    (12, 'AIR_CONDITIONING'),
    (12, 'LUGGAGE_STORAGE'),
    (13, 'HAIR_DRYER'),
    (13, 'ROOM_SERVICE'),
    (13, 'AIR_CONDITIONING'),
    (13, 'LUGGAGE_STORAGE'),
    (14, 'FREE_WIFI'),
    (14, 'ROOM_SERVICE'),
    (14, 'AIR_CONDITIONING'),
    (14, 'LUGGAGE_STORAGE'),
    (15, 'FREE_WIFI'),
    (15, 'BUSINESS_CENTER'),
    (15, 'ROOM_SERVICE'),
    (15, 'AIR_CONDITIONING'),
    (16, 'FREE_WIFI'),
    (16, 'ROOM_SERVICE'),
    (16, 'AIR_CONDITIONING'),
    (16, 'LUGGAGE_STORAGE'),
    (17, 'HAIR_DRYER'),
    (17, 'ROOM_SERVICE'),
    (17, 'AIR_CONDITIONING'),
    (17, 'LUGGAGE_STORAGE'),
    (18, 'FREE_WIFI'),
    (18, 'ROOM_SERVICE'),
    (18, 'AIR_CONDITIONING'),
    (18, 'LUGGAGE_STORAGE'),
    (19, 'FREE_WIFI'),
    (19, 'BUSINESS_CENTER'),
    (19, 'ROOM_SERVICE'),
    (19, 'AIR_CONDITIONING'),
    (20, 'FREE_WIFI'),
    (20, 'ROOM_SERVICE'),
    (20, 'AIR_CONDITIONING'),
    (20, 'LUGGAGE_STORAGE'),
    (21, 'HAIR_DRYER'),
    (21, 'ROOM_SERVICE'),
    (21, 'AIR_CONDITIONING'),
    (21, 'LUGGAGE_STORAGE');

INSERT INTO image (accommodation_id, id, image_path, name, type)
VALUES
    (1, 1, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (1, 2, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (2, 3, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (2, 4, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (3, 5, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (3, 6, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (4, 7, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (4, 8, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (5, 9, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (5, 10, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (6, 11, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (6, 12, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (7, 13, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (7, 14, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (8, 15, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (8, 16, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (9, 17, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (9, 18, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (10, 19, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (10, 20, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (11, 21, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (11, 22, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (12, 23, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (12, 24, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (13, 25, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (13, 26, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (14, 27, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (14, 28, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (15, 29, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (15, 30, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (16, 31, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (16, 32, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (17, 33, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (17, 34, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (18, 35, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (18, 36, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (19, 37, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (19, 38, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (20, 39, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (20, 40, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg'),
    (21, 41, 'team23/src/main/resources/static/images/accommodations/img1.jpg', 'img1.jpg', 'image/jpeg'),
    (21, 42, 'team23/src/main/resources/static/images/accommodations/img2.jpg', 'img2.jpg', 'image/jpeg');

INSERT INTO available_intervals (accommodation_id, start_date, end_date) VALUES
     (1, 1643712000000, 1644316800000), -- Feb 1-10
     (1, 1644403200000, 1645008000000), -- Feb 12-21
     (1, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (1, 1645795200000, 1646400000000), -- Mar 2-11
     (2, 1644403200000, 1645008000000), -- Feb 12-21
     (2, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (2, 1645795200000, 1646400000000), -- Mar 2-11
     (3, 1643712000000, 1644316800000), -- Feb 1-10
     (3, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (3, 1645795200000, 1646400000000), -- Mar 2-11
     (4, 1643712000000, 1644316800000), -- Feb 1-10
     (4, 1644403200000, 1645008000000), -- Feb 12-21
     (4, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (4, 1645795200000, 1646400000000), -- Mar 2-11
     (5, 1644403200000, 1645008000000), -- Feb 12-21
     (5, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (5, 1645795200000, 1646400000000), -- Mar 2-11
     (6, 1643712000000, 1644316800000), -- Feb 1-10
     (6, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (6, 1645795200000, 1646400000000), -- Mar 2-11
     (7, 1644403200000, 1645008000000), -- Feb 12-21
     (7, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (7, 1645795200000, 1646400000000), -- Mar 2-11
     (8, 1643712000000, 1644316800000), -- Feb 1-10
     (8, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (8, 1645795200000, 1646400000000), -- Mar 2-11
     (9, 1644403200000, 1645008000000), -- Feb 12-21
     (9, 1645094400000, 1645699200000), -- Feb 23-Mar 1
     (9, 1645795200000, 1646400000000), -- Mar 2-11
     (10, 1643712000000,1644316800000), -- Feb 1-10
     (10, 1644403200000,1645008000000), -- Feb 12-21
     (10, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (10, 1645795200000,1646400000000), -- Mar 2-11
     (11, 1644403200000,1645008000000), -- Feb 12-21
     (11, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (11, 1645795200000,1646400000000), -- Mar 2-11
     (12, 1643712000000,1644316800000), -- Feb 1-10
     (12, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (12, 1645795200000,1646400000000), -- Mar 2-11
     (13, 1644403200000,1645008000000), -- Feb 12-21
     (13, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (13, 1645795200000,1646400000000), -- Mar 2-11
     (14, 1643712000000,1644316800000), -- Feb 1-10
     (14, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (14, 1645795200000,1646400000000), -- Mar 2-11
     (15, 1644403200000,1645008000000), -- Feb 12-21
     (15, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (15, 1645795200000,1646400000000), -- Mar 2-11
     (16, 1643712000000,1644316800000), -- Feb 1-10
     (16, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (16, 1645795200000,1646400000000), -- Mar 2-11
     (17, 1644403200000,1645008000000), -- Feb 12-21
     (17, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (17, 1645795200000,1646400000000), -- Mar 2-11
     (18, 1643712000000,1644316800000), -- Feb 1-10
     (18, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (18, 1645795200000,1646400000000), -- Mar 2-11
     (19, 1644403200000,1645008000000), -- Feb 12-21
     (19, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (19, 1645795200000,1646400000000), -- Mar 2-11
     (20, 1643712000000,1644316800000), -- Feb 1-10
     (20, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (20, 1645795200000,1646400000000), -- Mar 2-11
     (21, 1644403200000,1645008000000), -- Feb 12-21
     (21, 1645094400000,1645699200000), -- Feb 23-Mar 1
     (21, 1645795200000,1646400000000); -- Mar 2-11


