create table tbl_company_notice_alarm
(
    id                      bigint generated always as identity
        primary key,
    member_alarm_setting_id bigint not null
        constraint fk_company_notice_alarm_member_alarm_setting
            references tbl_member_alarm_setting,
    follow_id               bigint not null
        constraint fk_company_notice_alarm_follow
            references tbl_follow,
    experience_notice_id    bigint not null
        constraint fk_company_notice_alarm_experience_notice
            references tbl_experience_notice,
    intern_notice_id        bigint not null
        constraint fk_company_notice_intern_notice
            references tbl_intern_notice,
    member_id               bigint not null
        constraint fk_company_notice_alarm_member
            references tbl_member,
    created_datetime        timestamp default now(),
    updated_datetime        timestamp default now()
);