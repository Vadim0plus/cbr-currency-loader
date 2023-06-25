package ru.vtukhvatullin.cbrcurrloader.service;

import ru.vtukhvatullin.cbrcurrloader.config.AppConfig;

import java.util.concurrent.*;

public class CbrCurrencyLoaderScheduledExecutorService {
    public static void start() {
        try {
            CbrCurrencyLoaderService cbrCurrencyLoaderService = new CbrCurrencyLoaderService();
            Runnable runnable = cbrCurrencyLoaderService::load;
            ScheduledExecutorService executorService = Executors
                    .newSingleThreadScheduledExecutor();
            ScheduledFuture<?> scheduled = executorService.scheduleWithFixedDelay(runnable, 0, AppConfig.jobInterval, TimeUnit.SECONDS);
            scheduled.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
