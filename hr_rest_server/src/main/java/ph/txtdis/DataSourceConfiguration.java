package ph.txtdis;

import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.util.Secure;

@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String databaseDriverClassName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Bean
    public DataSource dataSource() throws IOException {
        DataSource ds = new DataSource();
        ds.setDriverClassName(databaseDriverClassName);
        ds.setUrl(datasourceUrl);
        ds.setUsername(Secure.username());
        ds.setPassword(Secure.password());
        return ds;
    }
}