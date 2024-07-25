package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

//科目情報・削除の情報取得
public class SubjectDeleteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {


		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = req.getParameter("cd");

		SubjectDao jDao = new SubjectDao();
		Subject sub = jDao.get(cd, teacher.getSchool());

		req.setAttribute("sub_date", sub);

		req.getRequestDispatcher("subject_delete.jsp").forward(req, res
	}
}