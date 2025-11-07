create table tbl_evaluation
(
    id                    bigint generated always as identity
        primary key,
    evaluation_content    varchar(255)     not null,
    evaluation_avg_score  double precision not null,
    request_experience_id bigint           not null
        constraint fk_evaluation_request_experience
            references tbl_request_experience,
    member_id             bigint           not null
        constraint fk_evaluation_member
            references tbl_member,
    company_id            bigint           not null
        constraint fk_evaluation_company
            references tbl_company,
    created_datetime      timestamp default now(),
    updated_datetime      timestamp default now()
);