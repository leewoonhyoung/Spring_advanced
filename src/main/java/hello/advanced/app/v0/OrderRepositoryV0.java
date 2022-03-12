package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemid){
        // 저장로직
        if (itemid.equals("ex")){
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000); // 상품을 저장하는데 1초정도 걸리는 것으로 가정하기 위해 1초의 지연 시간을 주었다.
    }

    private void sleep(int millis){

        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
