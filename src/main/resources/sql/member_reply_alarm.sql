create table tbl_member_reply_alarm
(
    id                      bigint generated always as identity
        primary key,
    member_id               bigint not null
        constraint fk_member_reply_alarm_member
            references tbl_member,
    comment_id              bigint not null
        constraint fk_member_reply_alarm_comment
            references tbl_comment,
    reply_id                bigint not null
        constraint fk_member_reply_alarm_reply
            references tbl_reply,
    member_alarm_setting_id bigint not null
        constraint fk_member_reply_alarm_member_alarm_setting
            references tbl_member_alarm_setting,
    created_datetime        timestamp default now(),
    updated_datetime        timestamp default now()
);