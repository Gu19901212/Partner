package com.nwnu.yiqing.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
/**
 * ��¼
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;

import com.nwnu.yiqing.bean.Operator;
import com.nwnu.yiqing.bean.Page;
import com.nwnu.yiqing.bean.SearchProperty;
import com.nwnu.yiqing.config.BaseConfig;
import com.nwnu.yiqing.dao.StudentDao;
import com.nwnu.yiqing.dao.TeacherDao;
import com.nwnu.yiqing.dao.CollegeadminDao;
import com.nwnu.yiqing.dao.SchoolOfficeDao;
import com.nwnu.yiqing.entity.Student;
import com.nwnu.yiqing.entity.Teacher;
import com.nwnu.yiqing.entity.Collegeadmin;
import com.nwnu.yiqing.entity.SchoolOffice;
import com.nwnu.yiqing.util.StringUtil;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870852067427524781L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String vcode = req.getParameter("vcode");
		String msg = "success";
		if(StringUtil.isEmpty(name)){
			msg = "�û�������Ϊ��!";
		}
		if(StringUtil.isEmpty(password)){
			msg = "���벻��Ϊ��!";
		}
		if(StringUtil.isEmpty(vcode)){
			msg = "��֤�벻��Ϊ��!";
		}
		if("success".equals(msg)){
			Object loginCpacha = req.getSession().getAttribute("loginCpacha");
			if(loginCpacha == null){
				msg = "session�ѹ��ڣ���ˢ��ҳ������ԣ�";
			}else{
				if(!vcode.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
					msg = "��֤�����";
				}
			}
			
		}
		if("success".equals(msg)){
			String typeString = req.getParameter("type");
			try {
				int type = Integer.parseInt(typeString);
				if(type == 1){
					//ѧУ���߰��û�
					SchoolOfficeDao adminDao = new SchoolOfficeDao();
					SchoolOffice admin = adminDao.getAdmin(name);
					adminDao.closeConnection();
					if(admin == null){
						msg = "�����ڸ��û���";
					}
					if(admin != null){
						if(!password.equals(admin.getPassword())){
							msg = "�������";
						}else{
								req.getSession().setAttribute("user", admin);
								req.getSession().setAttribute("userType", type);
						}
					}
				}else if(type == 2){
					//ѧ����¼
					StudentDao studentDao = new StudentDao();
					Page<Student> page = new Page<Student>(1, 10);
					page.getSearchProperties().add(new SearchProperty("name", name, Operator.EQ));
					Page<Student> studentPage = studentDao.findList(page);
					studentDao.closeConnection();
					if(studentPage.getConten().size() == 0){
						msg = "�����ڸ��û���";
					}else{
						Student student = studentPage.getConten().get(0);
						if(!password.equals(student.getPassword())){
							msg = "�������";
						}else{
							req.getSession().setAttribute("user", student);
							req.getSession().setAttribute("userType", type);
						}
					}
					
				}else if(type == 3){
					//ѧԺ�����˵�¼
					CollegeadminDao collegeDao = new CollegeadminDao();
					Page<Collegeadmin> page = new Page<Collegeadmin>(1, 10);
					page.getSearchProperties().add(new SearchProperty("name", name, Operator.EQ));
					page = collegeDao.findList(page);
					collegeDao.closeConnection();
					if(page.getConten().size() == 0){
						msg = "�����ڸ��û���";
					}else{
						Collegeadmin college = page.getConten().get(0);
						if(!password.equals(college.getPassword())){
							msg = "�������";
						}else{
							req.getSession().setAttribute("user", college);
							req.getSession().setAttribute("userType", type);
						}
					}
				}else if(type == 4){
						//��ʦ��¼
						TeacherDao studentDao = new TeacherDao();
						Page<Teacher> page = new Page<Teacher>(1, 10);
						page.getSearchProperties().add(new SearchProperty("name", name, Operator.EQ));
						Page<Teacher> studentPage = studentDao.findList(page);
						studentDao.closeConnection();
						if(studentPage.getConten().size() == 0){
							msg = "�����ڸ��û���";
						}else{
							Teacher teacher = studentPage.getConten().get(0);
							if(!password.equals(teacher.getPassword())){
								msg = "�������";
							}else{
								req.getSession().setAttribute("user", teacher);
								req.getSession().setAttribute("userType", type);
							}
						}
						
				}else{
					msg = "�û����ʹ���";
				}
			} catch (Exception e) {
				// TODO: handle exception
				msg = "�û����ʹ���";
			}
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(msg);
	}
}
