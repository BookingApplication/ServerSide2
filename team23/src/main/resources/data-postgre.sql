INSERT INTO role (id, name) VALUES (1, 'ROLE_GUEST');
INSERT INTO role (id, name) VALUES (2, 'ROLE_HOST');
INSERT INTO role (id, name) VALUES (3, 'ROLE_ADMIN');

--password je 123--
INSERT INTO administrator (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (1, 'admin1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin1', 'Admin1', '123 Admin St, Cityville', '1234567890', '2024-01-09 04:39:22', true, '123456', NULL, false);
INSERT INTO administrator (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (2, 'admin2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin2', 'Admin2', '456 Admin Ave, Townburg', '9876543210', '2024-01-09 04:39:22', true, '654321', NULL, false);
INSERT INTO administrator (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (3, 'admin3@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin3', 'Admin3', '789 Admin Blvd, Villagetown', '5551234567', '2024-01-09 04:39:22', true, '987654', NULL, false);

INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (4, 'guest1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest1', 'Guest1', '123 Guest St, Cityville', '1112223333', '2024-01-09 04:39:22', true, '111222', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (5, 'guest2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest2', 'Guest2', '456 Guest Ave, Townburg', '4445556666', '2024-01-09 04:39:22', true, '222333', NULL, false);
INSERT INTO guest (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (6, 'guest3@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest3', 'Guest3', '789 Guest Blvd, Villagetown', '5556667777', '2024-01-09 04:39:22', true, '333444', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (7, 'guest4@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest4', 'Guest4', '101 Guest St, Cityville', '8889990000', '2024-01-09 04:39:22', true, '444555', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (8, 'guest8@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest8', 'Guest8', '123 Guest Lane, Suburbia', '1112223333', '2024-01-09 04:39:22', true, '888999', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (9, 'guest9@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest9', 'Guest9', '456 Guest Ave, Townburg', '4445556666', '2024-01-09 04:39:22', true, '999000', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (10, 'guest10@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest10', 'Guest10', '789 Guest Blvd, Villagetown', '5556667777', '2024-01-09 04:39:22', true, '101112', NULL, false);
INSERT INTO guest (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (11, 'guest11@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest11', 'Guest11', '101 Guest St, Cityville', '8889990000', '2024-01-09 04:39:22', true, '111213', NULL, false);
INSERT INTO guest (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (12, 'guest12@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest12', 'Guest12', '123 Guest Lane, Suburbia', '1112223333', '2024-01-09 04:39:22', true, '121314', NULL, false);
INSERT INTO guest (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (13, 'guest13@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'guest13', 'Guest13', '456 Guest Ave, Townburg', '4445556666', '2024-01-09 04:39:22', true, '131415', NULL, false);

--password je pass--
INSERT INTO guest (id, activated, email, living_address, username, password, name, surname, telephone_number, deleted, last_password_reset_date)
VALUES (52, true, '33mymail@gmail.com', '123 Main St, City', 'guest123', '$2a$10$/k0/A8WjyeaHBZg5/Gi1p.ztF74NtOdtMy0vj57ZBkOg4zgpJeXaO', 'guest123','guest123', '111-456-111', false, '2024-01-09 04:39:22');
INSERT INTO guest (id, activated, email, living_address, username, password, name, surname, telephone_number, deleted, last_password_reset_date)
VALUES (53, true, '53mymail@gmail.com', '123 Main St, City', 'guest123', '$2a$10$T5cga/7Y.37Lhbt3hEPJVOX5OmKVgvj1/23c7w3z8E0RO.cYXkuEi', 'guest123','guest123', '111-456-111', false, '2024-01-09 04:39:22');
INSERT INTO guest (id, activated, email, living_address, username, password, name, surname, telephone_number, deleted, last_password_reset_date)
VALUES (102, true, '23mymail@gmail.com', '123 Main St, City', 'guest123', '$2a$10$5GY8ykeKQDcI9XVYV5cHweF9/7uNpb08MhkJD6nl/TTQBPT01YWFm', 'guest123','guest123', '111-456-111', false, '2024-01-09 04:39:22');


INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (14, 'host1@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host1', 'Host1', '123 Host St, Cityville', '7778889999', '2024-01-09 04:39:22', true, '333444', NULL, false);
INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (15, 'host2@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host2', 'Host2', '456 Host Ave, Townburg', '1011121314', '2024-01-09 04:39:22', true, '444555', NULL, false);
INSERT INTO host (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (16, 'host16@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host16', 'Host16', '123 Host Lane, Suburbia', '1112223333', '2024-01-09 04:39:22', true, '161718', NULL, false);
INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (17, 'host17@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host17', 'Host17', '456 Host Ave, Towndale', '4445556666', '2024-01-09 04:39:22', true, '171819', NULL, false);
INSERT INTO host (id, email, password,  name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (18, 'host18@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host18', 'Host18', '789 Host Blvd, Citytown', '5556667777', '2024-01-09 04:39:22', true, '181920', NULL, false);
INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (19, 'host19@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host19', 'Host19', '101 Host St, Villagetown', '8889990000', '2024-01-09 04:39:22', true, '192021', NULL, false);
INSERT INTO host (id, email, password, name, surname, living_address, telephone_number, last_password_reset_date, activated, code_activation, account_verification_request_date, deleted)
VALUES (20, 'host20@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'host20', 'Host20', '123 Host Lane, Suburbia', '1112223333', '2024-01-09 04:39:22', true, '202122', NULL, false);


insert into user_role (user_id, role_id) values(1,3);
insert into user_role (user_id, role_id) values(2,3);
insert into user_role (user_id, role_id) values(3,3);
insert into user_role (user_id, role_id) values(4, 1);
insert into user_role (user_id, role_id) values(5, 1);
insert into user_role (user_id, role_id) values(6, 1);
insert into user_role (user_id, role_id) values(7, 1);
insert into user_role (user_id, role_id) values(8, 1);
insert into user_role (user_id, role_id) values(9, 1);
insert into user_role (user_id, role_id) values(10, 1);
insert into user_role (user_id, role_id) values(11, 1);
insert into user_role (user_id, role_id) values(12, 1);
insert into user_role (user_id, role_id) values(13, 1);
insert into user_role (user_id, role_id) values(14, 2);
insert into user_role (user_id, role_id) values(15, 2);
insert into user_role (user_id, role_id) values(16, 2);
insert into user_role (user_id, role_id) values(17, 2);
insert into user_role (user_id, role_id) values(18, 2);
insert into user_role (user_id, role_id) values(19, 2);
insert into user_role (user_id, role_id) values(20, 2);
insert into user_role (user_id, role_id) values(52, 1);
insert into user_role (user_id, role_id) values(53, 1);
insert into user_role (user_id, role_id) values(102, 1);