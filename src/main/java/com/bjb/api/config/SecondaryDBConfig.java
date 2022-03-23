package com.bjb.api.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "secondaryEntityManagerFactory",
  transactionManagerRef = "secondaryTransactionManager",
  basePackages = { "com.bjb.api.repository2" }
)
public class SecondaryDBConfig {	
	@Autowired
	Environment env;
	
	@Bean(name="secondaryDataSource")
//	@Primary
	@ConfigurationProperties(prefix="second.datasource")
	public DataSource secondaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setUrl(env.getProperty("second.datasource.url"));
	    dataSource.setUsername(env.getProperty("second.datasource.username"));
	    dataSource.setPassword(env.getProperty("second.datasource.password"));
	    dataSource.setDriverClassName(env.getProperty("second.datasource.driver-class-name"));
	    return dataSource;
	}

//	@Primary
	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
		return builder
				.dataSource(secondaryDataSource)
				.packages("com.bjb.api.model2")
				.build();
	}

	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(
			@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
		return new JpaTransactionManager(secondaryEntityManagerFactory);
	}
}