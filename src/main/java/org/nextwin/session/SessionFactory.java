package org.nextwin.session;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {
	
private SqlSessionFactory factory;
	
	public SessionFactory(String mybatisXmlResource) {		
		try {
			InputStream inputStream = Resources.getResourceAsStream(mybatisXmlResource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SqlSession openSession() {
		return openSession(false);
	}
	
	public SqlSession openSession(boolean autoCommit) {
		SqlSession session = null;
		session = factory.openSession(autoCommit);
		return session;
	}

}
