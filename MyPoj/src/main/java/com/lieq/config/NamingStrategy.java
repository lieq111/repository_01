package com.lieq.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
/**
 * 自定义数据库命名策略，只使用于hibernate 5.0.x之后版本
 * @author LiEQ
 *
 */
public class NamingStrategy extends SpringPhysicalNamingStrategy {
	
	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		if(identifier==null||identifier.getText().length()==0)return null;
		Identifier id = super.toPhysicalTableName(identifier, jdbcEnvironment);
		return Identifier.toIdentifier("t_"+id.getText());
	}
}
