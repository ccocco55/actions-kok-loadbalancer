create table tbl_company_scale_category_bridge
(
    company_id                bigint not null
        constraint fk_company_scale_category_bridge_company
            references tbl_company,
    company_scale_category_id bigint not null
        constraint fk_company_scale_category_bridge_company_scale_category
            references tbl_company_scale_category
);