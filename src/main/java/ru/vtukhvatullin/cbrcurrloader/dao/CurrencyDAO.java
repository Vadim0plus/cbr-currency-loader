package ru.vtukhvatullin.cbrcurrloader.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CurrencyDAO {

    private Connection connection;

    public CurrencyDAO(Connection connection) {
        this.connection = connection;
    }

    public void deleteAll() {
        try {
            PreparedStatement ps = this.connection.prepareStatement("delete from currency");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(List<CurrencyRecord> currencyRecords) {
        try {
            for (CurrencyRecord currencyRecord : currencyRecords) {
                PreparedStatement ps = this.connection
                        .prepareStatement("insert into currency(id, numcode, charcode, nominal, name, value, previous) values (?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, currencyRecord.getID());
                ps.setString(2, currencyRecord.getNumCode());
                ps.setString(3, currencyRecord.getCharCode());
                ps.setString(4, currencyRecord.getNominal());
                ps.setString(5, currencyRecord.getName());
                ps.setBigDecimal(6, currencyRecord.getValue());
                ps.setBigDecimal(7, currencyRecord.getPrevious());

                ps.execute();
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class CurrencyRecord {
        protected String ID;
        protected String NumCode;
        protected String CharCode;
        protected String Nominal;
        protected String Name;
        protected BigDecimal Value;
        protected BigDecimal Previous;

        public CurrencyRecord(String ID, String Name) {
            this.ID = ID;
            this.Name = Name;
        }

        public CurrencyRecord(String ID, String numCode, String charCode, String nominal, String name, BigDecimal value, BigDecimal previous) {
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