package seegene.test.flux;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.SneakyThrows;

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
    }
}
