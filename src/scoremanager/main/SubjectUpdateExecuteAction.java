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

// 科目情報・更新の情報入力後
public class SubjectUpdateExecuteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		Subject sub = new Subject();
		SubjectDao jDao = new SubjectDao();
		Subject IF = jDao.get(cd, teacher.getSchool());// 判定用

		// 科目が存在しない場合
		if (IF == null){
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			errors.put("cd", "科目が存在していません");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
		//	科目が存在する場合
		} else {
			sub.setCd(cd);
			sub.setName(name);
			sub.setSchool(teacher.getSchool());
			jDao.save(sub);
		}

		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}