# 아래 블로그를 참고하였습니다.
https://terianp.tistory.com

WS 스터디 중입니다.




## 1. 사용기술
- Java 8
- Spring Boot MVC
- Gradle
- AJAX
- jquery
- WebSocket & SocketJS
- Stomp
- 추후 DB 기술 추가 예정

## 2. 다이어그램
![](info/Chat_diagram.png)

## 3. 공부 목표
- WebSocket 을 활용한 채팅 기능
- Stomp 를 활용한 채팅 고도화 -> pub/sub
- SocketJS 를 활용한 JS 공부
- Spring Boot 활용 및 다양한 어노테이션 사용
- AJAX , jquery, css 공부

## 4. 구현 기능
- 채팅방 생성
- 채팅방 생성 시 중복검사
- 채팅방 닉네임 선택
  => 닉네임 중복 시 임의의 숫자를 더해서 중복 안되도록
- 채팅방 입장 & 퇴장 확인
- 채팅 기능
- 채팅방 유저 리스트 & 유저 숫자 확인
- Amazon S3 기반으로 하는 채팅방 파일 업로드&다운로드
  => jquery, ajax 활용
- 채팅방 암호화 완료
- 채팅방 삭제 : 채팅방 삭제 시 해당 채팅방 안에 있는 파일들도 S3 에서 함께 삭제
- 채팅방 유저 인원 설정 -> 인원 제한 시 제한 된 인원만 채팅 참여 가능
- 소셜 로그인유저 채팅 => 네이버와 카카오 로그인 완료 단 DB 저장 X

## 5. 추후 추가 기능(목표 기능)
- 채팅방 리스트 ajax 형식으로 받기
- 채팅방명, 패스워드 변경
- 유저 클릭 시 1:1 채팅으로 전환
- 일반 로그인 유저 회원가입 및 채팅(최우선)

## 6. 구동방법
- 프로젝트를 jar 파일로 빌드
- cmd 에서 java -jar "파일명" 타이핑
- localhost:8080 으로 접속!

## 구동 화면
![](info/chatimg1.png)

