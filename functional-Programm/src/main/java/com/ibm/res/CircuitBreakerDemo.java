package com.ibm.res;


import com.ibm.func.vavr.BusinessException;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;

import java.time.Duration;

class BusineesException extends RuntimeException {
    public BusineesException(String message) {
        super();
    }
}

class MessageService {
    private static final int WAIT_TIME_MS = 1000;

    public String okay() {
        return "I'm okay.";
    }

    public String slow() throws InterruptedException {
        Thread.sleep(WAIT_TIME_MS);

        return "I'm okay, just slow";
    }

    public String error() {
        throw new BusineesException("I'm definitely not okay!");
    }

}

class Consumer {
    private final Bulkhead bulkhead;
    private MessageService messageService;

    public Consumer() {
        messageService = new MessageService();
        bulkhead = createBulkHead(100);
    }

    private Bulkhead createBulkHead(int maxConncurrent) {
        BulkheadConfig bulkheadConfig = BulkheadConfig.custom()
                .maxConcurrentCalls(maxConncurrent)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();
        Bulkhead bulkhead = Bulkhead.of("resilience-provider", bulkheadConfig);
        bulkhead.getEventPublisher()
                .onCallFinished(f -> System.out.println("Call finished"))
                .onCallPermitted(p -> System.out.println("CallPermitted"))
                .onCallRejected(r -> System.out.println("Call Rejected"));
        return bulkhead;
    }

    public String bulkHeadTest()  {

        Bulkhead.decorateSupplier(bulkhead, () -> "The message was"+ messageService.slow(),  String.class);
        return "";
    }


}