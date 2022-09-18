package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(
                        num -> System.out.println(num),
                        err -> System.out.println(err.getMessage()),
                        () -> System.out.println("Completed")
                );

        // Subscribe to a flux using an implementation of BaseSubscriber
        var subscriber = new MySubscriber<Integer>();
        ReactiveSources.intNumbersFluxWithException()
                        .subscribe(subscriber);

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnNext(T value) {
        System.out.println(value.toString() + " received");
        request(1);
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribe happened!");
        request(1);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println("Error happened in MySubscriber " + throwable.getMessage());
        System.exit(5);
    }
}