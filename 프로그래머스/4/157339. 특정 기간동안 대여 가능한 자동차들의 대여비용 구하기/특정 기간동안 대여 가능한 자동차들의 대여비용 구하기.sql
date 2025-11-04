/*
 <문제 분석>
 [조건]
    1. 자동차 종류 '세단' 또는 'SUV'
    2. 대여 가능일 2022-11-01 ~ 2022-11-30 
    3. 30일간의 대여 금액 50만원 이상, 200만원 미만 
 [출력]
    자동차 ID, 자동차 종류, 대여 금액(FEE)
 [정렬]
    대여 금액 DESC, 종류 ASC, ID DESC
 
 [풀이 접근]
    1. 대여 가능 차종 먼저 분류
        대여 불가 :  대여 시작일 <= 2022-12-01 AND 대여 종료일 >= 2022-11-01 
    2. 자동차 종류 '세단' 또는 'SUV' 
    3. 해당하는 차량 중 30일간의 대여 금액 계산
        - 30일 이상의 DURATION_TYPE만 추출
*/

SELECT
  CC.CAR_ID,
  CC.CAR_TYPE,
  ROUND(CC.DAILY_FEE * 30 * (100 - DP.DISCOUNT_RATE) / 100) AS FEE
FROM CAR_RENTAL_COMPANY_CAR AS CC
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS RH
  ON CC.CAR_ID = RH.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS DP
  ON CC.CAR_TYPE = DP.CAR_TYPE
WHERE CC.CAR_TYPE IN ('세단', 'SUV')
  AND CC.CAR_ID NOT IN (
      SELECT CAR_ID
      FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
      WHERE START_DATE <= '2022-11-30' AND END_DATE >= '2022-11-01'
  )
  AND DP.DURATION_TYPE = '30일 이상'
GROUP BY CC.CAR_ID
HAVING FEE >= 500000 AND FEE < 2000000
ORDER BY FEE DESC, CC.CAR_TYPE ASC, CC.CAR_ID DESC;
