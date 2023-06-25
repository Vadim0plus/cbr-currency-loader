package ru.vtukhvatullin.cbrcurrloader;

import ru.vtukhvatullin.cbrcurrloader.service.CbrCurrencyLoaderScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        CbrCurrencyLoaderScheduledExecutorService.start();
    }
}