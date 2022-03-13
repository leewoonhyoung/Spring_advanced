package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    //추가
    private final HelloTraceV2 helloTraceV2;

/**
 * version 1
    public void orderItem(String itemId){
        orderRepository.save(itemId);
        }

 */
/**
 * version 2

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = helloTraceV1.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            helloTraceV1.end(status);
        } catch(Exception e){
            helloTraceV1.exception(status, e);
            throw e;
        }
    }
**/

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            helloTraceV2.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            helloTraceV2.end(status);
        } catch(Exception e){
            helloTraceV2.exception(status, e);
            throw e;
        }
    }
 }
