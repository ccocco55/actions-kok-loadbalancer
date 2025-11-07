create table tbl_request_intern
(
    id                          bigint generated always as identity
        primary key,
    request_intern_status       request_status default 'await'::request_status not null,
    member_id                   bigint                                         not null
        constraint fk_request_intern_member
            references tbl_member,
    intern_notice_id            bigint                                         not null
        constraint fk_request_intern_intern_notice
            references tbl_intern_notice,
    member_alarm_setting_id     bigint                                         not null
        constraint fk_request_intern_member_alarm_setting
            references tbl_member_alarm_setting,
    created_datetime            timestamp      default now(),
    updated_datetime            timestamp      default now(),
    request_intern_member_name  varchar(255)                                   not null,
    request_intern_member_email varchar(255)                                   not null,
    request_intern_member_phone varchar(255)                                   not null,
    file_id                     bigint                                         not null
        constraint fk_request_intern_file
            references tbl_file,
    request_intern_member_url   varchar(255),
    request_intern_active       status         default 'active'::status        not null
);