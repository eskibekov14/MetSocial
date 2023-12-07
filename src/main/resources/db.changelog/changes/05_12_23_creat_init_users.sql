create table permissions(
    id serial primary key ,
    role varchar(50)
);

create table users(
    id serial primary key ,
    email varchar(255),
    full_name varchar(50),
    age int,
    address varchar(50),
    gender varchar(50),
    about varchar(255),
    password varchar(255)

);

create table users_permissions(
    users_id int references users(id),
    permissions_id int references permissions(id),
    primary key (users_id, permissions_id)
)
