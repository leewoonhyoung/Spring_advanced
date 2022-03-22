package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    static class Target {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    @Test
    void reflection0() {
        Target target = new Target();
        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); //호출하는 메서드가 다름
        log.info("result={}", result1);
        //공통 로직1 종료
        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("result={}", result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello"); //1. class.forName을 통해 클래스 메타정보를 얻는다./

        Target target = new Target(); // callA 메서드, callB 메서드가 함유된 target 클래스(실제 인스턴스)를 얻는다.

        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); // classHello의 메타정보에서 callA를 꺼낸다.
        Object result1 = methodCallA.invoke(target); // callA를 통해 획득한 메서드 매타정보[methodCallA.invoke()]를 통해 실제 인스턴스를 호출해낸다.
        log.info("result1={}", result1);

        //callB 메서드 정보

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception{
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Target");

        Target target  = new Target(); //callA 메서드 , callB 메서드 함유

        Method methodCallA = classHello.getMethod("callA");
        // 로직
        dynamicCall(methodCallA, target);
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }


    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
}
