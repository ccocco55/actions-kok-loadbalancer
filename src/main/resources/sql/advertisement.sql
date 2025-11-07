create table tbl_advertisement
(
    id                           bigint generated always as identity
        primary key,
    advertisement_main_text      varchar(255)                                   not null,
    advertisement_sub_text       varchar(255)                                   not null,
    advertisement_status         status         default 'active'::status        not null,
    advertise_start_datetime     date                                           not null,
    advertise_end_datetime       date                                           not null,
    company_id                   bigint                                         not null
        constraint fk_advertisement_company
            references tbl_company,
    created_datetime             timestamp      default now(),
    updated_datetime             timestamp      default now(),
    advertisement_request_status request_status default 'await'::request_status not null
);
select * from tbl_advertisement;
insert into tbl_advertisement (advertisement_main_text, advertisement_sub_text, advertise_start_datetime, advertise_end_datetime, company_id)
values ('광고 문의','광고 문의는 기업 콘솔창에서 확인하실 수 있습니다.','2025-10-27','2026-11-25',3);


ALTER TABLE tbl_advertisement
    ADD COLUMN advertisement_request_status request_status DEFAULT 'await'::request_status NOT NULL;

