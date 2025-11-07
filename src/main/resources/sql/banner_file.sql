create table tbl_banner_file
(
    id                       bigint generated always as identity
        primary key,
    banner_file_origin_name  varchar(255) not null,
    banner_file_name         varchar(255) not null,
    banner_file_path         varchar(255) not null,
    banner_file_size         varchar(255) not null,
    banner_file_content_type varchar(255) not null,
    created_datetime         timestamp default now(),
    updated_datetime         timestamp default now()
);

