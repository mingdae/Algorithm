/*

 조건
    1. 보호소에 들어올 때는 중성화 X
    2. 보호소 나갈 때는 중성화 O
    3. 생물 종, 이름 조회 => 아이디 순서로 정렬
*/

SELECT
  INS.ANIMAL_ID AS ANIMAL_ID,
  INS.ANIMAL_TYPE AS ANIMAL_TYPE,
  INS.NAME AS NAME
FROM ANIMAL_INS AS INS
JOIN ANIMAL_OUTS AS OUTS
  ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE INSTR(INS.SEX_UPON_INTAKE, 'Intact') > 0 # 보호소에 들어올 때는 중성화 X
  AND INSTR(OUTS.SEX_UPON_OUTCOME, 'Intact') = 0 # 보호소에서 나갈 때는 중성화 O 
ORDER BY INS.ANIMAL_ID