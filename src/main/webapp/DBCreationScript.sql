Create database phonestore default character set = "UTF8";
use phonestore;
CREATE TABLE brand
(
    id         int         NOT NULL AUTO_INCREMENT,
    brand_name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY brand_name (brand_name)
);

CREATE TABLE characteristics
(
    id           bigint NOT NULL AUTO_INCREMENT,
    memory       varchar(50) DEFAULT NULL,
    display      varchar(50) DEFAULT NULL,
    screen_size  varchar(50) DEFAULT NULL,
    camera       varchar(50) DEFAULT NULL,
    front_camera varchar(50) DEFAULT NULL,
    ram          varchar(50)  DEFAULT NULL,
    processor    varchar(50) DEFAULT NULL,
    battery      varchar(50) DEFAULT NULL,
    sizes        varchar(50) DEFAULT NULL,
    weight       varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           bigint      NOT NULL AUTO_INCREMENT,
    first_name   varchar(50) NOT NULL,
    second_name  varchar(50) NOT NULL,
    email        varchar(50) NOT NULL UNIQUE,
    phone_number varchar(20) NOT NULL,
    role         int         DEFAULT '0',
    address      varchar(100) NOT NULL,
    password     varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customerorder
(
    id               bigint NOT NULL AUTO_INCREMENT,
    customer_id      bigint       NOT NULL,
    constraint fk_orders_users_customers_id
        foreign key (customer_id) references user (id) on delete cascade on update cascade,
    date             varchar(20)  NOT NULL,
    status           varchar(50)  NOT NULL,
    shipping_address varchar(100) NOT NULL,
    full_name        varchar(100)  NOT NULL,
    phone_number     varchar(20)  NOT NULL,
    city             varchar(50)  NOT NULL,
    zip              varchar(7)   NOT NULL,
    details          varchar(50)  DEFAULT NULL,
    total_cost       float        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE phone
(
    id                 int         NOT NULL AUTO_INCREMENT,
    brand_id           int         NOT NULL,
    model              varchar(50) NOT NULL,
    color              varchar(50) DEFAULT NULL,
    model_year         int         DEFAULT NULL,
    price              float       NOT NULL,
    picture_path       text,
    phone_description  text,
    details            varchar(50) DEFAULT NULL,
    characteristics_id bigint      NOT NULL,
    quantity           int         NOT NULL,
    PRIMARY KEY (id),
    KEY fk_phone_brand_id (brand_id),
    KEY fk_phone_characteristic_id (characteristics_id),
    CONSTRAINT fk_phone_brand_id FOREIGN KEY (brand_id) REFERENCES brand (id) on delete cascade on update cascade,
    CONSTRAINT fk_phone_characteristic_id FOREIGN KEY (characteristics_id) REFERENCES characteristics (id) on delete cascade on update cascade
);

CREATE TABLE orderphone
(
    id       bigint NOT NULL AUTO_INCREMENT,
    order_id bigint NOT NULL,
    phone_id int    NOT NULL,
    quantity int    NOT NULL,
    PRIMARY KEY (id),
    KEY fk_phoneorder_order (order_id),
    KEY fk_phoneorder_phone (phone_id),
    CONSTRAINT fk_phoneorder_order FOREIGN KEY (order_id) REFERENCES customerorder (id) on delete cascade on update cascade,
    CONSTRAINT fk_phoneorder_phone FOREIGN KEY (phone_id) REFERENCES phone (id) on delete cascade on update cascade
);

# TEST VALUES FOR USER TABLE(password for admin : admin, password for user: user
insert into user (first_name, second_name, email, phone_number, role, address, password)
VALUES ('Админ', 'Тест', 'admin@mail.ru', '+7777666666', 1, 'ул.Пушкина, д.Колотушкина,кв.5',
        '21232f297a57a5a743894a0e4a801fc3');
insert into user (first_name, second_name, email, phone_number, role, address, password)
VALUES ('Юзер', 'Тест', 'user@mail.ru', '+77778888888', 0, 'ул.Пушкина, д.Колотушкина,кв.6',
        'ee11cbb19052e40b07aac0ca060c23ee');

INSERT INTO brand(id, brand_name) VALUES (1, 'Apple');
INSERT INTO brand(id, brand_name) VALUES (2, 'Xiaomi');
INSERT INTO brand(id, brand_name) VALUES (3, 'LG');
INSERT INTO brand(id, brand_name) VALUES (4, 'Nokia');
INSERT INTO brand(id, brand_name) VALUES (5, 'Samsung');

insert into characteristics(id, memory, display, screen_size, camera, front_camera, ram, processor, battery, sizes, weight) VALUES
(1, '128 GB', 'Retina HD', '5.6', '8 megapixels', '2 megapixels', '2 GB','Apple A8', '2800 mAh','122x189x133','133 g');
insert into phone(brand_id, model, color, model_year, price, picture_path, phone_description, characteristics_id, quantity) values
(1, 'Iphone 11', 'black', '2019', 322000, 'https://res.cloudinary.com/dj8xzq2we/image/upload/v1611169833/epam/phones_images/iphone11_yr3p59.png', 'Apple on September 10, 2019, unveiled the iPhone 11, and while Apple''s flagship lineup now consists of the iPhone 12, 12 Pro, 12 mini, and 12 Pro Max, the iPhone 11 is continuing to be sold as a lower cost option priced starting at $599.', 1, 5);

insert into characteristics(id, memory, display, screen_size, camera, front_camera, ram, processor, battery, sizes, weight) VALUES
(2, '64 GB', 'Amoled', '5.8', '18 megapixels', '2 megapixels', '2 GB','Mediatek A893', '3800 mAh','122x189x133','135 g');
insert into phone(brand_id, model, color, model_year, price, picture_path, phone_description, characteristics_id, quantity) values
(2, 'Redmi 9', 'white', '2020', 189000, 'https://res.cloudinary.com/dj8xzq2we/image/upload/v1611169677/epam/phones_images/xiaomi_redmi_9_gftktq.jpg', 'Redmi 9 smartphone was launched on 27th August 2020. The phone comes with a 6.53-inch touchscreen display with a resolution of 720x1600 pixels and an aspect ratio of 20:9. Redmi 9 is powered by an octa-core MediaTek Helio G35 processor. It comes with 4GB of RAM. The Redmi 9 runs Android 10 and is powered by a 5000mAh battery. The Redmi 9 supports proprietary fast charging.', 2, 5);

insert into characteristics(id, memory, display, screen_size, camera, front_camera, ram, processor, battery, sizes, weight) VALUES
(3, '64 GB', 'Amoled', '5.8', '18 megapixels', '2 megapixels', '2 GB','Mediatek A893', '3800 mAh','122x189x133','135 g');
insert into phone(brand_id, model, color, model_year, price, picture_path, phone_description, characteristics_id, quantity) values
(3, 'X5', 'olive', '2018', 200000, 'https://res.cloudinary.com/dj8xzq2we/image/upload/v1611171341/epam/phones_images/LG_X5_qy123s.jpg', 'LG X5 2018 smartphone runs on Android v8.0 (Oreo) operating system. The phone is powered by Octa core (1.5 GHz, Quad core, Cortex A53 + 1 GHz, Quad core, Cortex A53) processor. It runs on the MediaTek MT6750 Chipset. It has 2 GB RAM and 32 GB internal storage. LG X5 2018 smartphone has a IPS LCD display. It measures 154.7 mm x 78.1 mm x 8.9 mm and weighs 171 grams. The screen has a resolution of HD (720 x 1280 pixels) and 267 ppi pixel density. On camera front, the buyers get a 5 MP Front Camera and on the rear, there''''s an 13 MP camera with features like Digital Zoom, Auto Flash, Face detection, Touch to focus.', 3, 5);

insert into characteristics(id, memory, display, screen_size, camera, front_camera, ram, processor, battery, sizes, weight) VALUES
(4, '128 GB', 'Amoled', '5.8', '18 megapixels', '2 megapixels', '2 GB','Mediatek A893', '3800 mAh','122x189x133','135 g');
insert into phone(brand_id, model, color, model_year, price, picture_path, phone_description, characteristics_id, quantity) values
(4, 'A40', 'olive', '2018', 134000, 'https://res.cloudinary.com/dj8xzq2we/image/upload/v1611171676/epam/phones_images/NOKIA_nesqmt.jpg', 'Тройная камера и оптика ZEISS дают широкий простор для творчества. Высокочувствительный сенсор 48 МП для съемки мельчайших деталей, сенсор глубины 5 МП и оптика ZEISS для реалистичных портретов, а также сенсор с ультрашироким углом обзора 118° – в один кадр легко поместиться всё самое важное.', 4, 5);

insert into characteristics(id, memory, display, screen_size, camera, front_camera, ram, processor, battery, sizes, weight) VALUES
(5, '32 GB', 'Amoled', '5.8', '18 megapixels', '2 megapixels', '2 GB','Mediatek A893', '3800 mAh','122x189x133','135 g');
insert into phone(brand_id, model, color, model_year, price, picture_path, phone_description, characteristics_id, quantity) values
(5, 'A51', 'olive', '2019', 156000, 'https://res.cloudinary.com/dj8xzq2we/image/upload/c_scale,w_428/v1611169677/epam/phones_images/SamsungGalaxyA50_lgksho.jpg', 'Станьте ближе к тому, что важно для вас, на 6,4-дюймовом дисплее Infinity-U Galaxy A50. Практически безрамочный дисплей покрывает телефон от края до края. А благодаря разрешению FHD+ и цветам sAMOLED вы можете с головой окунуться в свои любимые видеоблоги и прямые трансляции.', 5, 5);


