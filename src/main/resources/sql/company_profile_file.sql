create table tbl_company_profile_file
(
    file_id    bigint not null
        primary key
        constraint fk_company_profile_file_file
            references tbl_file,
    company_id bigint not null
        constraint fk_company_profile_file_company
            references tbl_company
);