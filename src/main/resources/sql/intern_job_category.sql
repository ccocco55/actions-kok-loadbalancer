create table tbl_intern_job_category
(
    intern_notice_id bigint not null
        primary key
        constraint fk_intern_job_category_user
            references tbl_intern_notice,
    job_category     bigint not null
        constraint fk_user_job_category_job_category
            references tbl_job_category
);