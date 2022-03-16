package hello.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.origin.SystemEnvironmentOrigin;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직
        callback.call();
        //비즈니스 로직 끝

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);


    }
}
