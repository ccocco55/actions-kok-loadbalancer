create table tbl_company_scale_category
(
    id                 bigint generated always as identity
        primary key,
    company_scale_name varchar(255) not null,
    created_datetime   timestamp default now(),
    updated_datetime   timestamp default now()
);

insert into tbl_company_scale_category(company_scale_name)
values
    ('매우 작은 규모'),
    ('작은 규모'),
    ('중간 규모'),
    ('큰 규모'),
    ('매우 큰 규모');

insert into tbl_company_scale_category(company_scale_name)
values('미선택');

select * from tbl_company_scale_category;