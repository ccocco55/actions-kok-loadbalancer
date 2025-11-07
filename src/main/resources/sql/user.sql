create type request_status as enum('await','accept','reject');

create type status as enum('active', 'inactive');

create type role as enum('member', 'admin', 'company');

create table tbl_user
(
    id               bigint generated always as identity
        primary key,
    user_name        varchar(255)                       not null,
    user_phone       varchar(255)                       not null,
    user_email       varchar(255)
        unique,
    user_password    varchar(255),
    user_role        role                               not null,
    user_status      status    default 'active'::status not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    sns_email        varchar(255)
);

select * from tbl_user;