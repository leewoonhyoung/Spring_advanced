package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV1 {


    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();

    //코드를 보면 request() , noLog() 두 가지 메서드가 있다. request() 는 LogTrace 를 적용할
    //대상이고, noLog() 는 단순히 LogTrace 를 적용하지 않을 대상이다.
}
