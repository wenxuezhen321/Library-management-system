
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;
import dao.UserDao;


@WebServlet(urlPatterns ="/librarylogin.do")
public class LoginController extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode= request.getParameter("vcode");
		String autoLogin = request.getParameter("autoLogin");
		
		HttpSession session = request.getSession();
		String saveVcode = (String) session.getAttribute("verifyCode");
		String forwardPath = "";
		//�Ƚ��������֤������ɵ���֤���Ƿ�һ��
		if(!vcode.equalsIgnoreCase(saveVcode)){
			request.setAttribute("info", "��֤�벻��ȷ��");
			forwardPath = "/error.jsp";
		}
		else{//��֤����ȷ
			UserDao userDao = new UserDao();
			User user = userDao.get(userName);
			if(user==null){//�û��������ڣ�
				request.setAttribute("info", "��������û��������ڣ�");
				forwardPath="error.jsp";
			}
			else{
				if(!user.getPassword().equals(password)){
					request.setAttribute("info", "����������벻��ȷ��");
					forwardPath="error.jsp";
				}
				else{
					if("on".equalsIgnoreCase(autoLogin)){
						Cookie cookie=new Cookie("username",userName);
						cookie.setPath("/");
						cookie.setMaxAge(60*60*24*7);
						response.addCookie(cookie);
					}
					session.setAttribute("currentUser", user);
					forwardPath="/main.jsp";
				}
				
			}
		
	}	
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request,response);
	
		
	}

}
