package jp.co.aforce.dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * DAO共通処理
 * @author s.akama
 *
 */
public class DAO {

	static DataSource ds;

	public Connection getConnection() throws Exception {
		
		if(ds==null) {
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/database");
		}
		return ds.getConnection();
	}

}
