package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		String cd = req.getParameter("cd");

		Subject sub = new Subject();
		sub.setCd(cd);

		SubjectDao jDao = new SubjectDao();
		boolean hantei = jDao.delete(sub);

		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}