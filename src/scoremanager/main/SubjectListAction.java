package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

//科目情報・一覧の情報取得
public class SubjectListAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		HttpSession session = req.getSession(); // セッションの開始
		Teacher teacher = (Teacher)session.getAttribute("user");

		List<Subject> Subjects = null;
		SubjectDao jDao = new SubjectDao();

		Subjects = jDao.filter(teacher.getSchool());

		req.setAttribute("subjects", Subjects);
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}