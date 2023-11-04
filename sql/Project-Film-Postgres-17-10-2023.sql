create table user_info
(
    username      varchar(50)  not null primary key,
    user_fullname varchar(100) not null,
    user_email    varchar(50)  not null,
    user_password varchar      not null,
    account_role  varchar(10)  not null
);

create table film
(
    film_id           varchar(50) primary key,
    film_name         varchar(100) not null,
    film_price        numeric      not null,
    film_director     varchar(100) not null,
    film_cast         varchar(255) not null,
    film_length       int          not null,
    film_description  varchar,
    film_trailer_link varchar,
    img_path          varchar(255) not null
);

create table theater
(
    theater_id      varchar(50) primary key,
    theater_name    varchar(255) not null,
    tax_code        varchar(20)  not null,
    theater_address varchar      not null
);

create table room
(
    room_id    varchar(50) primary key,
    room_name  varchar(50),
    theater_id varchar(50) references theater (theater_id),
    seat_rows  int     not null,
    seat_cols  int     not null,
    seats_data varchar not null
);

create table showtime
(
    showtime_id   varchar(50) primary key,
    film_id       varchar(50) references film (film_id),
    room_id       varchar(50) references room (room_id),
    showtime_date timestamp not null,
    seats_data    varchar   not null
);

create table film_booking
(
    film_booking_id varchar(50) primary key,
    username        varchar(50) references user_info (username),
    showtime_id     varchar(50) references showtime (showtime_id),
    booking_date    timestamp    not null,
    seats           varchar(100) not null,
    total_fee       numeric      not null
);

create table genre
(
    genre_id   varchar(30) not null primary key,
    genre_name varchar(50) not null
);

create table film_genre
(
    film_id  varchar(50) references film (film_id),
    genre_id varchar(30) references genre (genre_id),
    primary key (film_id, genre_id)
);

create table food
(
    food_id    varchar(50)  not null primary key,
    food_name  varchar(100) not null,
    food_price numeric      not null
);

create table food_booking
(
    food_booking_id varchar(50) primary key,
    food_id         varchar(50) references food (food_id),
    film_booking_id varchar(50) references film_booking (film_booking_id)
);

create view v_showtime_details
as
select showtime.showtime_id, film.film_name, room.room_name, theater.theater_name, showtime.showtime_date
from showtime
         inner join film on showtime.film_id = film.film_id
         inner join room on showtime.room_id = room.room_id
         inner join theater on room.theater_id = theater.theater_id;

select *
from v_showtime_details;

insert into theater
values ('1', 'FB Thu Duc', '000000001', '1 Vo Van Ngan, Linh Chieu, Thu Duc');

select *
from theater;

insert into room
values ('1', 'R002', '1', 5, 6, '00000000')
returning room_id;

insert into room
values ('2', 'R003', '1', 5, 6, '00000000')
returning room_id;

insert into room
values ('3', 'R004', '1', 5, 6, '00000000')
returning room_id;

select *
from room;

select *
from user_info;

select *
from film;


insert into genre
values ('haikich', 'Hài kịch');

insert into genre
values ('tamly', 'Tâm lý');

select *
from genre;

-- insert into showtime values('1', '1', '1', '17-10-2023');

create view v_film_genre_details
as
select genre.genre_id, genre.genre_name, film_genre.film_id
from film_genre
         inner join genre on film_genre.genre_id = genre.genre_id;

create view v_room_details
as
select room.room_id, room.room_name, room.seat_rows, room.seat_cols, theater.theater_name
from room
         inner join theater on room.theater_id = theater.theater_id;

select *
from v_room_details;

set datestyle to Postgres, Europe;
insert into showtime
values ('2', '1', '1', '19/10/2023 10:06');
insert into showtime
values ('2', '1', '1', '20/10/2023 09:09');
select *
from showtime;