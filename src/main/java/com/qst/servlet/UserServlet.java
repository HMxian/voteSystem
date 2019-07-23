package com.qst.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.dao.UserDao;
import com.qst.dao.VoteDao;
import com.qst.entiy.PageUtil;
import com.qst.entiy.User;
import com.qst.entiy.vote;




public class UserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> map;
	private int goPage;
	private int count;
	PageUtil pageUtil=new PageUtil();
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String flag=req.getParameter("flag");
		if("login".equals(flag)){
			String username=req.getParameter("userName");
			String password=req.getParameter("passWord");
			int count=UserDao.selectByNM(username, password);
			
			if(count>0)
			{
				UserDao userDao = new UserDao();
				User user = userDao.selectByName1(username);
				HttpSession session = req.getSession();
				System.out.println(user.getVU_USER_NAME());
				session.setAttribute("user",user);
				
				//resp.sendRedirect("index.jsp");
				VoteDao voteDao=new VoteDao();
				//投票主题总数
				int count1=voteDao.voteCount();
				pageUtil.setRowPageData(goPage,count1);
				//总页数
				int totalpage=pageUtil.getPageNum();
				//当前页
				int nowpage=pageUtil.getGoPage();
				map=new HashMap<String, Object>();
				map.put("pageUtil", pageUtil);
				List<vote> voInfors=voteDao.showVoteList(map);
				session.setAttribute("voInfors", voInfors);
				session.setAttribute("totalpage", totalpage);
				session.setAttribute("nowpage", nowpage);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
			else{
				resp.getWriter().print("<script>alert('用户或密码错误!');</script>");
			}
			
		}
		else if("register".equals(flag)){
			String username = req.getParameter("userName");
			String password = req.getParameter("passWord");
			int count = UserDao.selectByName(username);
			if(count<=0){
				
				User user = new User("1", username, password, 2,2);
				int cou=UserDao.insert(user);
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("register-rseult.html");
			}
			else {
				resp.getWriter().print("<script>alert('用户名已存在!');</script>");
			}
		}
		
}
}