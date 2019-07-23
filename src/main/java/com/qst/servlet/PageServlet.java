package com.qst.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.dao.VoteDao;
import com.qst.entiy.PageUtil;
import com.qst.entiy.vote;


public class PageServlet extends  HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> map;

	private int count;
	PageUtil pageUtil=new PageUtil();
	VoteDao voteDao=new VoteDao();
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String flag = req.getParameter("flag");
		if("page".equals(flag)){
			//投票列表的上一个，下一页
			String goPage=req.getParameter("gopage");
			
			pageUtil.setRowPageData(0,voteDao.voteCount());
			map=new HashMap<String, Object>();
			map.put("pageUtil", pageUtil);
			count=voteDao.voteCount();
			pageUtil.setRowPageData(Integer.parseInt(goPage),count);
			List<vote> voInfors=voteDao.showVoteList(map);
			req.setAttribute("nowpage", pageUtil.getGoPage());
			req.setAttribute("voInfors", voInfors);
			req.getRequestDispatcher("auth_index.jsp").forward(req,resp);
		}else if("maintenancepage".equals(flag)){
			//维护页面的上一页，下一页
			count=voteDao.voteCount();
			pageUtil.setRowPageData(Integer.parseInt(req.getParameter("gopage")),count);
			List<vote> voInfors=voteDao.showVoteList(map);
			req.setAttribute("nowpage", pageUtil.getGoPage());
			req.setAttribute("voInfors", voInfors);
			req.getRequestDispatcher("maintenance.jsp").forward(req,resp);
		}
	}

}
