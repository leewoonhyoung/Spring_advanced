package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 orderControllerV2;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy( OrderControllerV2 orderControllerV2, LogTrace logTrace) {
        super(null);
        this.orderControllerV2 = orderControllerV2;
        this.logTrace = logTrace;
    }


    @Override
    public String request(String itemId) {

        TraceStatus status = null;
        try{
            status = logTrace.begin("OrderController.request()");

            //target (OrderControolerV2 실행)
            String result = orderControllerV2.request(itemId);
            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
