package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;


/**
구조를 분리 즉 external 안에서 internal을 호출하느게 아니라 internalService를 따로 만들어 internalService로부터 호출 받게 설계.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;

    public void external(){
        log.info("call external");
        internalService.internal();
    }
}
