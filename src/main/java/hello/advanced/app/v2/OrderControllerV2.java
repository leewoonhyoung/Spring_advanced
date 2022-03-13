package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 helloTraceV2;

/**
 * version 1

    @GetMapping("/v0/request")
    public String request(String itemId){
        orderService.orderItem(itemId);
        return "hello";
    }
 */

/**
 * version 2
    @GetMapping("v1/request")
    public String request(String itemId){

        TraceStatus status = null;

        try{
            status = helloTraceV1.begin("OrderController.request()");
            orderService.orderItem(itemId);
            helloTraceV1.end(status);
            return "ok";
        } catch (Exception e){
            helloTraceV1.exception(status, e);
            throw e; // 예외를 꼭 던져주어야 한다.
        }
    }
**/

    @GetMapping("v2/request")
    public String request(String itemId){

        TraceStatus status = null;

        try{
            helloTraceV2.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            helloTraceV2.end(status);
            return "ok";
        }catch(Exception e){
            helloTraceV2.exception(status, e);
            throw e;
        }
    }

}
