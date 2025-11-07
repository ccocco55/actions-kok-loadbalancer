create table tbl_post_like
(
    id                      bigint generated always as identity
        primary key,
    member_id               bigint not null
        constraint fk_post_like_member
            references tbl_member,
    post_id                 bigint not null
        constraint fk_post_like_post
            references tbl_post,
    member_alarm_setting_id bigint
        constraint fk_post_like_member_alarm_setting
            references tbl_member_alarm_setting,
    created_datetime        timestamp default now(),
    updated_datetime        timestamp default now()
);