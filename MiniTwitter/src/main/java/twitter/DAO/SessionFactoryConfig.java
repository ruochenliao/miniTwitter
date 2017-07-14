package twitter.DAO;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
//@ComponentScan({"twitter.DAO"})

//@ComponentScan

public class SessionFactoryConfig implements TransactionManagementConfigurer{
	  @Inject
	  private SessionFactory sessionFactory;

	  @Bean
	  public DataSource dataSource() {
		/*
		EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
	    edb.setType(EmbeddedDatabaseType.H2);
	    edb.addScript("schema.sql");
	    edb.addScript("test-data.sql");
	    EmbeddedDatabase embeddedDatabase = edb.build();
	    return embeddedDatabase;
	    */
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/twitter");
		ds.setUsername("root");
		ds.setPassword("272317227");
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		return ds;
		
	  }

	  public PlatformTransactionManager annotationDrivenTransactionManager() {
	    System.out.println(sessionFactory);
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(sessionFactory);
	    return transactionManager;
	  }

	  @Bean
	  public SessionFactory sessionFactoryBean() {
	    try {
	      LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
	      lsfb.setDataSource(dataSource());
	      lsfb.setPackagesToScan("twitter.domain");
	      Properties props = new Properties();
	      props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
	      lsfb.setHibernateProperties(props);
	      lsfb.afterPropertiesSet();
	      SessionFactory object = lsfb.getObject();
	      return object;
	    } catch (IOException e) {
	      return null;
	    }
	  }

}
