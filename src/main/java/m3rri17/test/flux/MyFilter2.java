package m3rri17.test.flux;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.SneakyThrows;

//이 클래스의 목적 : 이미 응답 받은 client에 추가적인 data를 계속 보내주는 역할
public class MyFilter2 implements Filter {
    private EventNotify eventNotify;

    public MyFilter2 (EventNotify eventNotify){
        this.eventNotify = eventNotify;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        System.out.println("do filter 2");//Thread.currentThread().getId()+

        eventNotify.add("new data~!");
        //client는 구독하기 시작하는 순간부터만 볼 수 있다. (hot)
    }
}
