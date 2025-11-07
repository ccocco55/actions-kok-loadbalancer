CREATE OR REPLACE VIEW vw_user_company_job_follow_scale AS
SELECT
    u.id AS user_id,
    u.user_name,
    c.company_name,
    u.user_email,
    u.user_phone,
    c.company_url,
    j.job_name,
    u.user_status,
    COALESCE(follow_count, 0) AS follow_count,
    cs.company_scale_name
FROM tbl_user u
-- 회사 정보
         JOIN tbl_company c
                   ON u.id = c.user_id
-- 직무 정보
         LEFT JOIN tbl_user_job_category ujc
                   ON u.id = ujc.user_id
         LEFT JOIN tbl_job_category j
                   ON ujc.job_category = j.id
-- 팔로우 수
         LEFT JOIN (
    SELECT member_id, COUNT(company_id) AS follow_count
    FROM tbl_follow
    WHERE follow_status = 'active'
    GROUP BY member_id
) f
                   ON u.id = f.member_id
-- 회사 규모
         LEFT JOIN tbl_company_scale_category_bridge csb
                   ON c.user_id = csb.company_id
         LEFT JOIN tbl_company_scale_category cs
                   ON csb.company_scale_category_id = cs.id;

