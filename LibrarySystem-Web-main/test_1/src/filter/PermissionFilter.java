package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.User;

public class PermissionFilter implements Filter {
	
	private String notCheckPath;//����Ҫ���˶������ַ����web.xml�ļ��ж�ȡ
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		String path = request.getServletPath();
		
		
		System.out.println("�����ַurl-pattern:"+path);
		
		if(notCheckPath.indexOf(path) == -1){
			HttpSession session = request.getSession();
			if(session.getAttribute("currentUse") == null){
				request.setAttribute("info", "û��Ȩ�޷���");
				request.getRequestDispatcher("/error.jsp").forward(request, 
						resp);
				
			}
			else {//�Ѿ���¼��ֱ�ӷ���
				
				String value=null;
				Boolean flag=false;
				Cookie[] cookie=request.getCookies();
				for(Cookie a:cookie){
					if(a.getName().equals("username")){
						flag=true;
						value=a.getValue();
						break;
					}
				}
				System.out.println(value);
				if(flag){
					
					User user=null;
					try {
						//user=dao.getUser(value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(user);
					session.removeAttribute("currentUser");
					String chr=user.getChrName();
					session.setAttribute("currentUser", chr);
				}
				else{
					request.setAttribute("info", "û��Ȩ�޷���");
					request.getRequestDispatcher("/error.jsp").forward(request, resp);
				}
				
				chain.doFilter(req, resp);
			}
		}
		else{//��ַ����Ҫ���˵ģ�ֱ�ӷ���
			chain.doFilter(req, resp);
		}
		
		

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// ��web.xml�����ļ��е�filter�ж�ȡ��ΪnotCheckPath�ĳ�ʼֵ
		notCheckPath = config.getInitParameter("notCheckPath");

	}

}
