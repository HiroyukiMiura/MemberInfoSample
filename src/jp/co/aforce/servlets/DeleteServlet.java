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

@WebServlet("/servlet/deleteServlet")
public class DeleteServlet extends HttpServlet {

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
		String transitScreen = "/views/delete.jsp";

		// 会員番号の取得
		String memberNo = request.getParameter("memberNo");

		// 戻るボタンを押された場合はメニュー画面を表示する。
		if (request.getParameter("buttonName").equals("戻る")) {

			transitScreen = "/views/menu.jsp";

			// 表示ボタンを押された場合は会員情報を表示する。
		} else if (request.getParameter("buttonName").equals("表示")) {

			MemberDAO memberDao = new MemberDAO();
			MemberBean memberBean = new MemberBean();
			try {
				memberBean = memberDao.search(memberNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (memberBean == null) {
				// エラーメッセージを設定
				memberBean = new MemberBean();
				memberBean.setEmsg("該当する会員情報が見つかりません");
			}

			request.setAttribute("memberBean", memberBean);

		} else if (request.getParameter("buttonName").equals("削除")) {

			// モデルをインスタンス化する
			MemberDAO memberDao = new MemberDAO();

			MemberBean memberBean = new MemberBean();

			// 変更処理を実行
			try {
				if (memberDao.delete(memberNo)) {

					memberBean.setCommsg("削除に成功しました");
					request.setAttribute("memberBean", memberBean);

				} else {

					memberBean.setCommsg("削除に失敗しました");
					request.setAttribute("memberBean", memberBean);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rDispatcher = request.getRequestDispatcher(transitScreen);
		rDispatcher.forward(request, response);

	}

}
