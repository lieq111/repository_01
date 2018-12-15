package com.lieq.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
public class DruidConfiguration {
	/**
	 * 初始化DruidDataSource对象
	 * @ConfigurationProperties(prefix="spring.datasource"),会根据我们在application中设置的该前缀注入值
	 * @returna
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource druid() {
		DruidDataSource ds = new DruidDataSource();
		System.out.println("************************************************DruidDataSourc初始化。。");
		return ds;
	}
	/**
	 * 注册后台界面Servlet bean, 用于显示后台界面
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		//创建StatViewServlet，绑定到/druid/下
		//开启后，访问 项目路径/druid就可以看到druid管理后台
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, String> param = new HashMap<>();
		//设置druid管理后台登录名和密码
		param.put("loginUsername", "admin");
		param.put("loginPassword", "123456");
		//哪些IP允许访问后台，""代表所有地址
		param.put("allow", "");
		//不允许该IP访问
		param.put("deny", "11.2.1.2");
		bean.setInitParameters(param);
		return bean;
	}
	/**
	 * 用于监听获取应用数据， Filter用于收集数据，Servler用于展现数据
	 * @return
	 */
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		//设置过滤器
		bean.setFilter(new WebStatFilter());
		bean.addUrlPatterns("/*");
		//排除静态资源
		bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");;
		return bean;
	}
	
}
