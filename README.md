<h1>프로젝트 개요</h1>
<p>Spring Boot 기반의 주문관리 시스템입니다.</p><br>

<h3>[Back-End]</h3>
<p>1. Spring Boot를 이용하여 음식점의 주문 서버 시스템을 Restful Api 서비스로 제공한다.</p>
<p>2. 클라이언트로부터 오는 요청에 대한 인증 시스템을 제공한다.</p>
<p>3. 가게의 정보, 음식의 정보, 토핑의 정보, 주문 정보를 CRUD 할 수 있는 관리자 페이지를 제공한다.</p><br>

<h3>[Front-End]</h3>
<p>1. 모바일 어플리케이션, 웹 어플리케이션, 윈도우 응용 어플리케이션 등을 클라이언트로 가정한다.</p>
<p>2. 리소스와 가게, 메뉴, 토핑 등에 대한 데이터는 서버에서 동적으로 가져오도록 한다.</p>
<p>3. 서버에 인증절차를 거친 클라이언트에게 주문에 대한 CRUD를 제공한다.</p><br>

<h1>프로젝트 기본 설정</h1>

<h3>리소스 외부경로 설정</h3>
<p>1. 'Github.zip' 파일의 압축을 푼다.</p>
<p>2. 압축을 풀어 나온 폴더를 적당한 위치에 옮긴다.</p>
<p>3. 'application.priperties' 파일의 변수 'custom.path.upload-images'에 리소스 폴더의 경로를 입력한다.</p><br>

<h3>DataBase 설정</h3>
<p>1. 데이터베이스의 경로를 'application.priperties' 파일의 변수 'spring.datasource.url'에 입력한다.</p>
<p>2. 'application.priperties' 파일의 변수 'spring.datasource.username', 'spring.datasource.password'에 DB의 권한정보를 입력한다.</p><br>

<h1>관리자 페이지 URL(GET)</h1>
<p>/admin/order : 주문목록 페이지</p>
<p>/admin/product : 상품목록 페이지</p>
<p>/admin/statistic : 통계 페이지</p>
<p>/admin/store : 가게정보 페이지</p>

<h1>API URL</h1>
<p>(GET) /api/order/{id} : 주문조회</p>
<p>(POST) /api/order : 주문하기</p>
<p>-> 매개변수 추가 필요</p>
<p>(PUT) /api/order : 주문수정</p>
<p>-> 매개변수 추가 필요</p>
<p>(DELETE) /api/order/{id} : 주문취소</p>
