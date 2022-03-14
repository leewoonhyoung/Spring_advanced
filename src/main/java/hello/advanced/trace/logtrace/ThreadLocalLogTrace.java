package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder  = new ThreadLocal<>();


    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        TraceId traceId = traceIdHolder.get();
        if(traceId == null){
            traceIdHolder.set(new TraceId());
        } else{
            traceIdHolder.set(traceId.createNextId());
        }
    }

    private static String addSpace(String prefix , int level){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0 ; i < level ; i++){
            stringBuilder.append((i == level-1) ? "|" + prefix : "|    ");
        }
        return stringBuilder.toString();
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);

    }
    private void complete(TraceStatus status, Exception e){
        long stopTimeMillis = System.currentTimeMillis();
        long resultTimeMs = stopTimeMillis - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),status.getMessage(), resultTimeMs);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }

        releaseTraceId();

    }

    private void releaseTraceId(){
        TraceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()){
            traceIdHolder.remove();
        }else{
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);

    }
}
