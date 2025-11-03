WITH RECURSIVE gen AS (
    SELECT id, parent_id, 1 AS lv1
    FROM ECOLI_DATA
    WHERE parent_id IS NULL
    
    UNION ALL
    
    SELECT e.id, e.parent_id, g.lv1+1
    FROM ECOLI_DATA e
    JOIN gen g ON e.parent_id = g.id
)

SELECT id
FROM gen
Where lv1 = 3
ORDER BY id ASC;