create view view_user_with_evaluation_and_job as
select
    u.*,
    v.avg_score,
    v.member_profile_url,
    j.job_name
from tbl_user u
-- 평균 점수 조인
         left join (
    select
        m.user_id, m.member_profile_url,
        ROUND(avg(e.evaluation_avg_score)::numeric, 1) as avg_score
    from tbl_member m
             join tbl_evaluation e
                  on m.user_id = e.member_id
    group by m.user_id
) v
                   on u.id = v.user_id
-- 유저-직무 매핑 조인
         left join tbl_user_job_category ujc
                   on u.id = ujc.user_id
-- 직무 카테고리 이름 조인
         left join tbl_job_category j
                   on ujc.job_category = j.id;


create view view_member_with_evaluation(user_id, member_provider, member_profile_url, member_info, avg) as
SELECT m1.user_id,
       m1.member_provider,
       m1.member_profile_url,
       m1.member_info,
       m2.avg
FROM tbl_member m1
         JOIN (SELECT m.user_id,
                      avg(e.evaluation_avg_score) AS avg
               FROM tbl_member m
                        JOIN tbl_evaluation e ON m.user_id = e.member_id
               GROUP BY m.user_id) m2 ON m1.user_id = m2.user_id;