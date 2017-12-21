package com.gigglehd;

import java.nio.charset.Charset;
import java.util.Locale;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.gigglehd" })
@MapperScan(value = "com.gigglehd.persistence")
public class Gigglehd1Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Gigglehd1Application.class, args);
	}

	@Autowired
	DataSource dataSource;

	@Bean
	public SqlSessionFactory factory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		Resource configLocation = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
		bean.setConfigLocation(configLocation);
		Resource[] mapperLocations = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mappers/*Mapper.xml");
		bean.setMapperLocations(mapperLocations);
		return bean.getObject();
	}

	@Bean
	public CharacterEncodingFilter filter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		return filter;
	}

	@Bean
	public TilesViewResolver viewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public TilesConfigurer configurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/config/tiles.xml" });
		return configurer;
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Bean
	public Filter charFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	public ReloadableResourceBundleMessageSource localeResolverf() {
		ReloadableResourceBundleMessageSource f = new ReloadableResourceBundleMessageSource();
		f.setBasename("classpath:messages");
		f.setDefaultEncoding("UTF-8");
		return f;
	}
	
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver resolver=new CookieLocaleResolver();
		resolver.setCookieMaxAge(1000*60*60*24*7);
		resolver.setDefaultLocale(Locale.US);
		resolver.setCookieName("haha");
		return resolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("locale");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
