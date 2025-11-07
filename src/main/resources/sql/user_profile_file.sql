create table tbl_user_profile_file
(
    file_id bigint not null
        primary key
        constraint fk_user_profile_file_file
            references tbl_file,
    user_id bigint not null
        constraint fk_user_profile_file_user
            references tbl_user
);