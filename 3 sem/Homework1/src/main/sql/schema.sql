create table teacher(
                        id serial primary key,
                        first_name varchar(30) not null default 'first_name',
                        last_name varchar(30) not null default 'last_name',
                        experience integer not null default 0,
                        subject varchar(30)
);