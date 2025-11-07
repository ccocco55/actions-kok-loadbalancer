create table tbl_payment_user
(
    id                    bigint generated always as identity
        primary key,
    payment_id            bigint not null
        constraint fk_payment_user_payment
            references tbl_payment,
    user_id               bigint not null
        constraint fk_payment_user_user
            references tbl_user,
    request_experience_id bigint
        constraint fk_payment_user_request_experience
            references tbl_request_experience,
    advertisement_id      bigint
        constraint fk_payment_user_advertisement
            references tbl_advertisement
);