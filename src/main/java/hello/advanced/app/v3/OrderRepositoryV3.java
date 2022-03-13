package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {


    //private final HelloTraceV2 helloTraceV2;
    private final LogTrace logTrace;
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

/** version 2

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
 **/

/**
 * version2
    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = helloTraceV2.beginSync(traceId, "OrderRepository.save()");

            //저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            helloTraceV2.end(status);
        } catch (Exception e){
            helloTraceV2.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

 **/
 public void save(String itemId){
     TraceStatus status = null;
     try{

         status = logTrace.begin("OrderRepository.save()");

         //저장 로직
         if (itemId.equals("ex")){
             throw new IllegalStateException("예외 발생");
         }
         sleep(1000);

         logTrace.end(status);

        } catch (Exception e){
         logTrace.exception(status, e);
         throw e;
     }
 }

     private void sleep(int millis){
         try{
             Thread.sleep(millis);
         } catch(InterruptedException e){
             e.printStackTrace();
         }
     }
 }

