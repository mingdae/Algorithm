-- 세대별 그룹
-- 1)'세대' 구하는 로직 필요
-- 2) 자식 유무 판단 로직 필요

-- 1) 세대 구하기
WITH RECURSIVE gen AS(
    -- 1-1) 루트 값(1세대)
    SELECT id, parent_id, 1 AS lv
    FROM ECOLI_DATA
    WHERE parent_id IS NULL
    
    UNION ALL
    -- 1-2) 재귀
    SELECT e.id, e.parent_id, gen.lv+1
    FROM ECOLI_DATA AS e
    JOIN gen ON e.parent_id = gen.id
)

-- 2) 자식 유무 판단
SELECT
  COUNT(*) AS COUNT,
  g1.lv AS GENERATION
FROM gen g1
LEFT JOIN gen g2
  ON g2.parent_id = g1.id
WHERE g2.id IS NULL
GROUP BY g1.lv
ORDER BY g1.lv ASC;