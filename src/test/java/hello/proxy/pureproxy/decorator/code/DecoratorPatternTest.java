package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Time;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();

    }

    @Test
    void decorator2(){


        //의존관계 변수 설정 주입일뿐!! 이 순서대로 로직이 흘러가는것은 아니다!!!!!
        //착각 금지!
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);


        //logic 실행.
        client.execute();
    }

}
