create table tbl_reply
(
    id                      bigint generated always as identity
        primary key,
    reply_content           text                               not null,
    reply_status            status    default 'active'::status not null,
    created_datetime        timestamp default now(),
    updated_datetime        timestamp default now(),
    member_id               bigint                             not null
        constraint fk_reply_member
            references tbl_member,
    comment_id              bigint                             not null
        constraint fk_reply_comment
            references tbl_comment,
    member_alarm_setting_id bigint
);