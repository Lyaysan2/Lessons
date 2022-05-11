create table teacher(
                        id serial primary key,
                        first_name varchar(30) not null default 'first_name',
                        last_name varchar(30) not null default 'last_name',
                        email varchar(100) not null unique,
                        subject varchar(50),
                        password varchar(100),
                        avatar_id integer
);

drop table teacher;


create table file_info(
                          id serial primary key,
                          original_file_name varchar(100),
                          storage_file_name varchar(100) not null,
                          size bigint,
                          type varchar(100)
);

drop table file_info;



