package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternClient {

    private Component component; // 주입된 component  TimeDecordator

    public DecoratorPatternClient(Component component) {
        this.component = component;
    }

    public void execute(){
        String result = component.operation(); // 여기서의 component는 TimeDecorator의 operation을 실행해.
        log.info("result={}", result);
    }
}
