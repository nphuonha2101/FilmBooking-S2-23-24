create table user_infos
(
    username      varchar(50)  not null primary key,
    user_fullname varchar(255) not null,
    user_email    varchar(50)  not null,
    user_password varchar(64)  not null,
    account_role  varchar(10)  not null
);

create table films
(
    film_id           bigserial primary key,
    film_name         varchar(255) not null,
    film_price        numeric      not null,
    film_director     varchar(100) not null,
    film_cast         varchar(255) not null,
    film_length       int          not null,
    film_description  varchar      not null,
    film_trailer_link varchar,
    img_path          varchar(255) not null,
    slug              varchar(100) not null
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
    room_name  varchar(255)  not null,
    theater_id bigint references theaters (theater_id),
    seat_rows  int     not null,
    seat_cols  int     not null,
    seats_data varchar not null,
    slug       varchar(100) not null
);

create table showtimes
(
    showtime_id   bigserial primary key,
    film_id       bigint references films (film_id),
    room_id       bigint references rooms (room_id),
    showtime_date timestamp not null,
    seats_data    varchar   not null,
    slug          varchar(100) not null
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
    food_name  varchar(255) not null,
    food_price numeric      not null
);

create table food_bookings
(
    food_booking_id bigserial primary key,
    food_id         varchar(50) references foods (food_id),
    film_booking_id bigint references film_bookings (film_booking_id)
);


-- Genre
insert into genres (genre_id, genre_name) values ('haikich', 'Hài kịch');
insert into genres (genre_id, genre_name) values ('tamly', 'Tâm lý');
insert into genres (genre_id, genre_name) values ('tinhcam', 'Tình cảm');
insert into genres (genre_id, genre_name) values ('trinhtham', 'Trinh thám');
insert into genres (genre_id, genre_name) values ('hoathinh', 'Hoạt hình');
insert into genres (genre_id, genre_name) values ('vientuong', 'Viễn tưởng');
insert into genres (genre_id, genre_name) values ('hanhdong', 'Hành động');

-- Film
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (1, 'Oppenheimer', 85000, 'Christopher Nolan', 'Cillian Murphy, Emily Blunt, Matt Damon, Robert Downey Jr., Florence Pugh, Josh Hartnett, Casey Affleck, ...', 180, '“Oppenheimer” là một bộ phim điện ảnh tiểu sử, tái hiện câu chuyện có thật về quá trình tạo ra bom nguyên tử - thứ vũ khí huỷ diệt đã được thả xuống Hiroshima và Nagasaki, Nhật Bản vào năm 1945. Bộ phim đặt ra những câu hỏi, cũng chính là những băn khoăn mà các nhà khoa học đã đưa ra vào thời điểm đó về quyền lực, đạo đức và tham vọng.
<br>
<br>
Nhân vật chính của “Oppenheimer” là Julius Robert Oppenheimer, sinh năm 1904 tại New York. Ông là con trai của một người Đức gốc Do Thái nhập cư, có mẹ là họa sĩ và cha là nhà nhập khẩu dệt may. Thời niên thiếu, ông sở hữu thành tích học tập xuất sắc, liên tục học vượt lớp. Sau này Oppenheimer theo đuổi ngành Hóa học tại Đại học Harvard, rồi chuyển sang Đại học Cambridge để học tiếp. Ông từng công tác tại Đại học Leiden, Viện Công nghệ Liên bang Thụy Sĩ (ETH) và trở thành Phó giáo sư tại Berkeley khi trở về Mỹ.
<br>
<br>
Vào tháng 5/1942, một trong những giáo sư ở Harvard của Oppenheimer, lúc đó đang là Chủ tịch Ủy ban Nghiên cứu Quốc phòng, đã mời ông đến để nghiên cứu cách tính toán neutron cho một quả bom nguyên tử mới. Chỉ 1 tháng sau đó, “Dự án Manhattan” đi vào hoạt động, với Oppenheimer trở thành Giám đốc đầu tiên của phòng thí nghiệm Los Alamos.
<br>
<br>
Bộ phim “Oppenheimer” được đạo diễn bởi Christopher Nolan, một trong những đạo diễn hàng đầu của Hollywood. Dàn diễn viên thực lực của phim bao gồm Cillian Murphy, Emily Blunt, Matt Damon, Robert Downey Jr., Florence Pugh, Josh Hartnett, Casey Affleck, Rami Malek, Kenneth Branagh. Với chủ đề đặc biệt và dàn diễn viên thực lực, phim được kỳ vọng rất cao cả về chất lượng lẫn doanh thu.
<br>
<br>
“Oppenheimer” nhận về nhiều đánh giá tích cực cho cả kịch bản, kỹ xảo lẫn diễn xuất. Trên trang đánh giá phim Rotten Tomatoes, phim đạt điểm “cà chua tươi” với 93% nhận xét tích cực từ các nhà phê bình. Doanh thu toàn cầu của phim đã lên đến 522 triệu USD chỉ sau 17 ngày ra mắt, vượt cả “Tenet” ở mức 366 triệu USD.
<br>
<br>
Với ngân sách ước tính khoảng 100 triệu USD, mức doanh thu này của “Oppenheimer” thực sự rất khả quan, đặc biệt nếu xét về tính kén người xem của thể loại tiểu sử/chính kịch cũng như việc phim bị dán nhãn “R” (giới hạn đối với người dưới 17 tuổi).
<br>
<br>
Tóm lại, “Oppenheimer” là một bộ phim tiểu sử đầy ấn tượng, tái hiện cuộc đời và sự nghiệp của Julius Robert Oppenheimer - người được coi là “cha đẻ” của bom nguyên tử. Bộ phim không chỉ mô tả sự thật về quá trình tạo ra bom nguyên tử mà còn đặt ra những câu hỏi sâu sắc về quyền lực, đạo đức và tham vọng.', 'https://www.youtube.com/embed/bK6ldnjE3Y0?si=ci643qq82Iwn7mM7', '/resources/images/upload/3b4ead7c-e49d-458b-8b0c-bb4cdbefbb8b.webp', 'oppenheimer');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (2, 'The Zone of Interest', 120000, 'Jonathan Glazer', 'Christian Friedel, Sandra Hüller,...', 150, 'The Zone of Interest là một bộ phim lịch sử kinh điển, khắc họa cuộc sống đầy bi kịch và mâu thuẫn của những người tham gia vào hệ thống tàn sát của Đức Quốc Xã. Phim dựa trên tiểu thuyết cùng tên của Martin Amis, một trong những nhà văn hiện đại nổi tiếng nhất thế giới. Phim được đạo diễn bởi Florian Henckel von Donnersmarck, người đã từng đoạt giải Oscar với bộ phim The Lives of Others.
<br><br>
Phim xoay quanh ba nhân vật chính: Rudolf Höss, chỉ huy trại tập trung Auschwitz, người đã ra lệnh giết hàng triệu người Do Thái và các dân tộc thiểu số khác; Hedwig, vợ của Höss, người bị chồng lạnh nhạt và bạo hành, và bị cuốn vào mối quan hệ nguy hiểm với một sĩ quan SS khác; và Angelus Thomsen, một sĩ quan trẻ tuổi, người yêu Hedwig và là cháu trai của một quan chức cao cấp của Đức Quốc Xã.
<br><br>
Phim khám phá những bí mật, những lý do, và những hậu quả của những hành động khủng khiếp mà những người này đã thực hiện hoặc chứng kiến. Phim cũng đặt ra những câu hỏi về nhân tính, lương tâm, và trách nhiệm của con người trong những hoàn cảnh khắc nghiệt nhất. Phim không chỉ là một tác phẩm nghệ thuật độc đáo, mà còn là một bài học lịch sử quan trọng, nhắc nhở chúng ta về những gì đã xảy ra và những gì không bao giờ được lặp lại.', 'https://www.youtube.com/embed/0DmawoNe9Vk?si=89a6z2tvgSgBJn0-', '/resources/images/upload/28028313-2177-4a21-a31b-dc953c974983.webp', 'the-zone-of-interest');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (3, 'Người Vợ Cuối Cùng', 80000, 'Victor Vũ', 'Kaity Nguyễn, Thuận Nguyễn, NSƯT Quang Thắng, NSƯT Kim Oanh, Đinh Ngọc Diệp, Anh Dũng, Quốc Huy, Bé Lưu Ly, ...', 132, 'Người Vợ Cuối Cùng là một bộ phim cổ trang, tâm lý, tình cảm của đạo diễn Victor Vũ, dựa trên tiểu thuyết Hồ Oán Hận của nhà văn Hồng Thái. Phim kể về cuộc đời bi thảm của một người phụ nữ thời phong kiến, bị ép làm vợ cuối cùng của một quan lại độc ác. Phim có sự tham gia của các diễn viên như Kaity Nguyễn, Thuận Nguyễn, NSƯT Quang Thắng, NSƯT Kim Oanh, Đinh Ngọc Diệp, Anh Dũng, Quốc Huy, Bé Lưu Ly, và nhiều diễn viên khác. Phim có thời lượng là 132 phút và được khởi chiếu tại Việt Nam vào ngày 03/11/2023. Phim được phổ biến đến người xem từ đủ 18 tuổi trở lên (18+).
<br><br>
Phim xoay quanh ba nhân vật chính: Thanh Thanh, một cô gái xinh đẹp, hiền lành, nhưng bị cha mẹ bán làm vợ cuối cùng của quan lại Tống Thế Lân; Tống Thế Lân, một quan lại giàu có, quyền lực, nhưng tàn bạo, độc đoán, và sở hữu nhiều vợ lẫn thiếp; và Lý Văn Hải, một thiếu gia tài giỏi, đẹp trai, nhưng bị cha mẹ ép gả cho con gái của Tống Thế Lân. Ba nhân vật này bị cuốn vào một vòng xoáy của tình yêu, ghen tuông, oán hận, và báo thù, khiến cho cuộc sống của họ trở nên đầy khổ đau và bi kịch.
<br><br>
Phim là một tác phẩm nghệ thuật đẹp mắt, lãng mạn, nhưng cũng đầy ám ảnh, phản ánh một góc nhìn sâu sắc về xã hội phong kiến, nơi phụ nữ bị coi là đồ vật, bị sử dụng và bị hành hạ. Phim cũng là một bài học về nhân quả, về sự trả giá của những hành động ác độc, và về tình yêu chân thành, vượt qua mọi khó khăn. Phim không chỉ làm rung động trái tim người xem, mà còn khiến họ suy ngẫm về ý nghĩa của cuộc sống và hạnh phúc.', 'https://www.youtube.com/embed/ygvNCEbMusE?si=8O8GxTQLvu0LajYG', '/resources/images/upload/63a065d1-bf45-4e1a-8a80-1a242c23066b.webp', 'nguoi-vo-cuoi-cung');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (4, 'Wonka', 115000, 'Paul King', 'Timothée Chalamet, Hugh Grant, Rowan Atkinson, Matt Lucas, Mathew Baynton, ...', 116, 'Wonka là một bộ phim nhạc kịch, kỳ ảo, chính kịch dựa trên nhân vật Willy Wonka của Roald Dahl. Phim kể về hành trình trở thành nhà sản xuất sôcôla nổi tiếng của Willy Wonka khi còn trẻ. Phim do Paul King đạo diễn và có sự tham gia của Timothée Chalamet trong vai nhân vật chính Willy Wonka.
<br><br>
Phim bắt đầu với cảnh Willy Wonka, một cậu bé nghèo sống ở một thành phố đông đúc, đam mê sôcôla và mơ ước mở một cửa hàng riêng. Tuy nhiên, Willy Wonka phải đối mặt với nhiều khó khăn, từ sự thiếu thốn, bị bắt nạt, cho đến sự cạnh tranh khốc liệt của các nhà sản xuất sôcôla khác. Willy Wonka quyết tâm vượt qua mọi thử thách và tìm ra công thức sôcôla hoàn hảo, với sự giúp đỡ của một số người bạn đặc biệt.
<br><br>
Phim là một sự kết hợp tuyệt vời giữa âm nhạc, hài hước, và kỳ ảo, mang đến cho khán giả những trải nghiệm thú vị và bất ngờ. Phim cũng là một câu chuyện cảm động về tình bạn, tình yêu, và ước mơ, khuyến khích mọi người theo đuổi đam mê và không bỏ cuộc. Phim được đánh giá cao về mặt nghệ thuật, từ kịch bản, đạo diễn, diễn xuất, cho đến âm thanh, hình ảnh, và hiệu ứng. Phim đã nhận được nhiều giải thưởng và đề cử uy tín, như Oscar, Golden Globe, và BAFTA.', 'https://www.youtube.com/embed/otNh9bTjXWg?si=1U_4yZfs3Y5PJy8t', '/resources/images/upload/ff84667b-6bc0-4a4f-8b4b-3975feb54587.webp', 'wonka');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (5, 'Nhà Vịt Di Cư (Migration 2023)', 85000, 'Benjamin Renner', 'Kumail Nanjiani, Elizabeth Banks, Caspar Jennings, Tresi Gazal, Awkwafina, ... (lồng tiếng)', 83, 'Nhà Vịt Di Cư là một bộ phim hoạt hình hài phiêu lưu của Mỹ, do Benjamin Renner đạo diễn và có sự tham gia lồng tiếng của các diễn viên như Kumail Nanjiani, Elizabeth Banks, Caspar Jennings, Tresi Gazal, Awkwafina, và nhiều người khác. Phim kể về cuộc hành trình của một gia đình vịt trời khi họ lạc đường trong lần đầu tiên di cư về phía nam để trú đông. Phim có thời lượng là 83 phút và được khởi chiếu tại Việt Nam vào ngày 29 tháng 12 năm 2023. Phim thuộc thể loại hoạt hình, hài, phiêu lưu và được phép phổ biến đến người xem ở mọi độ tuổi.
<br><br>
Phim bắt đầu với cảnh Willy, một con vịt trời giàu kinh nghiệm, dẫn dắt gia đình và đàn vịt của mình bay lượn trên bầu trời, chuẩn bị cho chuyến di cư. Tuy nhiên, Willy bị một con chim ăn thịt tấn công và rơi xuống mặt đất, khiến cho gia đình và đàn vịt mất tích. Willy được một cậu bé tên là Max cứu sống và chăm sóc. Max là một đứa trẻ mồ côi, sống với ông nội tại một trang trại ngoại ô. Max rất yêu thích vịt và mong muốn có một người bạn.
<br><br>
Trong khi đó, gia đình và đàn vịt của Willy tiếp tục bay theo hướng nam, nhưng không biết rằng họ đã bay ngược chiều với tất cả các đàn vịt khác. Họ bất ngờ gặp phải loạt “chướng ngại vật” là những tòa nhà cao tầng, đường cao tốc, và những con người của thành phố hiện đại. Họ phải vượt qua nhiều khó khăn và nguy hiểm, từ sự săn đuổi của những con mèo, chó, đến sự truy lùng của những kẻ săn bắn. Họ cũng phải học cách thích nghi với môi trường mới, từ việc tìm kiếm thức ăn, nơi ẩn náu, cho đến việc kết bạn với những loài động vật khác.
<br><br>
Phim là một sự kết hợp tuyệt vời giữa hài hước, lãng mạn, và phiêu lưu, mang đến cho khán giả những trải nghiệm thú vị và bất ngờ. Phim cũng là một câu chuyện cảm động về tình yêu thương, sự gắn kết, và sự hy sinh của gia đình và bạn bè. Phim được đánh giá cao về mặt nghệ thuật, từ kịch bản, đạo diễn, lồng tiếng, cho đến âm thanh, hình ảnh, và hiệu ứng. Phim đã nhận được nhiều giải thưởng và đề cử uy tín, như Annie, Golden Globe, và Oscar.', 'https://www.youtube.com/embed/865RCGVYcSc?si=2KF8uq5xC9ATwyFc', '/resources/images/upload/515a0ddd-e228-4e07-8dbc-668a0d2abc2a.webp', 'nha-vit-di-cu-(migration-2023)');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (6, 'Arkie Và Ngày Mặt Trời Mất Tích', 90000, 'Ricard Cussó và Tania Vincent', 'Jillian Nguyen, Sam Neill, Rob Collins, Remy Hii, Anna Torv, .. (lồng tiếng).', 93, 'Arkie Và Ngày Mặt Trời Mất Tích là một bộ phim hoạt hình hài phiêu lưu của Úc, do Ricard Cussó và Tania Vincent đạo diễn. Phim kể về cuộc hành trình của một cô bé tên Arkie, cùng cha và bạn bè của cô, để cứu hành tinh của họ khỏi một nhà khoa học bí ẩn. Phim có sự lồng tiếng của các diễn viên như Jillian Nguyen, Sam Neill, Rob Collins, Remy Hii, Anna Torv, và nhiều người khác. Phim có thời lượng là 93 phút và được khởi chiếu tại Việt Nam vào ngày 29 tháng 12 năm 2023. Phim thuộc thể loại hoạt hình, hài, phiêu lưu và được phép phổ biến đến người xem ở mọi độ tuổi.
<br><br>
Phim bắt đầu với cảnh Arkie, một cô bé sống ở một hành tinh xa xôi, nơi mọi thứ đều có thể thay đổi hình dạng theo ý muốn. Arkie có một cha tên là Blister, một nhà phát minh tài ba, nhưng cũng rất lơ đễnh. Arkie luôn mong muốn được phiêu lưu và khám phá thế giới, nhưng cha cô luôn cấm cô ra khỏi nhà. Một ngày, Arkie phát hiện ra một chiếc máy bay bị rơi ở gần nhà mình, và trong đó có một chú chó robot tên là Snook. Arkie và Snook nhanh chóng trở thành bạn thân, và cùng nhau tìm hiểu về nguồn gốc của chiếc máy bay.
<br><br>
Họ phát hiện ra rằng chiếc máy bay thuộc về một nhà khoa học bí ẩn tên là Dr. Maybe, người đang âm mưu sử dụng một thiết bị khổng lồ để hút ánh sáng mặt trời, khiến cho hành tinh của Arkie và các hành tinh khác bị tối tăm và lạnh lẽo. Dr. Maybe cũng bắt cóc cha của Arkie, và đe dọa sẽ giết anh nếu Arkie không giao nộp chiếc máy bay, bởi vì nó chứa một linh kiện quan trọng cho kế hoạch của hắn. Arkie quyết định cùng Snook và một số người bạn mới là Zed, một chú khỉ đột thông minh, và Mumbot, một robot giống như một bà mẹ, lên đường để cứu cha của mình và ngăn chặn Dr. Maybe.
<br><br>
Phim là một sự kết hợp tuyệt vời giữa hài hước, lãng mạn, và phiêu lưu, mang đến cho khán giả những trải nghiệm thú vị và bất ngờ. Phim cũng là một câu chuyện cảm động về tình yêu thương, sự gắn kết, và sự hy sinh của gia đình và bạn bè. Phim được đánh giá cao về mặt nghệ thuật, từ kịch bản, đạo diễn, lồng tiếng, cho đến âm thanh, hình ảnh, và hiệu ứng. Phim đã nhận được nhiều giải thưởng và đề cử uy tín, như AACTA, Golden Globe, và Oscar.', 'https://www.youtube.com/embed/ObMnmEnLxrY?si=gNaMEf0PLjzAL_Mc', '/resources/images/upload/5de42d28-814a-4b49-afcf-518fb664211f.webp', 'arkie-va-ngay-mat-troi-mat-tich');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (7, 'Điều Ước (Wish 2023)', 100000, 'Chris Buck và Fawn Veerasunthorn', 'Chris Pine, Ariana DeBose, Evan Peters, ... (lồng tiếng)', 90, 'Wish 2023 là một bộ phim hoạt hình, nhạc kịch, hài hước, phiêu lưu của Walt Disney Animation Studios, dự kiến sẽ ra mắt vào ngày 24/11/2023 nhân dịp kỷ niệm 100 năm thành lập của hãng. Phim kể về cuộc phiêu lưu của một cô bé tên Asha, cùng ngôi sao điều ước Star, để cứu hành tinh của mình khỏi một nhà khoa học bí ẩn. Phim có thời lượng là 90 phút và được đạo diễn bởi Chris Buck và Fawn Veerasunthorn. Phim có sự lồng tiếng của các diễn viên như Chris Pine, Ariana DeBose, Alan Tudyk, và nhiều người khác.
<br><br>
Phim bắt đầu với cảnh Asha, một cô bé sống ở Vương quốc Rosas, nơi mọi thứ đều có thể thay đổi hình dạng theo ý muốn. Asha có một ước mơ lớn là trở thành một nhà thiết kế thời trang nổi tiếng, nhưng cha cô, vua Rosas, không đồng ý và muốn cô kết hôn với một hoàng tử của một vương quốc khác. Một đêm, Asha chạy trốn khỏi lâu đài và lên một ngọn đồi, nơi cô thấy một ngôi sao rơi xuống trước mặt cô. Cô tiếp cận ngôi sao và phát hiện ra rằng nó là một sinh vật sống, có tên là Star, và có khả năng thực hiện điều ước cho người khác. Star nói với Asha rằng nó là một trong những ngôi sao ước vọng, những ngôi sao được sinh ra từ những ước mơ của con người, và nó đang bị truy đuổi bởi một nhà khoa học bí ẩn tên là Dr. Maybe, người muốn bắt cóc tất cả các ngôi sao ước vọng để sử dụng năng lượng của chúng cho mục đích xấu. Star nói với Asha rằng nó cần sự giúp đỡ của cô để trở về với gia đình của nó ở vùng đất xa xôi, nơi có nguồn gốc của tất cả các ngôi sao ước vọng. Asha đồng ý giúp Star, và cùng nhau, họ bắt đầu một cuộc hành trình đầy thử thách và thú vị.
<br><br>
Phim là một sự kết hợp tuyệt vời giữa hài hước, lãng mạn, và phiêu lưu, mang đến cho khán giả những trải nghiệm thú vị và bất ngờ. Phim cũng là một câu chuyện cảm động về tình bạn, tình yêu, và ước mơ, khuyến khích mọi người theo đuổi đam mê và không bỏ cuộc. Phim được đánh giá cao về mặt nghệ thuật, từ kịch bản, đạo diễn, lồng tiếng, cho đến âm thanh, hình ảnh, và hiệu ứng. Phim đã nhận được nhiều giải thưởng và đề cử uy tín, như Annie, Golden Globe, và Oscar.', '', '/resources/images/upload/78fa021d-1e8d-48f9-ac6d-ebf3f12e8072.webp', 'đieu-uoc-(wish-2023)');
insert into films (film_id, film_name, film_price, film_director, film_cast, film_length, film_description, film_trailer_link, img_path, slug) values (8, 'Aquaman Và Vương Quốc Thất Lạc', 115000, 'James Wan', 'Jason Momoa, Patrick Wilson, Amber Heard, Yahya Abdul-Mateen II, Willem Dafoe, Dolph Lundgren, Temuera Morrison, và Nicole Kidman, ...', 124, 'Aquaman Và Vương Quốc Thất Lạc là một bộ phim siêu anh hùng của Mỹ, dự kiến sẽ ra mắt vào năm 2023. Phim là phần tiếp theo của Aquaman: Đế vương Atlantis (2018) và là bộ phim thứ mười ba của Vũ trụ Mở rộng DC (DCEU). Phim kể về cuộc phiêu lưu của Aquaman / Arthur Curry để cứu hành tinh của mình khỏi một nhà khoa học bí ẩn. Phim có thời lượng là 124 phút và được đạo diễn bởi James Wan. Phim có sự tham gia của các diễn viên như Jason Momoa, Patrick Wilson, Amber Heard, Yahya Abdul-Mateen II, Nicole Kidman, Dolph Lundgren, và nhiều người khác. Phim thuộc thể loại hành động, kỳ ảo, chính kịch và được phép phổ biến đến người xem từ đủ 16 tuổi trở lên (16+).
<br><br>
Phim bắt đầu với cảnh David Kane, một tên tội phạm biển còn được biết đến với biệt danh Black Manta, tìm thấy một cây đinh ba đen, một vũ khí cổ xưa có thể hồi sinh đội quân quái vật của vua Atlan, người đã tạo ra Atlantis. Kane bị ám ảnh bởi sự trả thù đối với Aquaman, người đã để cha anh chết trong một cuộc đụng độ trước đó. Kane quyết định sử dụng cây đinh ba đen để tấn công Atlantis và hủy diệt Aquaman.
<br><br>
Trong khi đó, Aquaman, người đã trở thành vua của Atlantis, đang phải đối mặt với nhiều khó khăn trong việc duy trì hòa bình giữa các vương quốc dưới biển và thế giới trên mặt đất. Anh cũng phải giải quyết mối quan hệ phức tạp với Mera, nàng công chúa của Xebel, người đã giúp anh chiến đấu với Orm, em trai của anh, trong phần phim trước. Aquaman nhận được sự giúp đỡ của Vulko, cố vấn trung thành của anh, và Nuidis Vulko, một nhà nghiên cứu về lịch sử và văn hóa Atlantis. Họ tiết lộ cho anh về một vương quốc thất lạc của Atlantis, nơi có thể chứa bí mật để ngăn chặn Kane và cây đinh ba đen.
<br><br>
Aquaman, Mera, Vulko, và Nuidis Vulko lên đường để tìm kiếm vương quốc thất lạc, đối mặt với nhiều nguy hiểm và thử thách trên đường đi. Họ cũng phải đối đầu với Kane, người đã liên kết với một nhà khoa học bí ẩn tên là Dr. Maybe, người có kế hoạch sử dụng năng lượng của cây đinh ba đen để thực hiện một thí nghiệm khủng khiếp. Liệu Aquaman có thể cứu được Atlantis và thế giới trước khi quá muộn hay không? Phim sẽ mang đến cho khán giả câu trả lời trong m', 'https://www.youtube.com/embed/keZ70jipjXc?si=GGMMl56XGUxswR71', '/resources/images/upload/0108317b-b19d-4026-b656-4f0624520afb.webp', 'aquaman-va-vuong-quoc-that-lac');

-- Film Genre
insert into film_genres (film_id, genre_id)
values  (4, 'haikich'),
        (5, 'haikich'),
        (6, 'haikich'),
        (7, 'haikich'),
        (8, 'hanhdong'),
        (5, 'hoathinh'),
        (6, 'hoathinh'),
        (7, 'hoathinh'),
        (1, 'khoahoc'),
        (4, 'kyao'),
        (8, 'kyao'),
        (5, 'phieuluu'),
        (6, 'phieuluu'),
        (7, 'phieuluu'),
        (1, 'tailieu'),
        (2, 'tailieu'),
        (1, 'tamly'),
        (2, 'tamly'),
        (3, 'tamly'),
        (3, 'tinhcam');


-- Theater
insert into theaters (theater_name, tax_code, theater_address) values ('FilmBooking Thủ Đức', '000000001', '1 Võ Văn Ngân, Linh Chiểu, Thủ Đức');
insert into theaters (theater_name, tax_code, theater_address) values ('FilmBooking Bến Thành', '000000238', 'Trương Định, Bến Thành, Q.1');
insert into theaters (theater_name, tax_code, theater_address) values ('FilmBooking Bình Thạnh', '000000034', 'Điện Biên Phủ, P16, Q.Bình Thạnh');
