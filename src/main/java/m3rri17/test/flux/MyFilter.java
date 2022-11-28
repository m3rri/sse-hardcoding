package seegene.test.flux;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.PrintWriter;

public class MyFilter implements Filter {
    private EventNotify eventNotify;

    public MyFilter (EventNotify eventNotify){
        this.eventNotify = eventNotify;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        System.out.println("do filter 1");//Thread.currentThread().getId()+

        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setContentType("text/event-stream:charset=utf-8");
        //SSE 프로토콜의 header 중 Content-Type은 text/event-stream이어야 함
        //Cache-Control : no-cache
        //Connection : keep-alive (handshake를 생략하고 이미 연결된 tcp를 재사용한다.)

        //web flux 로직 start
        PrintWriter writer = response1.getWriter();

        // note1 : reactive streams library 사용 시 표준을 지켜서 응답하게 됨
        for(int i=0; i<5; i++){
            writer.println("response : "+i);
            writer.flush();
            Thread.sleep(1000);
        }
        //web flux 로직 end

        // note2 : SSE emitter library를 사용하면 편하게 구현할 수 있음
        while(true){
            if(eventNotify.isChange()){
                writer.println("response : "+eventNotify.pop()+"\n");
                writer.flush();
            }
            Thread.sleep(1);
        }

        // note3 : web flux + reactive streams 가 적용된 stream을 배우게 됨 (비동기 단일 스레드 동작)
        // note4 : servlet mvc + reactive streams 가 적용된 stream을 배우게 됨 (멀티 스레드 방식)
    }
}
