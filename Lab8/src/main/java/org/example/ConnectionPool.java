package org.example;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3305/db");
        dataSource.setUsername("root");
        dataSource.setPassword("Bananageo2bucati");
        dataSource.setInitialSize(5); // Dimensiunea initiala a pool-ului
        dataSource.setMaxTotal(20); // Numarul maxim de conexiuni
        dataSource.setDefaultAutoCommit(false);
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
