package pl.tutors.util;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

public final class FutureUtil {
    public static <T> CompletableFuture<T> convert(ListenableFuture<T> future) {
        CompletableFuture<T> completeable = new CompletableFuture<T>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean result = future.cancel(mayInterruptIfRunning);
                super.cancel(mayInterruptIfRunning);
                return result;
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }
        };

        future.addCallback(new ListenableFutureCallback<T>() {
            @Override
            public void onFailure(Throwable throwable) {
                completeable.completeExceptionally(throwable);
            }

            @Override
            public void onSuccess(T t) {
                completeable.complete(t);
            }
        });

        return completeable;
    }
}
