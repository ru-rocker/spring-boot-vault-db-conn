package com.rurocker.vaulttest;

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

import com.rurocker.vaulttest.config.AppConfig;
import com.rurocker.vaulttest.dto.Secrets;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class VaultTestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(VaultTestApplication.class, args);
    }

}
