package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		HttpSession session = req.getSession(); // セッションの開始
		Teacher teacher = (Teacher)session.getAttribute("user");
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		SubjectDao jDao = new SubjectDao();
		Subject hanbetu = jDao.get(cd, teacher.getSchool());

		// 科目コードが3文字ではない時
		if (cd.length() != 3) {
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			errors.put("cd", "科目コードは3文字で入力してください");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
		// 科目コードの重複がある場合
		} else if (hanbetu != null){
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			errors.put("cd", "科目コードが重複しています");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
		}

		Subject sub = new Subject();
		sub.setCd(cd);
		sub.setName(name);
		sub.setSchool(teacher.getSchool());

		boolean flag = jDao.save(sub);
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}