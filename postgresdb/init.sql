create table currency(
id varchar(255) primary key,
numcode varchar(255),
charcode varchar(255),
nominal varchar(255),
name varchar(255),
value numeric(5, 2),
previous numeric(5, 2)
);