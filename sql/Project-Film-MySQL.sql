create table user_infos
(
    username      varchar(50)  not null primary key,
    user_fullname varchar(255) not null,
    user_email    varchar(50)  not null,
    user_password varchar(64) not null,
    account_role  varchar(10)  not null
);

create table films
(
    film_id           bigint auto_increment primary key,
    film_name         varchar(255) not null,
    film_price        numeric      not null,
    film_director     varchar(100) not null,
    film_cast         varchar(255) not null,
    film_length       int          not null,
    film_description  varchar(5000)not null,
    film_trailer_link varchar(255),
    img_path          varchar(255) not null,
    slug              varchar(100) not null
);

create table theaters
(
    theater_id      bigint auto_increment primary key,
    theater_name    varchar(255) not null,
    tax_code        varchar(20)  not null,
    theater_address varchar(5000)not null
);

create table rooms
(
    room_id    bigint auto_increment primary key,
    room_name  varchar(255)  not null,
    theater_id bigint references theaters (theater_id),
    seat_rows  int     not null,
    seat_cols  int     not null,
    seats_data varchar(1000) not null,
    slug       varchar(100) not null
);

create table showtimes
(
    showtime_id   bigint auto_increment primary key,
    film_id       bigint references films (film_id),
    room_id       bigint references rooms (room_id),
    showtime_date timestamp 	not null,
    seats_data    varchar(1000) not null,
    slug          varchar(100) not null
);

create table film_bookings
(
    film_booking_id bigint auto_increment primary key,
    username        varchar(50) references user_infos (username),
    showtime_id     bigint references showtimes (showtime_id),
    booking_date    timestamp    not null,
    seats           varchar(100) not null,
    total_fee       numeric      not null
);

create table genres
(
    genre_id   varchar(30) not null primary key,
    genre_name varchar(50) not null
);

create table film_genres
(
    film_id  bigint references films (film_id),
    genre_id varchar(30) references genres (genre_id),
    primary key (film_id, genre_id)
);

create table foods
(
    food_id    varchar(50)  not null primary key,
    food_name  varchar(255) not null,
    food_price numeric      not null
);

create table food_bookings
(
    food_booking_id bigint auto_increment primary key,
    food_id         varchar(50) references foods (food_id),
    film_booking_id bigint references film_bookings (film_booking_id)
);




-- Genre
INSERT INTO genres (genre_id, genre_name) VALUES ('haikich', 'Hài kịch');
INSERT INTO genres (genre_id, genre_name) VALUES ('tamly', 'Tâm lý');
INSERT INTO genres (genre_id, genre_name) VALUES ('tinhcam', 'Tình cảm');
INSERT INTO genres (genre_id, genre_name) VALUES ('trinhtham', 'Trinh thám');
INSERT INTO genres (genre_id, genre_name) VALUES ('hoathinh', 'Hoạt hình');
INSERT INTO genres (genre_id, genre_name) VALUES ('vientuong', 'Viễn tưởng');
INSERT INTO genres (genre_id, genre_name) VALUES ('hanhdong', 'Hành động');


-- Theater
INSERT INTO theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Thủ Đức', '000000001', '1 Võ Văn Ngân, Linh Chiểu, Thủ Đức');
INSERT INTO theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Bến Thành', '000000238', 'Trương Định, Bến Thành, Q.1');
INSERT INTO theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Bình Thạnh', '000000034', 'Điện Biên Phủ, P15, Q.Bình Thạnh');
