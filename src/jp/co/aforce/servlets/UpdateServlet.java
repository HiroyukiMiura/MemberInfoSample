package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.MemberBean;
import jp.co.aforce.dao.MemberDAO;

/**
 * 
 * 更新画面サーブレット
 * 
 * @author s.akama
 *
 */
@WebServlet("/servlet/updateServlet")
public class UpdateServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("/views/menu.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		// 遷移先画面の設定
		String transitScreen = "/views/update.jsp";

		// 会員番号の取得
		String memberNo = request.getParameter("memberNo");

		// 戻るボタンを押された場合はメニュー画面を表示する。
		if (request.getParameter("buttonName").equals("戻る")) {

			transitScreen = "/views/menu.jsp";

			// 表示ボタンを押された場合は会員情報を表示する。
		} else if (request.getParameter("buttonName").equals("表示")) {

			MemberDAO memberDao = new MemberDAO();
			MemberBean memberBean = new MemberBean();

			memberBean = memberDao.search(memberNo);

			if (memberBean == null) {
				// エラーメッセージを設定
				memberBean = new MemberBean();
				memberBean.setEmsg("該当する会員情報が見つかりません");
			}

			request.setAttribute("memberBean", memberBean);

			// 変更ボタンを押された場合は会員情報を更新する
		} else if (request.getParameter("buttonName").equals("変更")) {

			// ユーザによって入力された情報を memberBean に格納する
			MemberBean memberBean = new MemberBean();
			memberBean.setMemberNo(memberNo);
			memberBean.setName(request.getParameter("name"));
			memberBean.setAge(request.getParameter("age"));
			memberBean.setBirthYear(request.getParameter("birthYear"));
			memberBean.setBirthMonth(request.getParameter("birthMonth"));
			memberBean.setBirthDay(request.getParameter("birthDay"));

			MemberDAO memberDAO = new MemberDAO();

			// 全て入力されているかどうかのチェック
			if (memberDAO.inputCheck(memberBean)) {

				// 変更処理を実行
				if (memberDAO.update(memberBean)) {

					memberBean.setCommsg("変更に成功しました");
					request.setAttribute("memberBean", memberBean);

				} else {

					memberBean.setCommsg("変更に失敗しました");
					request.setAttribute("memberBean", memberBean);

				}

				// 入力にエラーがある場合
			} else {

				// エラーメッセージを設定
				memberBean.setEmsg("入力されていない項目があります");
				request.setAttribute("memberBean", memberBean);

			}

		}

		// 遷移先画面に遷移する
		request.getRequestDispatcher(transitScreen).forward(request, response);

	}

}
