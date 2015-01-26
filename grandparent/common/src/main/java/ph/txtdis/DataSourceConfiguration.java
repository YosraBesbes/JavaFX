package ph.txtdis;

import java.io.IOException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        ds.setUsername(getSecureUsername());
        ds.setPassword(getSecurePassword());
        return ds;
    }

    private String getSecureUsername() throws IOException {
        return encode("username");
    }

    private String getSecurePassword() throws IOException {
        return encode("password");
    }

    private String encode(String text) throws IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("I'mAdmin4txtDIS@PostgreSQL");
        Properties props = new EncryptableProperties(encryptor);
        props.load(this.getClass().getResourceAsStream("/config/application.properties"));
        return props.getProperty("spring.datasource." + text);
    }
}