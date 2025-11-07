create table tbl_member_comment_alarm
(
    id                      bigint generated always as identity
        primary key,
    created_datetime        timestamp default now(),
    updated_datetime        timestamp default now(),
    member_id               bigint not null
        constraint fk_member_comment_alarm_member
            references tbl_member,
    post_id                 bigint not null
        constraint fk_member_comment_alarm_post
            references tbl_post,
    comment_id              bigint not null
        constraint fk_member_comment_alarm_comment
            references tbl_comment,
    member_alarm_setting_id bigint not null
        constraint fk_member_comment_alarm_member_alarm_setting
            references tbl_member_alarm_setting
);