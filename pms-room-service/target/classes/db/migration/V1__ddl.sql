CREATE TABLE Room (
                      id serial NOT NULL,
                      name VARCHAR(1000),
                      description text,
                      room_class integer NOT NULL,
                      room_floor integer NOT NULL,
                      room_area integer NOT NULL,
                      number_of_rooms integer NOT NULL,
                      sleeping_area_number integer NOT NULL DEFAULT 1,
                      price float,
                      CONSTRAINT Room_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );



CREATE TABLE RoomClass (
                           Id serial NOT NULL,
                           name VARCHAR(255) NOT NULL UNIQUE,
                           CONSTRAINT RoomClass_pk PRIMARY KEY (Id)
) WITH (
      OIDS=FALSE
    );



CREATE TABLE RoomTag (
                         id serial NOT NULL,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         CONSTRAINT RoomTag_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );



CREATE TABLE RoomToRoomTag (
                               id serial NOT NULL,
                               room_id integer NOT NULL,
                               room_tag_id integer NOT NULL,
                               CONSTRAINT RoomToRoomTag_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );



ALTER TABLE Room ADD CONSTRAINT Room_fk0 FOREIGN KEY (room_class) REFERENCES RoomClass(Id);

ALTER TABLE RoomToRoomTag ADD CONSTRAINT RoomToRoomTag_fk0 FOREIGN KEY (room_id) REFERENCES Room(id);
ALTER TABLE RoomToRoomTag ADD CONSTRAINT RoomToRoomTag_fk1 FOREIGN KEY (room_tag_id) REFERENCES RoomTag(id);


