package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace_test implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> threadLocal = new ThreadLocal<>();



    @Override
    public TraceStatus begin(String message) {
        syncTraceId(); // traceholder에서 가져오고없으면 다음 아이디 만드는 로직
        TraceId traceId = threadLocal.get();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);

    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);

    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void syncTraceId(){
        TraceId traceId = new TraceId();

        if(traceId == null){
            threadLocal.set(new TraceId());
        } else{
            threadLocal.set(traceId.createNextId()); //로컬에 id 있으면 새로운 id에 client 할당.
        }
    }

    private static String addSpace(String prefix, int level){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < level; i++){
            stringBuilder.append((i ==level -1) ? "|" + prefix : "|  ");
        }
        return stringBuilder.toString();
    }


    private void complete(TraceStatus status, Exception e){
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null){
            log.info("[{}]{}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),status.getMessage(), resultTimeMs, e.toString());
        }

        releaseTraceId(); // stack처럼 과거로 역행해서 실행해. 끝난것은 tomcat에서 지워주세요.
    }

    private void  releaseTraceId(){
        TraceId traceId = threadLocal.get();
        if(traceId.isFirstLevel()){
            threadLocal.remove();
        } else{
            threadLocal.set(traceId.createPreviousId());
        }
    }

}
