DROP TABLE IF EXISTS PHOTO CASCADE;
DROP TABLE IF EXISTS GEO_DATA CASCADE;
DROP TABLE IF EXISTS ALBUM CASCADE;
DROP TABLE IF EXISTS ALBUM_WITH_PHOTOS CASCADE;
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS BRAND CASCADE;
DROP TABLE IF EXISTS DEVICE CASCADE;
DROP TABLE IF EXISTS MODEL CASCADE;

CREATE TABLE USERS
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) NOT NULL
);

CREATE TABLE BRAND
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(50) NOT NULL
);

CREATE TABLE MODEL
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name     VARCHAR(50) NOT NULL,
    brand_id UUID        NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES BRAND (id)
);

CREATE TABLE PHOTO
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    file_name   VARCHAR(50),
    upload_date DATE        NOT NULL,
    upload_time TIME        NOT NULL,
    photo_date  DATE        NOT NULL,
    photo_time  TIME        NOT NULL,
    user_id     UUID        NOT NULL,
    model_id    UUID        NOT NULL,
    file BYTEA NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "users" (id),
    FOREIGN KEY (model_id) REFERENCES MODEL (id)
);

CREATE TABLE GEO_DATA
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    place    GEOGRAPHY NOT NULL
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