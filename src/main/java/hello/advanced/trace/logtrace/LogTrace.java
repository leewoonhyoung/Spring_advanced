package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.springframework.stereotype.Component;


public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
