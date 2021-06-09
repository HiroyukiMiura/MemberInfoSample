package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.MemberBean;
import jp.co.aforce.dao.MemberDAO;

@WebServlet("/servlet/registServlet")
public class RegistServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// GETリクエストはあり得ないので、無条件でログイン画面に飛ばす
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/menu.jsp");
		rDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字のエンコードを UTF-8 とする。
		request.setCharacterEncoding("UTF-8");

		// 遷移先画面の設定
		String transitScreen = "/views/regist.jsp";

		String button = request.getParameter("buttonName");
		if (button.equals("")) {
			transitScreen = "/views/regist.jsp";
		} else {

			// 戻るボタンを押された場合はメニュー画面を表示する。
			if (request.getParameter("buttonName").equals("戻る")) {

				transitScreen = "/views/menu.jsp";

			} else {

				// ユーザによって入力された情報を memberBean に格納する
				MemberBean memberBean = new MemberBean();
				memberBean.setName(request.getParameter("name"));
				memberBean.setAge(request.getParameter("age"));
				memberBean.setBirthYear(request.getParameter("birthYear"));
				memberBean.setBirthMonth(request.getParameter("birthMonth"));
				memberBean.setBirthDay(request.getParameter("birthDay"));

				// Daoの実行
				MemberDAO memberDao = new MemberDAO();

				// 全て入力されているかどうかのチェック
				if (memberDao.inputCheck(memberBean)) {

					// 登録処理を実行
					try {
						
						if (memberDao.insert(memberBean)) {

							memberBean.setCommsg("登録に成功しました");
							request.setAttribute("memberBean", memberBean);

						} else {

							memberBean.setCommsg("登録に失敗しました");
							request.setAttribute("memberBean", memberBean);

						}
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					// 入力にエラーがある場合
				} else {

					// エラーメッセージを設定
					memberBean.setEmsg("入力されていない項目があります");
					request.setAttribute("memberBean", memberBean);

				}

			}

		}

		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rDispatcher = request.getRequestDispatcher(transitScreen);
		rDispatcher.forward(request, response);

	}

}
