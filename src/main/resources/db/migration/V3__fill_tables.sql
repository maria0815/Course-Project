INSERT INTO USERS(id, name)
values ('aa3cb556-c469-4ce8-989b-a4d65fbb4643', 'Maria'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4644', 'Alex'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4645', 'Ivan'),
       ('aa3cb556-c469-4ce8-989b-a4d65fbb4646', 'Anna');

INSERT INTO BRAND(id, name)
values ('bc3cb556-c469-4ce8-989b-a4d65fbb4643', 'Sony'),
       ('bc3cb556-c469-4ce8-989b-a4d65fbb4644', 'Apple'),
       ('bc3cb556-c469-4ce8-989b-a4d65fbb4645', 'Canon'),
       ('bc3cb556-c469-4ce8-989b-a4d65fbb4646', 'Nikon');

INSERT INTO MODEL(id, name, brand_id)
values ('df3cb556-c469-4ce8-989b-a4d65fbb4643', 'Alpha A7 Mark', 'bc3cb556-c469-4ce8-989b-a4d65fbb4643'),
       ('df3cb556-c469-4ce8-989b-a4d65fbb4644', 'iPhone 4', 'bc3cb556-c469-4ce8-989b-a4d65fbb4644'),
       ('df3cb556-c469-4ce8-989b-a4d65fbb4645', 'EOS 2000D Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4645'),
       ('df3cb556-c469-4ce8-989b-a4d65fbb4646', 'D3500 Kit', 'bc3cb556-c469-4ce8-989b-a4d65fbb4646');


INSERT INTO PHOTO(id, user_id, file_name, upload_date, upload_time, photo_date, photo_time, model_id, file)
values ('df3cb686-c469-4ce8-989b-a4d65fbb4643', 'aa3cb556-c469-4ce8-989b-a4d65fbb4643', 'name1', '2021-05-04',
        '00:04:08', '2020-05-04', '00:04:08', 'df3cb556-c469-4ce8-989b-a4d65fbb4643', '\000'),
       ('df3cb686-c469-4ce8-989b-a4d65fbb4644', 'aa3cb556-c469-4ce8-989b-a4d65fbb4644', 'name2', '2021-05-02',
        '00:02:03', '2020-05-02', '00:07:08', 'df3cb556-c469-4ce8-989b-a4d65fbb4644', '\001'),
       ('df3cb686-c469-4ce8-989b-a4d65fbb4645', 'aa3cb556-c469-4ce8-989b-a4d65fbb4645', 'name3', '2021-05-03',
        '00:06:03', '2021-05-08', '00:06:08', 'df3cb556-c469-4ce8-989b-a4d65fbb4645','\002'),
       ('df3cb686-c469-4ce8-989b-a4d65fbb4646', 'aa3cb556-c469-4ce8-989b-a4d65fbb4646', 'name4', '2021-05-01',
        '00:04:03', '2021-05-06', '00:04:09', 'df3cb556-c469-4ce8-989b-a4d65fbb4646','\003');

INSERT INTO GEO_DATA(id, photo_id, place)
values ('ff3cb556-c469-4ce8-989b-a4d65fbb4643', 'df3cb686-c469-4ce8-989b-a4d65fbb4643','POINT(-118.4079 33.9434)'),
       ('ff3cb556-c469-4ce8-989b-a4d65fbb4644', 'df3cb686-c469-4ce8-989b-a4d65fbb4644','POINT(2.3490 48.8533)');

INSERT INTO ALBUM(id, album_name, creation_date, creation_time, description, user_id)
values ('113cb686-c469-4ce8-989b-a4d65fbb4646', 'album1', '2021-05-03', '00:04:03', 'description1',
        'aa3cb556-c469-4ce8-989b-a4d65fbb4643'),
       ('113cb687-c469-4ce8-989b-a4d65fbb4646', 'album2', '2021-05-04', '00:04:03', 'description2',
        'aa3cb556-c469-4ce8-989b-a4d65fbb4644'),
       ('113cb688-c469-4ce8-989b-a4d65fbb4646', 'album3', '2021-05-06', '00:04:03', 'description3',
        'aa3cb556-c469-4ce8-989b-a4d65fbb4645');

INSERT INTO ALBUM_WITH_PHOTOS(id, album_id, photo_id)
values ('223cb688-c469-4ce8-989b-a4d65fbb4646','113cb687-c469-4ce8-989b-a4d65fbb4646','df3cb686-c469-4ce8-989b-a4d65fbb4643'),
       ('223cb686-c469-4ce8-989b-a4d65fbb4646','113cb686-c469-4ce8-989b-a4d65fbb4646','df3cb686-c469-4ce8-989b-a4d65fbb4644'),
       ('223cb682-c469-4ce8-989b-a4d65fbb4646','113cb688-c469-4ce8-989b-a4d65fbb4646','df3cb686-c469-4ce8-989b-a4d65fbb4645');