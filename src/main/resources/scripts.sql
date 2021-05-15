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

CREATE TABLE ALBUM_WITH_PHOTOS
(
    id       UUID,
    album_id UUID NOT NULL,
    photo_id UUID NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album (id),
    FOREIGN KEY (photo_id) REFERENCES photo (id),
    UNIQUE (album_id, photo_id),
    PRIMARY KEY (id)
);

INSERT INTO USERS(id, name)
values ('aa3cb556-c469-4ce8-989b-a4d65fbb4643', 'name1'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4644', 'name2'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4645', 'name3'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4646', 'name4');


-- INSERT INTO USERS(id, name)
-- values ('aa3cb556-c469-4ce8-989b-a4d65fbb4643', 'name1'),
--        ('aa3cb556-c469-4ce8-989b-a4d65fbb4644', 'name2'),
--        ('aa3cb556-c469-4ce8-989b-a4d65fbb4645', 'name3'),
--        ('aa3cb556-c469-4ce8-989b-a4d65fbb4646', 'name4');
--
-- INSERT INTO BRAND(id, name,description)
-- values ('bc3cb556-c469-4ce8-989b-a4d65fbb4643', 'Sony', 'description1'),
--        ('bc3cb556-c469-4ce8-989b-a4d65fbb4644', 'Apple', 'description'),
--        ('bc3cb556-c469-4ce8-989b-a4d65fbb4645', 'Canon', ''),
--        ('bc3cb556-c469-4ce8-989b-a4d65fbb4646', 'Nikon', 'description4');
--
-- INSERT INTO MODEL(id, name,brand_id)
-- values ('df3cb556-c469-4ce8-989b-a4d65fbb4643', 'Alpha A7 Mark', 'bc3cb556-c469-4ce8-989b-a4d65fbb4643'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4644', 'iPhone 4', 'bc3cb556-c469-4ce8-989b-a4d65fbb4644'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4645', 'EOS 2000D Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4645'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4646', 'D3500 Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4646');
--
--
-- INSERT INTO PHOTO(id, file_name,upload_date, upload_time, photo_date, photo_time, user_id, model_id)
-- values ('df3cb556-c469-4ce8-989b-a4d65fbb4643', 'Alpha A7 Mark', 'bc3cb556-c469-4ce8-989b-a4d65fbb4643'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4644', 'iPhone 4', 'bc3cb556-c469-4ce8-989b-a4d65fbb4644'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4645', 'EOS 2000D Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4645'),
--        ('df3cb556-c469-4ce8-989b-a4d65fbb4646', 'D3500 Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4646');
--
--
-- CREATE TABLE PHOTO
-- (
--     id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     file_name    VARCHAR(50)  NOT NULL,
--     upload_date  DATE         NOT NULL,
--     upload_time  TIME         NOT NULL,
--     photo_date   DATE         NOT NULL,
--     photo_time   TIME         NOT NULL,
--     user_id      UUID         NOT NULL,
--     brand_id UUID         NOT NULL,
--     model_id UUID         NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES "users" (id),
--     FOREIGN KEY (brand_id) REFERENCES BRAND (id),
--     FOREIGN KEY (model_id) REFERENCES MODEL (id)
-- );
--
-- CREATE TABLE GEO_DATA
-- (
--     id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     photo_id UUID      NOT NULL,
--     place    GEOGRAPHY NOT NULL,
--     FOREIGN KEY (photo_id) REFERENCES photo (id),
--     UNIQUE (photo_id)
-- );
--
-- CREATE TABLE ALBUM
-- (
--     id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     album_name    VARCHAR(30) NOT NULL,
--     creation_date DATE        NOT NULL,
--     creation_time TIME        NOT NULL,
--     description   VARCHAR(50),
--     user_id       UUID        NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES "users" (id)
-- );
--
-- CREATE TABLE ALBUM_WITH_PHOTOS
-- (
--     id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     album_id UUID NOT NULL,
--     photo_id UUID NOT NULL,
--     FOREIGN KEY (album_id) REFERENCES album (id),
--     FOREIGN KEY (photo_id) REFERENCES photo (id),
--     UNIQUE (album_id, photo_id)
-- );

