package me.fulln.ftz.config.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author fulln
 * @description db的配置
 * @date Created in  14:37  2020-07-22.
 **/
@Slf4j
@Configuration
@MapperScan(basePackages = {"me.fulln.ftz.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MysqlConfig {


	@Autowired
	private MybatisProperties mybatisProperties;

	@Bean(name = "db")
	public DataSource dataSource(DataSourceProperties properties) {
		return DataSourceBuilder.create(properties.getClassLoader())
				.type(HikariDataSource.class)
				.driverClassName(properties.determineDriverClassName())
				.url(properties.determineUrl())
				.username(properties.determineUsername())
				.password(properties.determinePassword())
				.build();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db") DataSource dataSource) throws Exception {
		//通用设置
		GlobalConfig globalConfig = new GlobalConfig();
		GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
		dbConfig.setIdType(IdType.AUTO);
		dbConfig.setLogicDeleteValue("1");
		dbConfig.setLogicNotDeleteValue("0");
		globalConfig.setDbConfig(dbConfig);
		globalConfig.setBanner(false);

		MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
		mybatisConfiguration.setMapUnderscoreToCamelCase(true);

		// 配置SQL打印，仅用于开发环境
		if ("dev".equals(System.getProperty("spring_profiles_active"))) {
			mybatisConfiguration.setLogImpl(StdOutImpl.class);
		}

		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setPlugins(new PaginationInterceptor());
		sqlSessionFactory.setConfiguration(mybatisConfiguration);
		sqlSessionFactory.setGlobalConfig(globalConfig);
		sqlSessionFactory.setDataSource(dataSource);
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mybatisProperties.getMapperLocations()[0]);
			sqlSessionFactory.setMapperLocations(resources);
		} catch (IOException e) {
			log.error("加载 sqlmap 配置文件失败，路径={}, 详情={}", mybatisProperties.getMapperLocations()[0], e.getMessage(), e);
		}
		return sqlSessionFactory.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		// 使用上面配置的Factory
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "TransactionManager")
	public DataSourceTransactionManager TransactionManager(@Qualifier("db") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}


}