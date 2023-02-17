package seegene.test.flux;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class EventNotify {

    private List<String> events = new ArrayList<>();
    private boolean change = false;

    public void add(String data){
        events.add(data);
        change = true;
    }

    public String pop(){
        if(this.events.size()>0){
            this.change = false;
            return this.events.get(this.events.size()-1);
        }else{
            return null;
        }
    }
}
