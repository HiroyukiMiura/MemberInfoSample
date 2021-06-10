package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jp.co.aforce.beans.MemberBean;

public class MemberDAO extends DAO {

	// 会員番号による会員情報の取得
	public MemberBean search(String keyword) {

		MemberBean member = new MemberBean();

		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("select * from members where member_no = ?");
			st.setString(1, keyword);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				member.setMemberNo(keyword);
				member.setName(rs.getString("name"));
				member.setAge(String.valueOf(rs.getInt("age")));
				member.setBirthYear(String.valueOf(rs.getInt("birth_year")));
				member.setBirthMonth(String.valueOf(rs.getInt("birth_month")));
				member.setBirthDay(String.valueOf(rs.getInt("birth_day")));

			} else {

				member = null;

			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	// 会員情報の登録
	public boolean insert(MemberBean member) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("insert into members values(?,?,?,?,?,?)");

			// 会員番号作成
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");
			String memberNo = "A" + format.format(date);

			st.setString(1, memberNo);
			st.setString(2, member.getName());
			st.setInt(3, Integer.valueOf(member.getAge()));
			st.setInt(4, Integer.valueOf(member.getBirthYear()));
			st.setInt(5, Integer.valueOf(member.getBirthMonth()));
			st.setInt(6, Integer.valueOf(member.getBirthDay()));

			int line = st.executeUpdate();

			if (line > 0) {
				result = true;
			}

			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	// 会員情報の変更
	public boolean update(MemberBean member) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = getConnection();
			st = con.prepareStatement(
					"update members set name=?,age=?,birth_year=?,birth_month=?,birth_day=? WHERE member_no=?");

			st.setString(1, member.getName());
			st.setInt(2, Integer.valueOf(member.getAge()));
			st.setInt(3, Integer.valueOf(member.getBirthYear()));
			st.setInt(4, Integer.valueOf(member.getBirthMonth()));
			st.setInt(5, Integer.valueOf(member.getBirthDay()));
			st.setString(6, member.getMemberNo());

			int line = st.executeUpdate();

			if (line > 0) {
				result = true;
			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 会員情報の削除
	public boolean delete(String memberNo) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = getConnection();
			st = con.prepareStatement(
					"delete from members where member_no= ?");
			st.setString(1, memberNo);

			int line = st.executeUpdate();

			if (line > 0) {
				result = true;
			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
