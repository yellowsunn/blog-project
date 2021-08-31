create table account (
     account_id bigint not null auto_increment,
     password varchar(255) not null,
     username varchar(255) not null unique,
     role varchar(255) not null,
     primary key (account_id)
);

create table category (
    category_id bigint not null auto_increment,
    name varchar(255) not null,
    orders bigint default 0,
    parent_category_id bigint,
    primary key (category_id),
    foreign key (parent_category_id) references category (category_id)
);

create table article (
    article_id bigint not null auto_increment,
    title varchar(255),
    writer varchar(255),
    content mediumtext,
    date datetime,
    hit bigint default 0,
    article_like bigint default 0,
    like_id varchar(50),
    category_id bigint,
    thumbnail_id bigint,
    primary key (article_id),
    foreign key (category_id) references category (category_id)
);

create table image (
   image_id bigint not null auto_increment,
   name varchar(255) not null,
   article_id bigint,
   primary key (image_id),
   foreign key (article_id) references article (article_id)
);

alter table article add foreign key (thumbnail_id) references image (image_id);

create table comment (
     comment_id bigint not null auto_increment,
     name varchar(255),
     password varchar(255),
     content varchar(2048),
     ip_addr varchar(255),
     order_number varchar(255),
     is_manager boolean default false,
     date datetime,
     article_id bigint,
     main_comment_id bigint,
     primary key (comment_id),
     foreign key (article_id) references article(article_id),
     foreign key (main_comment_id) references comment(comment_id)
);

create table cover (
    cover_id bigint not null auto_increment,
    title varchar(255),
    slogun_title varchar(255),
    slogun_text varchar(255),
    cover_article_category_id bigint,
    cover_category_id bigint,
    profile_id bigint,
    profile_text varchar(255),
    foreign key (cover_article_category_id) references category (category_id),
    foreign key (cover_category_id) references category (category_id),
    foreign key (profile_id) references image (image_id),
    primary key (cover_id)
);

insert into account (username, password, role) values ('yellowsunn', '{bcrypt}$2y$12$SxlPlPPSS/9sCKG7ZNB/AOay1VPCvxYfvFovB0Ttu0ghG545lJrRm', 'ROLE_ADMIN');

insert into cover (title, slogun_title, slogun_text, profile_text) values
(
    '테스트 제목',
    '테스트 타이틀',
    '테스트 텍스트',
    '테스트 프로필 텍스트'
);




