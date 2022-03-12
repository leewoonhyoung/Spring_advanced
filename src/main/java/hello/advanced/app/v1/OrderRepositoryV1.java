package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    //추가
    private final HelloTraceV1 helloTraceV1;

/**
 * version 1
    public void save(String itemId){
        // 저장로직
        if (itemId.equals("ex")){
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000); // 상품을 저장하는데 1초정도 걸리는 것으로 가정하기 위해 1초의 지연 시간을 주었다.
    }
 **/

    public void save(String itemId){

        TraceStatus status = null;
        try{
            status = helloTraceV1.begin("OrderRepository.save()");

            //저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);

            helloTraceV1.end(status);
        } catch (Exception e){
            helloTraceV1.exception(status, e);
            throw e;
        }

    }

    private void sleep(int millis){

        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
