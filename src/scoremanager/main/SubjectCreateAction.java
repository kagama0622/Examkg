package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

//科目情報・作成への画面遷移
public class SubjectCreateAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}
}