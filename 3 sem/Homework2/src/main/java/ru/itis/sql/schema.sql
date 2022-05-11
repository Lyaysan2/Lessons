create table teacher(
                        id serial primary key,
                        first_name varchar(30) not null default 'first_name',
                        last_name varchar(30) not null default 'last_name',
                        subject varchar(30),
                        password varchar(40)
);

drop table teacher;

insert into teacher(first_name, last_name, subject, password) VALUES
                                                                     ('maria', 'mosen', 'math', 'qwerty'),
                                                                     ('denni', 'wick', 'history', '000'),
                                                                     ('herman', 'strother', 'infa', '1234'),
                                                                     ('lyaysan', 'zaynullina', 'data base', 'qazwsx');

