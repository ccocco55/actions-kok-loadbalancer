create table tbl_request_experience_file
(
    file_id               bigint not null
        constraint fk_request_experience_file_file
            references tbl_file,
    request_experience_id bigint not null
        primary key
        constraint fk_request_experience_file_request_experience
            references tbl_request_experience
);

drop table tbl_request_experience_file;