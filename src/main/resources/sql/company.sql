create table tbl_company
(
    user_id      bigint       not null
        primary key
        constraint fk_company_user
            references tbl_user,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url  varchar(255)
);