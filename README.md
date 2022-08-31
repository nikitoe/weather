# [개인 프로젝트] 날씨 일기장 만들기
## 프로젝트 목적
- 작성하는 일기에 대해 일과 뿐만 아니라 당일 날씨, 온도, 정보도 같이 작성하기 위해

## 프로젝트 기능

### 1.날씨 데이터 저장 API 작성
- OpenWeatherMap 에서 데이터 받아오기 ✅ 
- 받아온 데이터 (json) 사용 가능하게 파싱하게 ✅
- DB에 저장하기 ✅

### 2.날씨 일기 조회 API 작성
**GET / read / diary**
- date parameter 로 조회할 날짜를 받기. ✅
- 해당 날짜의 일기를 List 형태로 반환. ✅

**GET / read / diaries**
- startDate, ednDate parameter 로 조회할 날짜 기간의 시작일/종료일을 받기. ✅
- 해당 기간의 일기를 List 형태로 반환. ✅

### 3.날씨 일기 작성
**POST / create / diary**
- date parameter 로 받기. (date 형식 : yyyy-MM-dd) ✅
- text parameter 로 일기 글을 받기. ✅
- 외부 API 에서 받아온 날씨 데이터와 함께 DB에 저장. ✅

### 4.날씨 일기 수정하기
**PUT / update / diary**
- date parameter 로 수정할 날짜를 받기. ✅
- text parameter 로 수정할 새 일기 글을 받기. ✅
- 해당 날짜의 첫번째 일기 글을 새로 받아온 일기글로 수정. ✅

### 5.날씨 일기삭제 API 작성
**DELETE / delete / diary**
- date parameter 로 삭제할 날짜를 받기. ✅
- 해당 날짜의 모든 일기를 지우기 ✅


## 프로젝트 완성도 높이기
- DB와 관련된 함수들을 트랜잭션 처리. ✅
- 매일 새벽 1시에 날씨 데이터를 외부 API 에서 받아다 DB에 저장해두는 로직을 구현. ✅
- logback 을 이용하여 프로젝트에 로그를 남기기. 
- ExceptionHandler 을 이용한 예외처리를 하기.
- swagger 을 이용하여 API documentation 만들기.