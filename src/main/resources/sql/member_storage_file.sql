create table tbl_member_storage_file
(
    file_id   bigint not null
        constraint fk_member_storage_file_file
            references tbl_file,
    member_id bigint not null
        constraint fk_member_storage_file_member
            references tbl_member
);