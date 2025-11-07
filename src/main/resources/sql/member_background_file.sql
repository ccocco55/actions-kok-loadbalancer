create table tbl_member_background_file
(
    file_id   bigint not null
        constraint fk_member_background_file_file
            references tbl_file,
    member_id bigint not null
        constraint fk_member_background_file_user
            references tbl_member
);