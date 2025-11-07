create table tbl_admin_notice
(
    id                   bigint generated always as identity
        primary key,
    admin_notice_title   varchar(255)                       not null,
    admin_notice_content varchar(255)                       not null,
    notice_status        status    default 'active'::status not null,
    created_datetime     timestamp default now(),
    updated_datetime     timestamp default now()
);