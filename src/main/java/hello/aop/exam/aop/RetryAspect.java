package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {


    // @annotation(retry) , Retry retry 를 사용해서 어드바이스에 애노테이션을 파라미터로 전달한다.
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value(); // 애노테이션에 지정된 값을 가져오기.

        Exception exceptionHolder = null;

        for(int retryCount =1; retryCount <= maxRetry; retryCount++){
            try{
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e){
                exceptionHolder = e;

            }
        }
        throw exceptionHolder;
    }
}
