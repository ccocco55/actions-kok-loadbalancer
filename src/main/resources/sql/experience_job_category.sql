create table tbl_experience_job_category
(
    experience_notice_id bigint not null
        primary key
        constraint fk_experience_job_category_user
            references tbl_experience_notice,
    job_category         bigint not null
        constraint fk_user_job_category_job_category
            references tbl_job_category
);