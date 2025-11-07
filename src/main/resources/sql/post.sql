create table tbl_post
(
    id               bigint generated always as identity
        primary key,
    post_content     text   not null,
    post_status      status    default 'active'::status,
    member_id        bigint not null
        constraint fk_post_member
            references tbl_member,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    likes_count      integer   default 0
);