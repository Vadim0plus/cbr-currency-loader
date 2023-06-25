package ru.vtukhvatullin.cbrcurrloader.dao;

import com.google.gson.Gson;
import ru.vtukhvatullin.cbrcurrloader.config.AppConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Map;

public class CbrCurrencyHttpClient {
    protected Gson gson;
    protected HttpClient httpClient;

    public CbrCurrencyHttpClient() {
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public CbrCurrencyResponse getCurrencies() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(AppConfig.cbrCurrencyUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), CbrCurrencyResponse.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class CbrCurrencyResponse {
        private java.util.Date Date;
        private Date PreviousDate;
        private String PreviousURL;
        private Date Timestamp;
        private Map<String, CbrCurrency> Valute;

        public CbrCurrencyResponse(java.util.Date date, java.util.Date previousDate, String previousURL, java.util.Date timestamp, Map<String, CbrCurrency> valute) {
            Date = date;
            PreviousDate = previousDate;
            PreviousURL = previousURL;
            Timestamp = timestamp;
            Valute = valute;
        }

        public java.util.Date getDate() {
            return Date;
        }

        public java.util.Date getPreviousDate() {
            return PreviousDate;
        }

        public String getPreviousURL() {
            return PreviousURL;
        }

        public java.util.Date getTimestamp() {
            return Timestamp;
        }

        public Map<String, CbrCurrency> getValute() {
            return Valute;
        }

        public static class CbrCurrency {
            protected String ID;
            protected String NumCode;
            protected String CharCode;
            protected String Nominal;
            protected String Name;
            protected BigDecimal Value;
            protected BigDecimal Previous;

            public CbrCurrency(String ID, String numCode, String charCode, String nominal, String name, BigDecimal value, BigDecimal previous) {
                this.ID = ID;
                NumCode = numCode;
                CharCode = charCode;
                Nominal = nominal;
                Name = name;
                Value = value;
                Previous = previous;
            }

            public String getID() {
                return ID;
            }

            public String getNumCode() {
                return NumCode;
            }

            public String getCharCode() {
                return CharCode;
            }

            public String getNominal() {
                return Nominal;
            }

            public String getName() {
                return Name;
            }

            public BigDecimal getValue() {
                return Value;
            }

            public BigDecimal getPrevious() {
                return Previous;
            }
        }
    }
}
