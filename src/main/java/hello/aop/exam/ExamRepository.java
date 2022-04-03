package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;
    /**
     * 5번에 1번 실패하는 요청
     */

    @Trace
    @Retry(value =4) // aop.retryAspect의 doRetry 메소드를 통해 메서드에서 문제가 발생하면 4번 재시도 한다.
    public String save(String itemId){
        seq++;
        if(seq % 5 == 0){
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
