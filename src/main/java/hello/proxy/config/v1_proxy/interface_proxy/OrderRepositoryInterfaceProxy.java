package hello.proxy.config.v1_proxy.interface_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderRepositoryV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 orderRepositoryV1; // target
    private final LogTrace logTrace;


    @Override
    public void save(String itemId) {

        TraceStatus status = null;

        try{
            status = logTrace.begin("OrderRepositoryV1");

            orderRepositoryV1.save(itemId);
            logTrace.end(status);
        }catch (Exception e){

            logTrace.exception(status, e);
            throw e;

        }
    }
}
