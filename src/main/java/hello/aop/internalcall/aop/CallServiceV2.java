package hello.aop.internalcall.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {

    //ObjectProvider는 ApplicationContext기능의 일부이며
    // 스프링 빈 생성 시점이 아니라 실제 객체를 사용하는 시점으로 지연가능하게 한다.
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal();
    }

    public void internal(){
        log.info("call internal");
    }
}
