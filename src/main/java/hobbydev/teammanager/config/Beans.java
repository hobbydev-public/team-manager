package hobbydev.teammanager.config;

import liquibase.integration.spring.SpringLiquibase;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

/**
 * Common spring beans to be used in application
 */
@Configuration
@PropertySource("classpath:application.properties")
public class Beans {

	/**
	 * Liquibase bean is responsible for database migrations.
	 * Migrations are executed each time the application starts or restarts.
	 *
	 * @param dataSource datasource definition for DB migrations
	 *
	 * @return Spring Liquibase bean
     */
	@Bean
	@Autowired
	public SpringLiquibase springLiquibase(DataSource dataSource) {
		SpringLiquibase bean = new SpringLiquibase();

		bean.setDataSource(dataSource);
		bean.setChangeLog("classpath:db_migrations/master.xml");

		return bean;
	}

	/**
	 * Password encryptor for Spring security
	 *
	 * @return Password Encryptor bean
     */
	@Bean
	public PasswordEncryptor passwordEncryptor() {
		
		PasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		
		return passwordEncryptor;
	}

	/**
	 * Password encoder for Spring security
	 *
	 * @param passwordEncryptor encryptor to be used by encoder
	 *
	 * @return Password Encoder bean
	 */
	@Bean
	@Autowired
	public PasswordEncoder  passwordEncoder(PasswordEncryptor passwordEncryptor) {
		
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		
		return passwordEncoder;
	}

	/**
	 * Bean for application.properties file resolution
	 * @return
     */
	@Bean
	public PropertySourcesPlaceholderConfigurer properties() {
		
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		
		return properties;
	}
}
