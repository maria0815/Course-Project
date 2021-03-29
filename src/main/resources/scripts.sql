CREATE TABLE PHOTO
(
    id           SERIAL,
    path_to_file VARCHAR(100) NOT NULL,
    file_name    VARCHAR(50)  NOT NULL,
    photo_date   DATE         NOT NULL,
    photo_time   TIME         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE GEODATA
(
    id       SERIAL,
    photo_id INT       NOT NULL,
    place    GEOGRAPHY NOT NULL,
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (photo_id),
    PRIMARY KEY (id)
);

CREATE TABLE ALBUM
(
    id         SERIAL,
    album_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ALBUM_WIH_PHOTOS
(
    id       SERIAL,
    album_id INT NOT NULL,
    photo_id INT NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album (id),
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (album_id, photo_id),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS PHOTO;
DROP TABLE IF EXISTS GEODATA;
DROP TABLE IF EXISTS ALBUM;
DROP TABLE IF EXISTS ALBUM_WIH_PHOTOS;

