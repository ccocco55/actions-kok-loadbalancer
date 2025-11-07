create table tbl_post_file
(
    file_id bigint not null
        constraint fk_post_file_file
            references tbl_file,
    post_id bigint not null
        constraint fk_post_file_post
            references tbl_post
);