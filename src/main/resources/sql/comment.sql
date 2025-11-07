create table tbl_comment
(
    id               bigint generated always as identity
        primary key,
    comment_content  text                               not null,
    comment_status   status    default 'active'::status not null,
    member_id        bigint                             not null
        constraint fk_comment_member
            references tbl_member,
    post_id          bigint                             not null
        constraint fk_comment_post
            references tbl_post,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);