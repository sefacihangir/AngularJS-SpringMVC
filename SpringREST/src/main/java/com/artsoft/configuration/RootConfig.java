package com.artsoft.configuration;

import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.artsoft.*")
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sbox?zeroDateTimeBehavior=convertToNull");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.artsoft.model");
		sessionFactoryBean.setHibernateProperties(hibProperties());

		return sessionFactoryBean;
	}
	

	private Properties hibProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.id.new_generator_mappings", "false");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");

		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
//	@Bean
//	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
//		return new HibernateJpaVendorAdapter();
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//
//		entityManagerFactory.setDataSource(dataSource());
//		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
//		entityManagerFactory.setJpaProperties(jpaProperties());
//		entityManagerFactory.setPackagesToScan("com.artsoft.model");
//		entityManagerFactory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
//		entityManagerFactory.setValidationMode(ValidationMode.NONE);
//
//		return entityManagerFactory;
//	}
//
//
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//		return transactionManager;
//	}
//
//
//
//	private Properties jpaProperties() {
//		Properties properties = new Properties();
//
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
//		properties.put("hibernate.show_sql", "false");
//		properties.put("hibernate.id.new_generator_mappings", "false");
//		properties.put("hibernate.enable_lazy_load_no_trans", "true");
//
//		return properties;
//	}	
	
	
}
