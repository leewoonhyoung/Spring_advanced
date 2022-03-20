package hello.proxy.config.v1_proxy.interface_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderControllerV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 orderControllerV1;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("OrderController.request()");

            //controller 호출
            String result = orderControllerV1.request(itemId);
            log.info("응 너불렀어");
            logTrace.end(status);
            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return orderControllerV1.noLog();
    }
}
