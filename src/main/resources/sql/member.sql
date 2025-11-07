create type provider as enum('google', 'kakao', 'naver', 'kok');

create table tbl_member
(
    user_id            bigint                           not null
        primary key
        constraint fk_member_user
            references tbl_user,
    member_provider    provider default 'kok'::provider not null,
    member_profile_url varchar(255),
    member_info        text
);
