DROP TABLE IF EXISTS PHOTO CASCADE;
DROP TABLE IF EXISTS GEO_DATA CASCADE;
DROP TABLE IF EXISTS ALBUM CASCADE;
DROP TABLE IF EXISTS ALBUM_WIH_PHOTOS CASCADE;
DROP TABLE IF EXISTS "USER" CASCADE;
DROP TABLE IF EXISTS BRAND CASCADE;
DROP TABLE IF EXISTS DEVICE CASCADE;
DROP TABLE IF EXISTS MODEL CASCADE;

CREATE TABLE "USER"
(
    id   UUID,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE BRAND
(
    id          UUID,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE DEVICE
(
    id       UUID,
    name     VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE MODEL
(
    id   UUID,
    name VARCHAR(50) NOT NULL,
    brand_id UUID        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE PHOTO
(
    id           UUID,
    path_to_file VARCHAR(100) NOT NULL,
    file_name    VARCHAR(50)  NOT NULL,
    upload_date  DATE         NOT NULL,
    upload_time  TIME         NOT NULL,
    photo_date   DATE         NOT NULL,
    photo_time   TIME         NOT NULL,
    user_id      UUID         NOT NULL,
    device_id UUID         NOT NULL,
    model_id UUID         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "USER" (id),
    FOREIGN KEY (device_id) REFERENCES DEVICE (id),
    FOREIGN KEY (model_id) REFERENCES MODEL (id),
    PRIMARY KEY (id)
);

CREATE TABLE GEO_DATA
(
    id       UUID,
    photo_id UUID      NOT NULL,
    place    GEOGRAPHY NOT NULL,
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (photo_id),
    PRIMARY KEY (id)
);

CREATE TABLE ALBUM
(
    id            UUID,
    album_name    VARCHAR(30) NOT NULL,
    creation_date DATE        NOT NULL,
    creation_time TIME        NOT NULL,
    description   VARCHAR(50),
    user_id       UUID        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "USER" (id),
    PRIMARY KEY (id)
);

CREATE TABLE ALBUM_WIH_PHOTOS
(
    id       UUID,
    album_id UUID NOT NULL,
    photo_id UUID NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album (id),
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (album_id, photo_id),
    PRIMARY KEY (id)
);

