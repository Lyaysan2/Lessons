create table users(
                        id serial primary key,
                        first_name varchar(50),
                        last_name varchar(50),
                        age integer,
                        city varchar(30),
                        email varchar(50) not null unique,
                        password varchar(100),
                        avatar_id integer
);

create table files(
                          id serial primary key,
                          original_file_name varchar(100),
                          storage_file_name varchar(100) not null,
                          size bigint,
                          type varchar(100)
);

create table books(
    id serial primary key,
    name varchar(100),
    author varchar(100),
    year integer,
    description varchar(300),
    price integer,
    seller_id integer,
    image_id integer
);

create table comments(
    id serial primary key,
    user_id integer,
    book_id integer,
    text varchar(255),
    created_at timestamp
);

create table cart(
    id serial primary key,
    user_id integer,
    book_id integer
);

