# Triple HomeWork
### 트리플여행자 마일리지 서비스 과제

#### Stack
- Java 11
- Gradle 7.1
- MySQL 5.7.34
- docker
- kafka

#### Run Application
` $ docker-compose up --build `

#### TEST API 
##### POST /events
```json
{
"type": "REVIEW",
"action": "ADD",
"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
"content": "좋아요!",
"attachedPhotoIds": [
"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8",
"afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
"userId": "3ede0ef2-92b7-4817-a5f3-0c575361f744",
"placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"

}
```
Place는 본래 저장이 되어있어야 API 처리가 성공적으로 가능 하지만 
테스트 편의 상 저장이 되어있지 않는 Place의 경우 요청 PlaceId로 생성 처리 

##### GET /point/history/{userId} 
```json
Response:
{
    "header": {
    "code": 200,
    "message": "SUCCESS"
},
"body": {
    "data": 
        [
            {
            "point": 1,
            "pointType": "CONTENT",
            "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
            },
            {
            "point": 1,
            "pointType": "PHOTO",
            "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
            }
        ]
    }
}
```

##### GET /point/total/{userId}
```json
Response:
{
    "header": {
    "code": 200,
    "message": "SUCCESS"
},
"body": {
    "data": 
        "totalPoint": 2
    }
}
```
## ERD 
<iframe width="600" height="336" src="https://www.erdcloud.com/p/dcWnzpRMrj9edDTuz" frameborder="0" allowfullscreen></iframe>
🏝 요구사항 분석
### 1. 문제 분석
`고민을 많이 했습니다. Review Event API 를 보니 Delete Event API
와 같은 경우 이미 삭제가 된 리뷰의 대한 정보에 대해 event 가 발생 한다는게 가장 큰 고민이였습니다.`

`여기서 요구사항을 다시 되짚어 봤을 때 Review가 삭제 되는 것은 Client 에서 보여지지 않는 것 뿐이고
정보 자체는 테이블에 로그처럼 남아있는 것으로 생각 할 수 있습니다.`





### 2. API 분석 
`ADD , MOD , DEL `



