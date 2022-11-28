# sse-hardcoding
* study sse implement hard coding source from metacoding
* 코드 출처 : [메타 코딩 - Srpingboot WebFlux 강좌](https://www.youtube.com/watch?v=fYfNd6hqxu8&list=PL93mKxaRDidFH5gRwkDX5pQxtp0iv3guf&index=3)
  * 3강 ~ 4강까지의 코드 정리
### SSE protocal note!
* SSE : Server Send Event
  | | WebSocket | SSE |
  |:-|:---------|:----|
  |프로토콜|ws, wss|HTTP|
  |통신 방향|양방향|서버>클라이언트|
  |실시간|완전히 실시간|비교적 실시간|
  |데이터 형태|binary, UTF-8|UTF-8|
  |자동 재접속|지원 안 함|일정 시간마다 재시도<br/>(default = 3s)|
  |최대 동시 접속 수|서버 셋업에 따라 다르게 적용|브라우저당 6개<br/>HTTP2 사용 시 100개|
  |전력 소모량|크다|적다|
  |방화벽 친화적|X|O|
  |서버와 연결|지속적 handshake 실행|한 번 열고 계속 기다림|

* flux 개념 정리

  * :ocean: 연산하는데 시간이 오래걸리는 작업을 여러 클라이언트가 동시에 요청하는 경우, 서버가 처리하는 방법을 아래와 같이 구분해서 접근
    1. 동기 + 일회용 연결 : 늦게 요청한 클라이언트는 요청한 작업이 연산중인건지 연산을 대기중인건지 아무 정보도 없이 대기해야함
    2. 비동기 + 지속적 연결 : 일단 모두 응답을 하고나서 처리가 완료됐을 때마다 요청한 클라이언트에 알려 줄 수 있음

  * 2번과 같이 개발하는 경우 추가 개념
    * 서버는 일단 응답을 하고 나서도 원래 요청에 대해 처리가 끝나면 다시 응답을 해야 한다는 것을 기억하고 있어야 한다 -> Event Loop
      * Event Loop : 동시성에 대한 reactive 접근을 묘사하는 프로그래밍 모델 중 하나
    * 클라이언트에게 응답을 보내기 위해서는 한 번 연결된 요청을 유지시켜야 한다 -> stream => Flux
 
