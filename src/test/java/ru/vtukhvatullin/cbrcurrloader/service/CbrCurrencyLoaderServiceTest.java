package ru.vtukhvatullin.cbrcurrloader.service;

import org.junit.jupiter.api.Test;

class CbrCurrencyLoaderServiceTest {

    @Test
    void load() {
        CbrCurrencyLoaderService service = new CbrCurrencyLoaderService();
        service.load();
    }
}