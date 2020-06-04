package com.ibm.res;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.reactivex.Observable;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;


import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

//Exception class
class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}

//Datasource

interface Connector {
    String failure();

    String success();

    String ignoreException();

    Observable<String> methodWhichReturnsAStream();
}

@Component(value = "backendBConnector")
class BackendBConnector implements Connector {

    @Override
    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @Override
    public String success() {
        return "Hello World from backend B";
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend B");
    }

    @Override
    public Observable<String> methodWhichReturnsAStream() {
        return Observable.never();
    }
}

//Service interface
interface BusinessService {
    String failure();

    String success();

    String ignore();

    Try<String> methodWithRecovery();
}
//Service implementation
@Service(value = "businessBService")
class BusinessServiceB implements BusinessService {

    private final Connector backendBConnector;
    private  CircuitBreakerRegistry circuitBreakerRegistry= CircuitBreakerRegistry.ofDefaults();


    public BusinessServiceB(@Qualifier("backendBConnector") Connector backendBConnector,
                            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.backendBConnector = backendBConnector;
    }

    @Override
    public String failure() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::failure).get();
    }

    @Override
    public String success() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::success).get();
    }

    @Override
    public String ignore() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::ignoreException).get();
    }

    @Override
    public Try<String> methodWithRecovery() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
        Supplier<String> backendFunction = CircuitBreaker.decorateSupplier(circuitBreaker, () -> backendBConnector.failure());
        return Try.ofSupplier(backendFunction)
                .recover((throwable) -> recovery(throwable));
    }
    private String recovery(Throwable throwable) {
        // Handle exception and invoke fallback
        return "Hello world from recovery";
    }

}
@RestController
@RequestMapping(value = "/backendB")
class BackendBController {

    @Autowired
    private BusinessService businessBService;
    public BackendBController(){
        this.businessBService = businessBService;

    }

    public BackendBController(@Qualifier("businessBService")BusinessService businessBService){
        this.businessBService = businessBService;
    }

    @GetMapping("failure")
    public String backendBFailure(){
        return businessBService.failure();
    }

    @GetMapping("success")
    public String backendBSuccess(){
        return businessBService.success();
    }

    @GetMapping("ignore")
    public String ignore(){
        return businessBService.ignore();
    }
}

@SpringBootApplication
class CircuitBreakerDemo {
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerDemo.class, args);

    }
}