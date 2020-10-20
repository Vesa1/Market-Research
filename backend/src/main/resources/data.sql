delete from users_roles;
delete from roles_privileges;
delete from privilege;
delete from role;
delete from check_in;
delete from poll;
delete from user;
delete from location;
delete from cigarettes;

insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');

INSERT INTO `user` (`id`, `active`, `approved`, `email`, `name`, `password`, `surname`, `username`) VALUES ('1', b'1', b'1', 'vesovic@gmail', 'Jelena', '$2a$10$fauGFYJbpX88pdVhSgC82eASjrbQk/S.xu1FpK2zkD5Lt/M1j5WZm', 'Vesovic', 'Vesa111');
INSERT INTO `user` (`id`, `active`, `approved`, `email`, `name`, `password`, `surname`, `username`) VALUES ('2', b'1', b'1', 'marko@gmail', 'Marko', '$2a$10$1c8DyRLwhr1XV6T2Af585O8wLkbLfDrncIUXoQqNKyYtv8R6SGIje', 'Markovic', 'Vesa123');
INSERT INTO `user` (`id`, `active`, `approved`, `email`, `name`, `password`, `surname`, `username`) VALUES ('3', b'1', b'0', 'jovanica@gmail', 'Jovana', '$2a$10$6rVimaVfUQastFIl1iAgTeecfZMHV9zUWRwTw8tJD1b5ZNqQ7JKfm', 'Jovanovic', 'jovana');
INSERT INTO `user` (`id`, `active`, `approved`, `email`, `name`, `password`, `surname`, `username`) VALUES ('4', b'1', b'1', 'ivan@gmail.com', 'Ivan', '$2a$10$IweBVYszCLK6e6v/KxMc.O2FgdZxs4UfLvYhPquOwoANQg6pF9L3C', 'Ivanovic', 'ivan');

insert into users_roles(user_id, role_id) values (1,1);
insert into users_roles(user_id, role_id) values (2,2);
insert into users_roles(user_id, role_id) values (3,2);
insert into users_roles(user_id, role_id) values (4,1);

insert into privilege (id, name) values (1, 'CRUDLocation');
insert into privilege (id, name) values (2, 'allLocations');
insert into privilege (id, name) values (3, 'checkIn');
insert into privilege (id, name) values (4, 'CRUDCigarettes');
insert into privilege (id, name) values (5, 'CRUDpolls');
insert into privilege (id, name) values (6, 'allPolls');
insert into privilege (id, name) values (7, 'CRUDUsers');

insert into roles_privileges (role_id, privilege_id) values (1,1);
insert into roles_privileges (role_id, privilege_id) values (1,2);
insert into roles_privileges (role_id, privilege_id) values (2,2);
insert into roles_privileges (role_id, privilege_id) values (2,3);
insert into roles_privileges (role_id, privilege_id) values (2,4);
insert into roles_privileges (role_id, privilege_id) values (2,5);
insert into roles_privileges (role_id, privilege_id) values (1,6);
insert into roles_privileges (role_id, privilege_id) values (1,7);

INSERT INTO `location` (`id`, `active`, `address`, `city`, `grocery`, `latitude`, `longitude`, `type_of_store`) VALUES ('7', b'1', 'Nizijska 21', 'Novi Sad', '053 MIKROMARKET NS PJ 53', '45.286092', '19.822274', '1');
INSERT INTO `location` (`id`, `active`, `address`, `city`, `grocery`, `latitude`, `longitude`, `type_of_store`) VALUES ('41', b'1', 'Bulevar Evrope 7', 'Novi Sad', '019 MIKROMARKET NS PJ 19', '45.241017', '19.821468', '1');
INSERT INTO `location` (`id`, `active`, `address`, `city`, `grocery`, `latitude`, `longitude`, `type_of_store`) VALUES ('42', b'1', 'Branka Copica 80', 'Novi Sad', '084 Mikromarket ns doo Pj 84', '45.233859', '19.792914', '1');
INSERT INTO `location` (`id`, `active`, `address`, `city`, `grocery`, `latitude`, `longitude`, `type_of_store`) VALUES ('43', b'1', 'Bulevar Kralja Petra I 7', 'Novi Sad', 'BBT68', '45.261533', '19.8358', '2');
INSERT INTO `location` (`id`, `active`, `address`, `city`, `grocery`, `latitude`, `longitude`, `type_of_store`) VALUES ('44', b'1', 'Podgoricka 8', 'Novi Sad', 'Miss 2001 STRK-Mirko Tomovic', '45.241664', '19.840336', '0');


INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('1', '0', 'Touch 100s');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('2', '0', 'Touch KS');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('3', '0', 'Red 100s');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('4', '0', 'Red KS');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('5', '1', 'XStyle Blue');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('6', '1', 'XStyle Silver');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('7', '2', 'Gold KS');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('8', '2', 'Black KS');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('9', '3', 'Red 100s');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('10', '3', 'Red KS');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('11', '4', 'Black');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('12', '4', 'Blue');
INSERT INTO `cigarettes` (`id`, `brand`, `type`) VALUES ('13', '4', 'Silver');

