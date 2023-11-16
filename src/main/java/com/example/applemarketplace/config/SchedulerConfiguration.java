package com.example.applemarketplace.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1M")
public class SchedulerConfiguration {
    @Bean
    LockProvider getLockProvider(
            @Value("${app.shedlock.table-name}") String shedlockTableName,
            final DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource, shedlockTableName);
    }
}
