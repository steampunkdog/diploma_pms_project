INSERT INTO RoomTag (name) VALUES ('Perfect view');
INSERT INTO RoomTag (name) VALUES ('Kitchen facilities');
INSERT INTO RoomTag (name) VALUES ('Pet friendly');
INSERT INTO RoomTag (name) VALUES ('Non-smoking room');
INSERT INTO RoomTag (name) VALUES ('Terrace');
INSERT INTO RoomTag (name) VALUES ('Balcony');
INSERT INTO RoomTag (name) VALUES ('Soundproof');
INSERT INTO RoomTag (name) VALUES ('Wheelchair accessible');

INSERT INTO RoomClass (name) VALUES ('Lux');
INSERT INTO RoomClass (name) VALUES ('Half-Lux');
INSERT INTO RoomClass (name) VALUES ('Family');
INSERT INTO RoomClass (name) VALUES ('Standart');
INSERT INTO RoomClass (name) VALUES ('Economy');


INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (1, 'Comfort Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 2, 10, 50, 4, 4, 400.0);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (2, 'Premium Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 1, 8, 60, 4, 4, 599.99);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (3, 'Big Family Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 3, 8, 50, 3, 6, 300);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (4, 'Room Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 4, 8, 35, 2, 2, 200);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (5, 'Just Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 5, 8, 30, 1, 2, 69.78);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (6, 'Another Simple Room ', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 5, 6, 20, 1, 2, 50);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (7, 'Room-Room-Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 1, 6, 70, 4, 4, 700);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (8, 'Another Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 3, 6, 40, 2, 2, 343);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (9, 'Some Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?', 3, 3, 40, 2, 2, 350);

INSERT INTO Room (id, name, description, room_class, room_floor, room_area, number_of_rooms, sleeping_area_number, price)
VALUES (10,'Again Room', 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nulla nostrum, laborum corrupti voluptates, libero blanditiis inventore. Officiis ab aliquam nostrum?',  3, 3, 40, 2, 2, 442);



INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (1, 1);
INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (1, 3);
INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (1, 5);

INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (3, 5);
INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (3, 1);

INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (4, 3);
INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (4, 2);

INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (5, 5);
INSERT INTO RoomToRoomTag (room_id, room_tag_id) VALUES (5, 3);