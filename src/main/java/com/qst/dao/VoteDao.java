package com.qst.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.qst.entiy.PageUtil;
import com.qst.entiy.VoteDetail;
import com.qst.entiy.vote;








public class VoteDao {
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
//添加投票主题
	public boolean addVote(String vname,String vtype){
		boolean b=false;
		String sql = "insert into tb_vote(vname,vtype) value ('"+vname+"','"+vtype+"');";
		conn = Basedao.getconn();
		try {
			pst=  (PreparedStatement) conn.prepareStatement(sql);
			int i = pst.executeUpdate();
			if(i>0)
				b=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeall(rs, pst, conn);
		}
		return b;
		
	}
	
	//添加投票选项
		public boolean addOption(int vid,String []optnames){
			boolean b=false;
			String sql;
			int i = 0;
			conn =  Basedao.getconn();
			try {
				for(int j=0;j<optnames.length;j++){
					sql = "insert into tb_option(vid,oname,number) value ('"+vid+"','"+optnames[j]+"',"+0+");";
					pst = (PreparedStatement) conn.prepareStatement(sql);
					i += pst.executeUpdate();
				}
				if (i==optnames.length) {
					b=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Basedao.closeall(rs, pst, conn);
			}
			return b;
		}
		
		//获取刚添加投票的id
		public int getInsertId(){
			int id = 0;
			String sql="SELECT max(vid) from tb_vote;";
			conn = Basedao.getconn();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()){
					id=rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Basedao.closeall(rs, pst, conn);
			}
			return id;
		}
		//根据投票ID获取每个选项
		public List<VoteDetail> showVoteDetail(int voteId){
			String sql="select * from tb_option where vid=?";
			conn = Basedao.getconn();
			List<VoteDetail> voteDetails=new ArrayList<VoteDetail>();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
	            pst.setInt(1,voteId);
				rs = pst.executeQuery();
				while(rs.next()){
					VoteDetail detail=new VoteDetail(rs.getString("oname"),rs.getInt("number"),rs.getInt("vid"));
					
					voteDetails.add(detail);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Basedao.closeall(rs, pst, conn);
			}
			return voteDetails;
		}
		
		
		//根据投票ID获取投票信息
		public vote getInfor(int vid){
			vote voteInfor=new vote();
			String sql="select * from tb_vote where vid=?";
			conn = Basedao.getconn();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
	            pst.setInt(1,vid);
				rs = pst.executeQuery();
				while(rs.next()){
					voteInfor.setVoteId(rs.getInt("vid"));
					voteInfor.setVoteName(rs.getString("vname"));
					voteInfor.setVoteType(rs.getString("vtype"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Basedao.closeall(rs, pst, conn);
			}
			return voteInfor;
		}
		
		//投票主题数量
		public int voteCount() {
			int count = 0;
			String sql = "select count(vid) c from tb_vote";
			conn = Basedao.getconn();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				rs = pst.executeQuery();
				if (rs.next()) {
					count= (rs.getInt("c")); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Basedao.closeall(rs, pst, conn);
			}
			return count;
		}
		
		
		//分页查询数据
		public List<vote>showVoteList(Map map){
			String sql = "select * from tb_vote LIMIT ?,?";
			conn = Basedao.getconn();
			List<vote>voInfors = new ArrayList<vote>();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setInt(1,((PageUtil)map.get("pageUtil")).getMinNum()-1);
				pst.setInt(2,PageUtil.ROWNUM);
				rs = pst.executeQuery();
				while(rs.next()){
					vote voteInfor = new vote(rs.getInt("vid"),rs.getString("vname"),rs.getString("vtype"));
				
					voInfors.add(voteInfor);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return voInfors;
		}
		//投票数据增加
		public boolean vote(String arr[]){
			Boolean a =false;
			int j =0;
			String sql = "update tb_option set number=number+1 where oname=?";
			conn = Basedao.getconn();
			try {
				for(int i =0;i<arr.length;i++)
				{
					pst = (PreparedStatement) conn.prepareStatement(sql);
					pst.setString(1, arr[i]);
					j+=pst.executeUpdate();
					System.out.println(j);
					System.out.println(arr.length);
				}
				if(j==arr.length)
				{	a = true;}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Basedao.closeall(rs, pst, conn);
			}
			
			
			return a;
			
		}
		//查询投票
		
		public List<vote>votesearch(String str)
		{
			String sql = "select * from tb_vote where vname like?";
			String str1 = "%"+str+"%";
			conn = Basedao.getconn();
			List<vote>voteList = new ArrayList<vote>();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setString(1,str1);
				rs = pst.executeQuery();
				while(rs.next()){
					vote voteInfor = new vote();
					voteInfor.setVoteId(rs.getInt("vid"));
					voteInfor.setVoteName(rs.getString("vname"));
					voteList.add(voteInfor);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Basedao.closeall(rs, pst, conn);
			}
			return voteList;
		}
		//删除存在投票的选项   根据vid
		public void removeOption(int vid){
			String sql = "delete from tb_option where vid=?";
			conn = Basedao.getconn();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setInt(1, vid);
				int i = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Basedao.closeall(rs, pst, conn);
			}
			
			
		}
		//删除投票主题 根据vid
		public void removeVote(int vid)
		{
			String sql = "delete from tb_vote where vid=?";
			conn= Basedao.getconn();
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setInt(1, vid);
				int i = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Basedao.closeall(rs, pst, conn);
			}
		}
		//根据vid 修改投票的信息
		
		public boolean voteMofity(int vid,String vname,String vtype){
		
			String sql = "update tb_vote set vname=?,vtype=? where vid=?";
			conn = Basedao.getconn();
			int i = 0;
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setString(1, vname);
				pst.setString(2, vtype);
				pst.setInt(3, vid);
				 i = pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Basedao.closeall(rs, pst, conn);
			}
			
			if(i>0)
				return true;
			else return false;
			
		}
		
		
		
		
		
}
