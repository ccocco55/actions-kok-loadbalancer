create table tbl_report
(
    id        bigint generated always as identity
        primary key,
    post_id   bigint not null
        constraint fk_report_post
            references tbl_post,
    member_id bigint not null
        constraint fk_report_member
            references tbl_member
);