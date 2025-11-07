create table tbl_experience_notice
(
    id                              bigint generated always as identity
        primary key,
    experience_notice_title         varchar(255) not null,
    experience_notice_subtitle      varchar(255) not null,
    experience_notice_introduce_job varchar(255) not null,
    experience_notice_etc           varchar(255) not null,
    experience_start_date           date         not null,
    experience_end_date             date         not null,
    experience_notice_status        status    default 'inactive'::status,
    created_datetime                timestamp default now(),
    updated_datetime                timestamp default now(),
    company_id                      bigint       not null
        constraint fk_experience_notice_company
            references tbl_company,
    experience_main_tasks           varchar(255),
    experience_notice_start_date    date         not null,
    experience_notice_end_date      date         not null
);
select * from tbl_experience_notice;
insert into tbl_experience_job_category (experience_notice_id, job_category) VALUES (4,2);
insert into tbl_experience_notice (experience_notice_title, experience_notice_subtitle, experience_notice_introduce_job, experience_notice_etc, experience_start_date, experience_end_date, company_id, experience_main_tasks, experience_notice_start_date, experience_notice_end_date)
VALUES ('AI Developer', 'AI 개발자','소개','참고','2025-10-27','2026-11-25',3,'업무','2025-10-17','2026-11-25');


-- 순서대로
ALTER TABLE tbl_experience_notice
    ALTER COLUMN experience_notice_status SET DEFAULT 'active'::status;
ALTER TABLE tbl_experience_notice
    ALTER COLUMN experience_notice_status SET NOT NULL;

ALTER TABLE tbl_experience_notice
    ALTER COLUMN experience_main_tasks SET DEFAULT '';
ALTER TABLE tbl_experience_notice
    ALTER COLUMN experience_main_tasks SET NOT NULL;

ALTER TABLE tbl_experience_notice
    ALTER COLUMN company_id SET NOT NULL;
