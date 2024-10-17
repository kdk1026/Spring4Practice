package config.spring.app;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@PropertySource("classpath:properties/jdbc.properties")
@EnableTransactionManagement
@Import({
	TransactionalConfig.class
})
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource h2DataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getRequiredProperty("h2.jdbc.driver"));
		hikariConfig.setJdbcUrl(env.getRequiredProperty("h2.jdbc.url"));
		hikariConfig.setUsername(env.getRequiredProperty("h2.jdbc.username"));
		hikariConfig.setPassword(env.getRequiredProperty("h2.jdbc.password"));
		hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(this.h2DataSource());
	}

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(this.h2DataSource());
		flyway.setBaselineOnMigrate(true);
		return flyway;
	}

}
