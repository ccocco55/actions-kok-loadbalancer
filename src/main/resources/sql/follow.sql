create table tbl_follow
(
    id            bigint generated always as identity
        primary key,
    member_id     bigint not null
        constraint fk_follow_member
            references tbl_member,
    company_id    bigint not null
        constraint fk_follow_company
            references tbl_company,
    follow_status status default 'active'::status,
    constraint uq_follow_member_company
        unique (member_id, company_id)
);