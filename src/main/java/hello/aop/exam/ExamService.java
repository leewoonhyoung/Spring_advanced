package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace //@Trace를 사용하면 메서드 호출 정보를 AOP를 통해 로그로 남길수 있다.
    public void request(String itemId){
        examRepository.save(itemId);
    }
}
