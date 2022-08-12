truncate table shop_info.branch cascade;
truncate table shop_info.shop cascade ;
truncate table shop_info.contact_info cascade;

insert into shop_info.contact_info (id, city, country, email, post_code, street_name, tel_land, tel_mobile)
values  (10, 'Atherstone', 'United Kingdom', 'test@aldi.co.uk', 'CV9 2SQ', 'Holly Ln', '08000420800', ''),
        (11, 'Cardiff', 'United Kingdom', 'test.citylink@aldi.co.uk', 'CF24 1PQ', '', '08000420800', ''),
        (12, 'Cardiff', 'United Kingdom', 'test.citylink@aldi.co.uk', 'CF11 0JR', '', '08000420800', ''),
        (14, 'Cardiff', 'United Kingdom', 'test.citylink@aldi.co.uk', 'CF110JR', '', '08000420800', '');

insert into shop_info.shop (id, created_user, is_deleted, last_updated_user, name, contact_info_id, created_date_time, updated_date_time)
values  (4, 'amila_student', false, 'amila_student', 'ALDI', 10, '2022-05-17 23:29:27.717000', '2022-05-17 23:29:27.717000');



insert into shop_info.branch (id, created_date_time, created_user, is_deleted, last_updated_user, name, qr_code, updated_date_time, contact_info_id, shop_id)
values  (7, '2022-05-17 23:30:22.599000', 'amila_student', false, 'amila_student', 'ALDI - City Link Retail Park', 'E:\UK\MSc\Dissertation\qr\138721265798090961.png', '2022-05-17 23:30:22.599000', 11, 4),
        (8, '2022-05-17 23:31:26.967000', 'amila_student', false, 'amila_student', 'ALDI - Ferry Road', 'E:\UK\MSc\Dissertation\qr\138721256108858971.png', '2022-05-17 23:31:26.967000', 12, 4),
        (10, '2022-05-18 00:08:58.016000', 'amila_student', false, 'amila_student', 'ALDI - Ferry Road2', 'E:\UK\MSc\Dissertation\qr\138721259726313466.png', '2022-05-18 00:09:09.565000', 14, 4);