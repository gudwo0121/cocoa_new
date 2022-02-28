# CoCoa Renewal

****

22.01.06 추가

* 홈화면 추가

***

22.01.07 추가

* 부트스트랩 홈화면 적용

***

22.01.08 추가

* 부트스트랩 최적화

***

22.01.09 추가

* 부트스트랩 최적화

***

22.01.10 추가

* 부트스트랩 최적화

***

22.01.12 추가

* 코칭, 프로젝트 리스트 화면 이동 구현 (비동기)

***

22.01.13 추가

* 리스트 분야 3개 추가

***

22.01.14 추가

* 코칭, 프로젝트 리스트 화면 내용 수정

***

22.01.15 추가

* 코칭 등록 화면 이동 구현

***

22.01.16 추가

*  프로젝트 모집 화면 이동 구현

***

22.01.17 추가

*  MyBatis 환경설정 및 DB 연동 - Local 3306
*  코칭 리스트 전체 조회 구현

***

22.01.18 추가

*  프로젝트 리스트 전체 조회 구현

***

22.01.19 추가

*  로그인, 회원가입 화면 이동 구현 = 홈 화면 shortcut 필요

***

22.01.20 추가

*  로그인, 회원가입 시 홈 화면 shortcut 구현
*  로그인, 회원가입 입력 내용 수정
*  회원가입 비즈니스 로직은 완성되었지만 비동기로 구현은 아직 = signUp.js 아직

***

22.01.21 추가

* 회원가입 비동기 구현 = 비밀번호 일치 확인 구현 = ID 중복확인 아직

***

22.01.22 추가

* 회원가입 ID 중복확인 구현 = java.lang.Integer 에러는 @ResponseBody를 안써줬기 때문

***

22.01.23 추가

* 메소드명 및 RequestMapping value 통일시키기 - goLogin, goSignUp 수정

***

22.01.25 추가

* 메소드명 및 RequestMapping value 통일

  = goCoachingList, goProjectList, goCoachingWrite, goProjectWrite 수정

***

22.01.26 추가

* 홈 화면 contents 파트 이동 수정 필요 = 비동기라서 뒤로가기 및 새로고침이 불편

  = Ajax + GET 방식을 혼합하여 구현 예정

***

22.01.27 추가

* 홈 화면 = sideBar + header + contents + footer

  = 총 4파트로 jsp:include

***

22.01.28 추가

* GET 방식으로 쿼리스트링 날려서 contents 파트 구분하여 이동

  = Ajax + GET 방식은 아직 구현 x

***

22.01.29 추가

* 해결방법 후보 : pushState + popState

* 문제점 : URL은 바뀌는데 반영이 안됌 + 쿼리스트링을 써야함(?) + GET은 보안 취약 

  = movePage.js에서 좀 더 디버깅해야함

***

22.01.30 ~ 22.02.02 - 추가 x = 설날

***

22.02.03 추가

* contents 파트 location.href 방식으로 페이지 이동 모두 구현

  = 새로고침 / 뒤로가기 문제 해결

  = 차후에 쿼리스트링 사용 가능성 고려

  = movePage.js는 보류

  -> 페이지이동에 무조건 비동기를 쓰는건 옳지 않다
  
  -> pushState, popState도 있지만 결국 GET으로 가기때문에 차이가 없다.

***

22.02.04 추가

* 로그인, 로그아웃 구현 
* 로그인, 비로그인에 따른 header 변경 구현 = 틀만 잡아놨음
* 로그인은 비동기로 구현하되 나중에 사용가능성을 고려하여 MemberVO로 받아와서 세션에 저장

***

22.02.05 추가

* 마이프로필 구성 재설계 = sideBar 색상 다르게 / 프로필과 회원정보 수정을 통합하기

  = 구현 예정

***

22.02.06 추가

* sideBar 색상 다르게 myProfile.jsp 생성 = 내정보 클릭시 이동 체킹해봐야함

  = sideBar -> profileSideBar로 새로 만듬


***

22.02.07 추가

* 코칭 등록 구현 = multipartHttpRequest

  = 이미지 업로드 고려하여 멀티파트로 구현

  = 이미지 업로드는 아직 구현 안되서 'test'로 하드코딩하여 진행하였음 = 구현 완료

  = SSL 해제 / multipartResolver 추가 / cImg 누락으로 인한 발생 에러 모두 해결


***

22.02.08 추가

* 이미지 업로드 및 로컬 다운로드 구현 = 썸네일은 코칭 리스트 조회 기능과 함께 구현 예정

***

22.02.10 추가

* 코칭 썸네일 조회 구현

***

22.02.11 추가

* 프로젝트  등록 구현 = 지도는 "default"로 DB에 insert

***

22.02.13 추가

* 프로젝트  썸네일 조회 구현 = "오타를 조심하자"

***

22.02.14 추가

* 비로그인이면 코칭 등록 / 프로젝트 모집 클릭시 로그인창으로 이동 구현

***

22.02.15 추가

* 프로젝트 리더 표시 보완 = 오타
* 메인 sideBar 하단 파트 주석처리

***

22.02.16 추가

* REST 공부

  1. /cocoa/coaching/{field} << 설계 시 적절한 URI 가이드라인이 필요

  2. @PathVariable(value = "field") String field <<< 이런식으로 @RequestParam이랑 비슷한 느낌

  3. 페이지 이동은 ModelAndView 사용하는 편이 용이

  4. @ResponseBody = POST 방식 + JSON 형태로 입력 받는 거

  5. @RequestParam = GET 방식 + 쿼리스트링으로 입력 받는 거

  6. ResponseEntity = 상태코드를 담는 클래스

  7. JSON으로 값을 받아 VO의 toString()을 거쳐 문자열로 사용하는 방식

  8. @RestController = @ResponseBody + @Controller

  9. @RequestMapping(value = "/", method = RequestMethod.GET)

     => @GetMapping("/") <<< REST
  
* 일단은 URL 주소를 구분해서 goCoachingList -> coaching/{field} 변경

  = 다른 URI 마다 3가지 field 조건 조회 + tool 표시 다르게 구현 해야함

***

22.02.17 추가

* 코칭 글 분야별 조건 조회 REST 구현

***

22.02.18 추가

* 코칭 분야별 url에 따라 개발툴 다르게 표시 구현 = location.attr('href')

***

22.02.19 추가

* 프로젝트 분야별 글 조건 조회 REST 구현

***

22.02.20 추가

* 코칭 글 작성 후 이동 경로 = 자신이 작성한 분야 조건 조회 페이지 = 즉 뒤로가기 구현

***

22.02.21 추가

* 프로젝트 글 작성 후 이동 경로 = 자신이 작성한 분야 조건 조회 페이지 = 즉 뒤로가기 구현

***

22.02.22 추가

* 코칭 글 개발툴별 글 조건 조회 REST 구현 = showTools은 url의 3번째 split 값에 따라 동적으로 구현
* 수정된 css / js 반영 안될 때는 "ctrl + F5"를 눌러 캐시와 메모리까지 새로고침

***

22.02.23 추가

* 프로젝트 글 분야+레벨별 글 조건 조회 REST 구현 = 레벨이 모두 동일하게 3단계라서 조건 2개 걸어야함

  = 나중에 동적 쿼리문으로 중복제거 해야할듯 하다

  = 이동은 url path split으로 구분
  
* conditionRead 코칭 / 프로젝트 공동 참조라 동적으로 구현

***

22.02.24 추가

* Capitalized cField + pField 경로 표시 추가

  = conditionRead.js 에서 소문자를 쓰기 때문에 ${showField}로 전송

***

22.02.25 추가

* tool / level 클릭에 따라 배경색상 변경으로 현재 위치 표시

  = 예전엔 onclick 이벤트가 복잡했는데 REST로 구현하니 URL 정보 받아와서 JavaScript에서 활용 가능하다
  
  = ~~매우 신기하고 편리하다... 왜 몰랐지~~

***

22.02.26 추가

* UI 개선 = 코칭 / 프로젝트 글 색상 변경

***

22.02.27 추가

* UI 개선 = 코칭 / 프로젝트 글 색상 변경

  = 3계층 카테고리랑 색상 맞추는게 더 나아보임

***

22.02.28 추가

* 마이프로필 UI 개선 = 홈 화면의 대시보드 테마로 변경중 + JSP 코드 정리

***

22.03.01 추가

* 마이프로필 UI 개선 = 홈 화면의 대시보드 테마로 변경중 + JSP 코드 정리

***
