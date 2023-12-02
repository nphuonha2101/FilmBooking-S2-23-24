create table user_infos
(
    username      varchar(50)  not null primary key,
    user_fullname varchar(100) not null,
    user_email    varchar(50)  not null,
    user_password varchar      not null,
    account_role  varchar(10)  not null
);

create table films
(
    film_id           bigserial primary key,
    film_name         varchar(100) not null,
    film_price        numeric      not null,
    film_director     varchar(100) not null,
    film_cast         varchar(255) not null,
    film_length       int          not null,
    film_description  varchar,
    film_trailer_link varchar,
    img_path          varchar(255) not null
);

create table theaters
(
    theater_id      bigserial primary key,
    theater_name    varchar(255) not null,
    tax_code        varchar(20)  not null,
    theater_address varchar      not null
);

create table rooms
(
    room_id    bigserial primary key,
    room_name  varchar(50),
    theater_id bigint references theaters (theater_id),
    seat_rows  int     not null,
    seat_cols  int     not null,
    seats_data varchar not null
);

create table showtimes
(
    showtime_id   bigserial primary key,
    film_id       bigint references films (film_id),
    room_id       bigint references rooms (room_id),
    showtime_date timestamp not null,
    seats_data    varchar   not null
);

create table film_bookings
(
    film_booking_id bigserial primary key,
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
    food_name  varchar(100) not null,
    food_price numeric      not null
);

create table food_bookings
(
    food_booking_id bigserial primary key,
    food_id         varchar(50) references foods (food_id),
    film_booking_id bigint references film_bookings (film_booking_id)
);



-- Film

INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('UP (2009)', 60000, 'Pete Docter, Blob Peterson', 'Ed Asner, Jordan Nagai, Christopher Plummer', 90, 'Phim tập trung vào Carl Fredricksen, một người góa phụ già đi đến Nam Mỹ cùng với Russell, một hướng đạo sinh, để thực hiện lời hứa mà ông đã hứa với vợ quá cố của mình, Ellie. Trên đường đi, họ gặp một con chó biết nói tên là Dug và gặp một con chim khổng lồ tên là Kevin, người đang bị thợ săn Charles Muntz - người mà Carl từng ngưỡng mộ từ thời thơ ấu - săn đuổi', 'https://www.youtube.com/embed/pkqzFUhGPJg?si=ojO_TjUW3ZWnLmG0', '/resources/images/upload/f5d39307-328d-4da1-b417-743c1994817f.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Sing', 60000, 'Garth Jennings, Christophe Lourdelet', 'Matthew McConaughey, Reese Witherspoon, Seth MacFarlane, ...', 80, 'Phim kể về một chú gấu trúc tên Buster Moon, người quản lý một nhà hát đã xuống cấp. Ông quyết định tổ chức một cuộc thi âm nhạc để cứu nhà hát khỏi phá sản. Các thí sinh tham gia cuộc thi là những con vật có đủ sự đa dạng: từ chú khỉ đam mê rock, chú voi có giọng ca ngọt ngào, đến cô gái chuột có ước mơ trở thành ngôi sao pop.', 'https://www.youtube.com/embed/9qPgK_u4vX8?si=eHjqINHvupyNfMNP', '/resources/images/upload/a5ffde0c-147b-4bf5-b5c7-1fd14202560e.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Cô Ba Sài Gòn', 59000, 'Trần Bửu Lộc', 'NSND Hồng Vân, Diễm My, Ngô Thanh Vân, ...', 86, e'Mô tả: Phim lấy bối cảnh chính là Sài Gòn (hay Thành phố Hồ Chí Minh) của hai năm 1969 và 2017. Truyện phim lấy chủ đề thời trang kết hợp yếu tố xuyên không kỳ ảo. Chủ đề chính của phim là tôn vinh áo dài, trang phục truyền thống của Việt Nam.
<br>
<br>
Phim có buổi công chiếu đầu tiên trên toàn thế giới vào ngày 14 tháng 10 năm 2017 tại Liên hoan phim quốc tế Busan lần thứ 22 ở Hàn Quốc trước khi ra mắt công chúng Việt Nam vào ngày 10 tháng 11 cùng năm.', 'https://www.youtube.com/embed/E_Y4z8gJjvM?si=1awFAHzJwHt_oNmr', '/resources/images/upload/6308ca57-ee50-4c54-8e30-0c9e790e5a61.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Nhắm Mắt Thấy Mùa Hè', 75000, 'Cao Thuý Nhi', 'Phương Anh Đào, Takafumi Akutsu', 89, e'Mô tả: Phim kể về hành trình tâm lý của Hạ và Akira, nói về tình yêu của hai bạn trẻ người Việt Nam và người Nhật Bản.
<br>
<br>
Phim là một câu chuyện giản đơn nhưng lại cuốn khán giả vào một vòng xoáy cảm xúc vô tận, lúc thì vui vẻ hớn hở, lúc thì cảm thấy vô vọng, và đôi khi bộ phim còn lấy đi cả những giọt nước mắt cùng tiếng nấc nghẹn ngào của khán giả.', 'https://www.youtube.com/embed/MVyBEJ74ryw?si=17Hd6RhN-_gztfmD', '/resources/images/upload/98e3563b-e087-43de-bca5-a764cefbc42b.webp');
INSERT INTO public.films ( film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Black Panther (2018)', 68000, 'Ryan Coogler', 'T''Challa', 90, e'Phim kể về T’Challa, người kế nhiệm ngai vàng Wakanda sau cái chết của cha mình. T’Challa trở về quê hương Wakanda, một quốc gia châu Phi tiên tiến và cô lập, để trở thành vị vua mới. Tuy nhiên, T’Challa sớm phát hiện ra rằng anh đang bị thách thức cho ngôi vương từ các phe phái trong chính quốc gia của mình.
<br>
<br>
Khi hai kẻ thù âm mưu phá hủy Wakanda, anh hùng được biết đến với tên gọi Black Panther phải hợp tác với đặc vụ C.I.A. Everett K. Ross và các thành viên của Dora Milaje, lực lượng đặc biệt của Wakanda, để ngăn chặn Wakanda rơi vào cuộc chiến thế giới', 'https://www.youtube.com/embed/xjDjIWPwcPU?si=qNn8Cjkc8u3q-LxT', '/resources/images/upload/dc8455d7-ecb8-4473-8e5a-94519a76f12e.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Em Chưa 18', 72000, 'Lê Thanh Sơn', 'Kiều Minh Tuấn, Kaity Nguyễn, Will, Quang Minh, Huy Khánh', 102, e'Phim kể về chuyện tình giữa Hoàng, một huấn luyện viên Yoga và cũng là một tay chơi sát gái, và Linh Đan, một cô gái trẻ chưa đủ 18 tuổi.
<br>
<br>
Trong một lần đi bar, Hoàng đã có một đêm tình cùng Linh Đan. Tuy nhiên, sáng hôm sau, anh mới tá hỏa khi biết Linh Đan chưa đủ 18 tuổi. Linh Đan đã quay lại video cảnh “ân ái” giữa hai người và dọa tố cáo Hoàng nếu anh không chịu làm bạn trai của cô', 'https://www.youtube.com/embed/JumMd7g61Gw?si=PetMADUVEHSpXU9P', '/resources/images/upload/9adeb368-9afd-4420-8352-78908abdd9cb.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Mắt Biếc', 65000, 'Victor Vũ', 'Trần Nghĩa, Trúc Anh, Khánh Vân', 89, 'Phim xoay quanh mối tình đơn phương của Ngạn với Hà Lan, cô bạn gái có cặp mắt hút hồn nhưng cá tính bướng bỉnh. Một chuyện tình nhiều cung bậc, từ ngộ nghĩnh trẻ con, rồi tình yêu thuở học trò trong sáng, trải qua bao biến cố, trở thành một cuộc “đuổi hình bắt bóng” buồn da diết nhưng không nguôi hi vọng', 'https://www.youtube.com/embed/KSFS0OfIK2c?si=PvkKosAyW5vX4aMu', '/resources/images/upload/72fee823-3a2b-440a-94d3-6604620c07f7.webp');
INSERT INTO public.films (film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path) VALUES ('Avengers: Endgame', 70000, 'Anthony Russo, Joe Russo', 'Chris Evans, Robert Downey Jr., Mark Ruffalo, ...', 105, e'“Avengers: Hồi kết” (tựa gốc tiếng Anh: Avengers: Endgame) là phim điện ảnh siêu anh hùng Mỹ ra mắt năm 2019, do Marvel Studios sản xuất và Walt Disney Studios Motion Pictures phân phối độc quyền tại thị trường Bắc Mỹ.
<br>
<br>
Phim là phần thứ tư của loạt phim Avengers, sau Biệt đội siêu anh hùng1. Trong phim, giấc mơ của ác nhân Thanos (Josh Brolin thủ vai) là cân bằng một cách hoàn hảo vũ trụ khi quyết định xóa sổ một nửa số sinh linh tồn tại.
<br>
<br>
“Endgame” tiếp nối những sự kiện của “Avengers: Infinity War” (2018) khi Thanos thu thập đủ sáu viên đá Vô Cực và búng tay khiến một nửa sinh linh của vũ trụ tan biến2. Những người may mắn sống sót trong trận đại chiến tại Wakanda như Captain America (Chris Evans), Thor (Chris Hemsworth), Hulk (Mark Ruffalo) hay Black Widow (Scarlett Johansson) hay trôi dạt trong vũ trụ như Iron Man (Robert Downey Jr.) đều mang nỗi ám ảnh khôn nguôi.
<br>
<br>
Một ngày nọ khi tất cả tưởng như đã buông xuôi, một tia sáng hy vọng bất ngờ xuất hiện và giúp nhóm Avengers có cơ hội thay đổi thế cờ', 'https://www.youtube.com/embed/TcMBFSGVi1c?si=7S8P7VioXCRt65dY', '/resources/images/upload/74491b74-855f-436a-b628-9068707855bf.webp');


-- Genre
INSERT INTO public.genres (genre_id, genre_name) VALUES ('haikich', 'Hài kịch');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('tamly', 'Tâm lý');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('tinhcam', 'Tình cảm');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('trinhtham', 'Trinh thám');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('hoathinh', 'Hoạt hình');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('vientuong', 'Viễn tưởng');
INSERT INTO public.genres (genre_id, genre_name) VALUES ('hanhdong', 'Hành động');


-- Theater
INSERT INTO public.theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Thủ Đức', '000000001', '1 Võ Văn Ngân, Linh Chiểu, Thủ Đức');
INSERT INTO public.theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Bến Thành', '000000238', 'Trương Định, Bến Thành, Q.1');
INSERT INTO public.theaters (theater_name, tax_code, theater_address) VALUES ('FilmBooking Bình Thạnh', '000000034', 'Điện Biên Phủ, P16, Q.Bình Thạnh');

select * from showtimes;
select * from user_infos;
select * from film_bookings;