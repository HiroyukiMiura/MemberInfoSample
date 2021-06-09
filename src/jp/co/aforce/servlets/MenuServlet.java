package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/menuServlet")
public class MenuServlet extends HttpServlet {

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

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		// 遷移先画面の設定
		String transitScreen = "/views/menu.jsp";

		// ボタンを押された場合はそれぞれの画面へ遷移。そうでない場合はメニュー画面を表示する。
		if (request.getParameter("buttonName").equals("会員情報新規登録")) {

			transitScreen = "/views/regist.jsp";

		} else if (request.getParameter("buttonName").equals("会員情報変更")) {

			transitScreen ="/views/update.jsp";

		} else if (request.getParameter("buttonName").equals("会員情報削除")) {

			transitScreen = "/views/delete.jsp";

		}

		// 遷移先画面に遷移する
		RequestDispatcher rDispatcher = request.getRequestDispatcher(transitScreen);
		rDispatcher.forward(request, response);

	}

}
