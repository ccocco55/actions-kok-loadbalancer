create table tbl_request_intern_file
(
    file_id           bigint not null
        constraint fk_request_intern_file_file
            references tbl_file,
    request_intern_id bigint not null
        primary key
        constraint fk_request_intern_file_request_intern
            references tbl_request_intern
);

drop table tbl_request_intern_file;