create table tbl_request_experience
(
    id                              bigint generated always as identity
        primary key,
    request_experience_status       request_status default 'await'::request_status not null,
    experience_notice_id            bigint                                         not null
        constraint fk_request_experience_experience_notice
            references tbl_experience_notice,
    member_id                       bigint                                         not null
        constraint fk_request_experience_member
            references tbl_member,
    member_alarm_setting_id         bigint                                         not null
        constraint fk_request_experience_member_alarm_setting
            references tbl_member_alarm_setting,
    created_datetime                timestamp      default now(),
    updated_datetime                timestamp      default now(),
    request_experience_member_name  varchar(255)                                   not null,
    request_experience_member_email varchar(255)                                   not null,
    request_experience_member_phone varchar(255)                                   not null,
    file_id                         bigint                                         not null
        constraint fk_request_experience_file
            references tbl_file,
    request_experience_member_url   varchar(255),
    request_experience_active       status         default 'active'::status        not null
);
