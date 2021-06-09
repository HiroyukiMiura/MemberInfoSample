package jp.co.aforce.dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.MemberBean;

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
	
	/**
	 * 必須項目が全て入力されているかどうか
	 * @param member
	 * @return
	 */
	public boolean inputCheck(MemberBean member) {

		if (member.getName().isEmpty() || member.getAge().isEmpty() || member.getBirthYear().isEmpty()
				|| member.getBirthMonth().isEmpty() || member.getBirthDay().isEmpty()) {

			return false;

		}

		return true;
	}

}
