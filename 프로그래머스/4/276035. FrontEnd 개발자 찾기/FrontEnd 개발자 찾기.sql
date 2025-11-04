/*
 조건
    1. Front End 스킬을 가진 개발자 정보 조회
    2. ID, 이메일, 이름, 성 조회
    3. ID 기준 오름차순 정렬
 풀이
    1. 개발자 테이블의 SKILL_CODE 와 스킬코드 테이블의 CODE를 &연산 
*/

SELECT DISTINCT
  ID,
  EMAIL,
  FIRST_NAME,
  LAST_NAME
FROM DEVELOPERS AS DP
JOIN SKILLCODES AS SC
  ON SC.CODE = DP.SKILL_CODE & SC.CODE
WHERE SC.CATEGORY = 'Front End'
ORDER BY ID ASC;