create table tbl_user_job_category
(
    user_id      bigint not null
        primary key
        constraint fk_user_job_category_user
            references tbl_user,
    job_category bigint not null
        constraint fk_user_job_category_job_category
            references tbl_job_category
);