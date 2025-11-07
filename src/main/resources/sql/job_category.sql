create table tbl_job_category
(
    id               bigint generated always as identity
        primary key,
    job_name         varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);
insert into tbl_job_category (job_name) values
                                            ('SW 개발'),
                                            ('데이터/AI'),
                                            ('기획/전략'),
                                            ('디자인/UX'),
                                            ('마케팅/PR'),
                                            ('경영/운영'),
                                            ('HR/인사'),
                                            ('회계/재무'),
                                            ('법률/법무'),
                                            ('상담/영업'),
                                            ('교육/복지'),
                                            ('HW 개발'),
                                            ('연구/R&D'),
                                            ('금융/투자'),
                                            ('의료/바이오'),
                                            ('건설/부동산'),
                                            ('미디어/출판'),
                                            ('제조/생산'),
                                            ('운전/물류'),
                                            ('식품/조리'),
                                            ('숙박/레저'),
                                            ('서비스'),
                                            ('종교인'),
                                            ('미선택');


select * from tbl_job_category;
