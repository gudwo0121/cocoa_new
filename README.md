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

