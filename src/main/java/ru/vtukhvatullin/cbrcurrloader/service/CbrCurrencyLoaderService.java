package ru.vtukhvatullin.cbrcurrloader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vtukhvatullin.cbrcurrloader.config.DbConnectionFactory;
import ru.vtukhvatullin.cbrcurrloader.dao.CbrCurrencyHttpClient;
import ru.vtukhvatullin.cbrcurrloader.dao.CurrencyDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CbrCurrencyLoaderService {
    public CurrencyDAO currencyDAO;
    public CbrCurrencyHttpClient cbrCurrencyHttpClient;
    private static Logger logger = LoggerFactory.getLogger(CbrCurrencyLoaderService.class);

    public CbrCurrencyLoaderService() {
        this.currencyDAO = new CurrencyDAO(DbConnectionFactory.getConnection());
        this.cbrCurrencyHttpClient = new CbrCurrencyHttpClient();
    }

    public void load() {
        logger.info("start load currencies");
        CbrCurrencyHttpClient.CbrCurrencyResponse currencyResponse = cbrCurrencyHttpClient.getCurrencies();
        List<CurrencyDAO.CurrencyRecord> records = currencyResponse.getValute().values().stream()
                .map(it -> new CurrencyDAO.CurrencyRecord(it.getID(), it.getNumCode(), it.getCharCode(), it.getNominal(), it.getName(), it.getValue(), it.getPrevious()))
                .collect(Collectors.toList());
        currencyDAO.deleteAll();
        currencyDAO.save(records);
        logger.info(String.format("updating %s currencies", records.size()));
    }
}
