package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;

    // verison2 까지만 사용  private final HelloTraceV2 helloTraceV2;
    private final LogTrace logTrace;
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
/**
    version 2
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

 **/


    /** version 4
     *
     * @param itemId

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = logTrace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            logTrace.end(status);
        } catch(Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    **/

    public void orderItem(String itemId){
        AbstractTemplate<Object> template = new AbstractTemplate<>(logTrace){

            @Override
            protected String call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
