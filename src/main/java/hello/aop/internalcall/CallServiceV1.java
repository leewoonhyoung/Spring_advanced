package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        this.callServiceV1 = callServiceV1;
    }


    //callServiceV1을 수정자를 통해서 주입 한다.
    //aop가 적용된 대상을 의존관계 주입을 받으면 주입 받은 대상은 자신이 아니라 프록시 객체이다.
    /**
     * 참고로 이 경우 생성자 주입시 오류가 발생한다. 본인을 생성하면서 주입해야 하기 때문에 순환 사이클이
     * 만들어진다. 반면에 수정자 주입은 스프링이 생성된 이후에 주입할 수 있기 때문에 오류가 발생하지 않는다.
     */

    public void external(){
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal(){
        log.info("call internal");
    }
}
