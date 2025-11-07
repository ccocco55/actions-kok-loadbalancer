create table tbl_save_intern_notice
(
    id bigint generated always as identity
        primary key,
    member_id        bigint not null
        constraint fk_save_intern_notice_member
            references tbl_member,
    intern_notice_id bigint not null
        constraint fk_save_intern_notice_intern_notice
            references tbl_intern_notice,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

drop table tbl_save_intern_notice;