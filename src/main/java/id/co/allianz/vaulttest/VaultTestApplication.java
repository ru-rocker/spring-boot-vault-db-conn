package id.co.allianz.vaulttest;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport.KeyValueBackend;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultToken;

import com.zaxxer.hikari.HikariDataSource;

import id.co.allianz.vaulttest.config.AppConfig;

@SpringBootApplication
public class VaultTestApplication implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private DataSource dataSource;

    public static void main(final String[] args) {
        SpringApplication.run(VaultTestApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {

        final HikariDataSource hds = (HikariDataSource) dataSource;

        final Logger LOGGER = LoggerFactory.getLogger(VaultTestApplication.class);
        final VaultToken login = appConfig.clientAuthentication().login();
        LOGGER.info("Token: {}", login.getToken());

        final VaultTemplate vaultTemplate = new VaultTemplate(appConfig.vaultEndpoint(),
                new TokenAuthentication(login.getToken()));
        final VaultKeyValueOperations operations = vaultTemplate.opsForKeyValue("database",
                KeyValueBackend.versioned());
        LOGGER.info("Resposne : {}", operations.get("postgres").getData());
        LOGGER.info("Max pool: {}", hds.getMaximumPoolSize());

        // LOGGER.info("Resposne : {}", operations.get("oracle/11g").getData());
        // final VaultTemplate vt = new VaultTemplate(appConfig.vaultEndpoint(), new TokenAuthentication("myroot"));
        // final Secrets secrets = new Secrets();
        // secrets.setUsername("hello");
        // secrets.setPassword("password");
        //
        // final VaultKeyValueOperations keyValue = vt.opsForKeyValue("kv", KeyValueBackend.versioned());
        // keyValue.put("secret", secrets);

    }

}
