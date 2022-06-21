
🏝 요구사항 분석
### 1. 문제 분석
`고민을 많이 했습니다. Review Event API 를 보니 Delete Event API
와 같은 경우 이미 삭제가 된 리뷰의 대한 정보에 대해 event 가 발생 한다는게 가장 큰 고민이였습니다.`

`여기서 요구사항을 다시 되짚어 봤을 때 Review가 삭제 되는 것은 Client 에서 보여지지 않는 것 뿐이고
정보 자체는 테이블에 로그처럼 남아있는 것으로 생각 할 수 있습니다.`


###### 이에 따라서 ReviewTable을 정의 할 수 있습니다. 
#####  Review Table Data Scheme
<pre>
<code>
{
    "review_id": "String",
    "user_id": "String",
    "attachedPhotoIds": ["String"],
    "content": "String",
    "created_at": "String",
    "updated_at": "String",
    "is_deleted": "Boolean"
    "place_id": "String"
}
</code>
</pre>


### 2. API 분석 
`ADD , MOD , DEL `



