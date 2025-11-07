create table tbl_company_license_file
(
    file_id    bigint not null
        constraint fk_company_license_file_file
            references tbl_file,
    company_id bigint not null
        constraint fk_company_license_file_company
            references tbl_company
);