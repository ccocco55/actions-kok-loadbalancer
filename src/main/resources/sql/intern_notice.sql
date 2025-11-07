create table tbl_intern_notice
(
    id                          bigint generated always as identity
        primary key,
    intern_notice_title         varchar(255)                       not null,
    intern_notice_subtitle      varchar(255)                       not null,
    intern_notice_introduce_job varchar(255)                       not null,
    intern_main_tasks           varchar(255)                       not null,
    intern_notice_status        status    default 'active'::status not null,
    company_id                  bigint                             not null
        constraint fk_intern_notice_company
            references tbl_company,
    created_datetime            timestamp default now(),
    updated_datetime            timestamp default now(),
    intern_notice_start_date    date                               not null,
    intern_notice_end_date      date                               not null,
    intern_notice_etc           varchar(255)                       not null
);