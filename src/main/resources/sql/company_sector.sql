create table tbl_company_sector
(
    id                  bigint generated always as identity
        primary key,
    company_sector_name varchar(255) not null,
    created_datetime    timestamp default now(),
    updated_datetime    timestamp default now()
);

insert into tbl_company_sector(company_sector_name)
values
    ('IT/웹/통신'),
    ('서비스업'),
    ('제조/화학'),
    ('의료/제약/복지'),
    ('유통/무역/운송'),
    ('교육업'),
    ('건설업'),
    ('미디어/디자인'),
    ('은행/금융업'),
    ('기관/협회');

insert into tbl_company_sector(company_sector_name)
values ('미선택');

select * from tbl_company_sector;