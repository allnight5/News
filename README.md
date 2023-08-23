# News
Swagger 사용한 API 명세 Application run 후 아래 링크
http://localhost:8080/swagger-ui/index.html

#ERD
---
![news (2)](https://github.com/allnight5/News/assets/45612782/98be1462-77a9-489f-810c-0a6640988874)



---
Get 
GET /api/news

 
GET /api/news/{news_id}


뉴스 작성
POST /api/news

Put
뉴스 수정
PUT /api/news/{news_id}

PATCH /api/news/{news_id}/lock

PATCH /api/news/{news_id}/unlock


Delete
DELETE /api/news/{news_id}

Error처리
없는 Url로 요청이 들어왔을때 Aop를 이용하여 오류 발생시 핸들러를 이용하여 별도 처리가 가능합니다.
