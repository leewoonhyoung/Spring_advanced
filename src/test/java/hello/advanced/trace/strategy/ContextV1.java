package hello.advanced.trace.strategy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;


//필드에 전략을 보관하는 방식
@Slf4j
public class ContextV1 {


    private Strategy strategy;

    public ContextV1(Strategy strategy){
        this.strategy = strategy;
    }


    public void execute(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * templete method pattern
    public abstract class AbstractTemplate<T> {

        private final LogTrace logTrace;

        public AbstractTemplate(LogTrace logTrace) {
            this.logTrace = logTrace;
        }


        public T execute(String message){
            TraceStatus status = null;
            try{
                status = logTrace.begin(message);

                //로직 호출
                T result = call();
                // 호출 종료


                logTrace.end(status);
                return result;

            }catch (Exception e){
                logTrace.exception(status, e);
                throw e;

            }
        }

        protected abstract  T call();
    }
**/

}
