/**
 * 
 */
package com.ibm.ws.samples.config.data;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author lentiummmx
 *
 */
@Configuration
@EnableJpaRepositories(basePackages="com.ibm.ws.samples.repositories")
@EnableTransactionManagement
public class DataAccessConfiguration implements TransactionManagementConfigurer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessConfiguration.class);
	
	@Autowired
	private Environment environment;
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
	@Bean
	public DataSource jndiDataSource() {
		LOGGER.debug("dataSource.jndi :: " + environment.getProperty("dataSource.jndi"));
		final JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		jndiDataSourceLookup.setResourceRef(true);
		DataSource jndiDataSource = jndiDataSourceLookup.getDataSource(environment.getProperty("dataSource.jndi"));
		return jndiDataSource;
	}

	private JpaVendorAdapter eclipseLinkJpaVendorAdapter() {
		EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
		eclipseLinkJpaVendorAdapter.setGenerateDdl(true);
		eclipseLinkJpaVendorAdapter.setShowSql(true);
		return eclipseLinkJpaVendorAdapter;
	}
	
	private Properties eclipselinkJpaProperties() {
		Properties ecslnkJpaProperties = new Properties();

		ecslnkJpaProperties.put("eclipselink.deploy-on-startup", environment.getProperty("eclipselink.deploy-on-startup"));
		ecslnkJpaProperties.put("eclipselink.ddl-generation", environment.getProperty("eclipselink.ddl-generation"));	//"create-or-extend-tables");
		ecslnkJpaProperties.put("eclipselink.ddl-generation.output-mode", environment.getProperty("eclipselink.ddl-generation.output-mode"));
		ecslnkJpaProperties.put("eclipselink.logging.level.sql", environment.getProperty("eclipselink.logging.level.sql"));
		ecslnkJpaProperties.put("eclipselink.logging.parameters", environment.getProperty("eclipselink.logging.parameters"));
		ecslnkJpaProperties.put("eclipselink.weaving", environment.getProperty("eclipselink.weaving"));
		ecslnkJpaProperties.put("eclipselink.weaving.lazy", environment.getProperty("eclipselink.weaving.lazy"));
		ecslnkJpaProperties.put("eclipselink.weaving.internal", environment.getProperty("eclipselink.weaving.internal"));
		ecslnkJpaProperties.put("eclipselink.logging.level", environment.getProperty("eclipselink.logging.level"));
		ecslnkJpaProperties.put("eclipselink.jdbc.batch-writing", environment.getProperty("eclipselink.jdbc.batch-writing"));
		ecslnkJpaProperties.put("eclipselink.jdbc.batch-writing.size", environment.getProperty("eclipselink.jdbc.batch-writing.size"));
		ecslnkJpaProperties.put("eclipselink.jdbc.cache-statements", environment.getProperty("eclipselink.jdbc.cache-statements"));
		ecslnkJpaProperties.put("eclipselink.jdbc.cache-statements.size", environment.getProperty("eclipselink.jdbc.cache-statements.size"));
		ecslnkJpaProperties.put("eclipselink.cache.shared.default", environment.getProperty("eclipselink.cache.shared.default"));
		ecslnkJpaProperties.put("eclipselink.flush-clear.cache", environment.getProperty("eclipselink.flush-clear.cache"));
		ecslnkJpaProperties.put("eclipselink.cache.size.default", environment.getProperty("eclipselink.cache.size.default"));
		ecslnkJpaProperties.put("eclipselink.target-database", environment.getProperty("eclipselink.target-database"));

		return ecslnkJpaProperties;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		LOGGER.debug("after defining LocalContainerEntityManagerFactoryBean entityManagerFactory()");
		entityManagerFactoryBean.setDataSource(jndiDataSource());
		//Configuración para utilizar EclipseLink como provedor de JPA
		entityManagerFactoryBean.setJpaProperties(eclipselinkJpaProperties());
		entityManagerFactoryBean.setJpaVendorAdapter(eclipseLinkJpaVendorAdapter());
		//Configuración para utilizar Hibernate como provedor de JPA
		//entityManagerFactoryBean.setJpaProperties(hibernateJpaProperties());
		//entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManagerFactoryBean.setPackagesToScan(new String[]{"com.ibm.ws.samples.entities"});
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 */
	@Override
	@Bean(name = "transactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		LOGGER.debug("inside PlatformTransactionManager annotationDrivenTransactionManager()");
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		//jpaTransactionManager.setDataSource(jndiDataSource());
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}
	
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
	
}
