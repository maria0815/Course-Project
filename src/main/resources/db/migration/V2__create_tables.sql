DROP TABLE IF EXISTS PHOTO CASCADE;
DROP TABLE IF EXISTS GEO_DATA CASCADE;
DROP TABLE IF EXISTS ALBUM CASCADE;
DROP TABLE IF EXISTS ALBUM_WITH_PHOTOS CASCADE;
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS MANUFACTURER CASCADE;
DROP TABLE IF EXISTS DEVICE CASCADE;
DROP TABLE IF EXISTS MODEL CASCADE;

CREATE TABLE USERS
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) NOT NULL
);

CREATE TABLE MANUFACTURER
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE MODEL
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name     VARCHAR(50) NOT NULL,
    manufacturer_id UUID        NOT NULL,
    FOREIGN KEY (manufacturer_id) REFERENCES MANUFACTURER (id),
    UNIQUE (name, manufacturer_id)
);

CREATE TABLE GEO_DATA
(
    id    UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    place GEOGRAPHY NOT NULL
);

CREATE TABLE PHOTO
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    file_name   VARCHAR(50) NOT NULL,
    user_id     UUID        NOT NULL,
    upload_date DATE        NOT NULL,
    upload_time TIME        NOT NULL,
    photo_date  DATE,
    photo_time  TIME,
    model_id    UUID,
    file        BYTEA       NOT NULL,
    geo_data_id  UUID,
    FOREIGN KEY (user_id) REFERENCES "users" (id),
    FOREIGN KEY (model_id) REFERENCES MODEL (id),
    FOREIGN KEY (geo_data_id) REFERENCES GEO_DATA (id)
);

CREATE TABLE ALBUM
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    album_name    VARCHAR(30) NOT NULL,
    creation_date DATE        NOT NULL,
    creation_time TIME        NOT NULL,
    description   VARCHAR(50),
    user_id       UUID        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "users" (id)
);

CREATE TABLE ALBUM_WITH_PHOTOS
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    album_id UUID NOT NULL,
    photo_id UUID NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album (id),
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (album_id, photo_id)
);