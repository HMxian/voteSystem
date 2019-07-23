package com.qst.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.dao.VoteDao;
import com.qst.entiy.PageUtil;
import com.qst.entiy.VoteDetail;
import com.qst.entiy.vote;

public class VoteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final int PageUtil = 0;
	 List<VoteDetail> details;
	 private int gopage;
	 VoteDao voteDao = new VoteDao();
		vote voteInfor = new vote();
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String flag=req.getParameter("flag");
		if(flag.equals("addnew")){
			//添加新投票
			if(req.getParameter("vname")==null||req.getParameter("vname").equals("")){
				resp.sendRedirect("returnlist.jsp");
			}else{
				boolean t1,t2;
				String vname=req.getParameter("vname");
				String vtype=req.getParameter("votetype");
				//获取添加投票的选项
				String []opt=req.getParameterValues("voteoption");
				t1=voteDao.addVote(vname, vtype);
				int vid=voteDao.getInsertId();
				t2=voteDao.addOption(vid, opt);
				
				if(t1&&t2){
				
					resp.sendRedirect("auth_index.jsp");
				}
			}
		}else if(flag.equals("checkvote")){
			//查看投票
			
			int vid=Integer.parseInt(req.getParameter("vid"));
			details=new ArrayList<VoteDetail>();
			details=voteDao.showVoteDetail(vid);
			vote voteInfor=new vote();
			voteInfor=voteDao.getInfor(vid);
			req.setAttribute("voteInfor", voteInfor);
			//总票数
			int totalNumber=0;
			for(VoteDetail v:details){
				totalNumber+=v.getOptNumber();
			}
			if(totalNumber==0){
				totalNumber=1;
			}
			req.setAttribute("details", details);
			req.setAttribute("totalNumber", totalNumber);
			req.getRequestDispatcher("showvote.jsp").forward(req,resp);
		}
		//进行投票
		else if(flag.equals("join")){
			
			List<VoteDetail> voteDetails = new ArrayList<VoteDetail>();
			
			int vid = Integer.parseInt(req.getParameter("vid"));
			voteDetails = voteDao.showVoteDetail(vid);
			
			voteInfor= voteDao.getInfor(vid);
			req.setAttribute("voteDetails", voteDetails);
			req.setAttribute("voteInfor",voteInfor);
		
			req.getRequestDispatcher("auth_update.jsp").forward(req, resp);
		}
		else if(flag.equals("vote")){
			//投票
			if(req.getParameter("voteoption")==null||req.getParameter("voteoption").equals("")){
				
				resp.sendRedirect("auth_index.jsp");
			}else{
				String [] arr = req.getParameterValues("voteoption");
				boolean b = voteDao.vote(arr);
				if(b)
				{
					resp.sendRedirect("auth_index.jsp");
				}
				else {
					resp.getWriter().print("<script>alert('xxxxxxxxxxxx');</script>");
				}
			}
			
		}//搜索投票
		else if(flag.equals("votesearch")){
			String str = req.getParameter("search");
			if(str.equals("")||str==null){
				req.getRequestDispatcher("returnlist.jsp").forward(req, resp);
			}
			else{
				System.out.println(str);
				VoteDao voteDao = new VoteDao();
				List<vote> voteInfors = new ArrayList<vote>();
				voteInfors = voteDao.votesearch(str);
				req.setAttribute("voteInfors", voteInfors);
				//resp.sendRedirect("search.jsp");
				req.getRequestDispatcher("search.jsp").forward(req, resp);
				
			}
		}
		//进入维护界面
		else if(flag.equals("maintenance")){
			int vid = Integer.parseInt(req.getParameter("vid"));
			int gopage = Integer.parseInt(req.getParameter("gopage"));
			voteInfor = voteDao.getInfor(vid);
			List<VoteDetail> voteDetails = new ArrayList<VoteDetail>();
			voteDetails = voteDao.showVoteDetail(vid);
			req.setAttribute("voteInfor", voteInfor);
			req.setAttribute("voteDetails",voteDetails);
			req.setAttribute("gopage",gopage);
			req.getRequestDispatcher("maintenancePage.jsp").forward(req, resp);
			
		}
		//删除投票主题
		else if(flag.equals("maintenanceDelet"))
		{
			int vid = Integer.parseInt(req.getParameter("vid"));
			
			voteDao.removeOption(vid);
			voteDao.removeVote(vid);
			PageUtil pageUtil = new PageUtil();
			int count = voteDao.voteCount();
			pageUtil.setRowPageData(gopage, count);
			int totalpage = pageUtil.getPageNum();
			Map map = new HashMap<String,Object>();
			map.put("pageUtil", pageUtil);
			List<vote>voteInfors = voteDao.showVoteList(map);
			HttpSession session = req.getSession();
			session.setAttribute("nowpage", pageUtil.getGoPage());
			session.setAttribute("totalpage",totalpage);
			session.setAttribute("voInfors",voteInfors);
			req.getRequestDispatcher("maintenance.jsp").forward(req,resp);
			
		}
		//添加维护信息
		else if(flag.equals("updatesubmit")){
			int vid = Integer.parseInt(req.getParameter("vid"));
			String votetype = req.getParameter("votetype");
			String vname = req.getParameter("vname");
			String []a = req.getParameterValues("voteoption");
			
			
			voteDao.removeOption(vid);
			Boolean b1 = voteDao.addOption(vid, a);
			Boolean b2 = voteDao.voteMofity(vid, vname, votetype);
			if(b1&&b2)
			{
				PageUtil pageUtil = new PageUtil();
				int count = voteDao.voteCount();
				pageUtil.setRowPageData(gopage, count);
				int totalpage = pageUtil.getPageNum();
				Map map = new HashMap<String,Object>();
				map.put("pageUtil", pageUtil);
				List<vote>voteInfors = voteDao.showVoteList(map);
				HttpSession session = req.getSession();
				session.setAttribute("nowpage", pageUtil.getGoPage());
				session.setAttribute("totalpage",totalpage);
				session.setAttribute("voInfors",voteInfors);
				req.getRequestDispatcher("maintenance.jsp").forward(req,resp);
			}
			
			
		}
		
	}
}
