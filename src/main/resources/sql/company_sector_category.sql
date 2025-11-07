create table tbl_company_sector_category
(
    company_id        bigint not null
        primary key
        constraint fk_company_sector_category_company
            references tbl_company,
    company_sector_id bigint not null
        constraint fk_company_sector_category_company_sector
            references tbl_company_sector
);