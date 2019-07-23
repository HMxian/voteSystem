<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="static/css/admin.css">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="static/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="static/css/global_style.css">
</head>
<body>
<div class="tm-tpl tpl-white-hn small-item" style-name="tpl-white-hn">
		<!--导航栏-->
		<div class="tpl-left-sidebar">
			<ul>
				<li class="logo">
					<img class="user-upload" src="static/images/4a251abe82900c79733daa753664f701.jpg" alt="admin" title="admin">
					<span>LeoAdmin</span>
				</li>
				<!--<li class="nav-item active">
				<a href="index.html" class="a-item"><i class="fa fa-home nav-icon" aria-hidden="true"></i><span>首页</span></a>
				</li>
				 -->
				<li class="nav-item">
					<a href="index.html" class="a-item"><i class="fa fa-home nav-icon"></i><span>首页</span></a>
				</li>
				<li class="nav-item">
					<a href="auth_index.html" class="a-item"><i class="fa fa-users nav-icon"></i><span>控制台</span></a>
					<ul class="nav-child-item has-child">
						<div class="nav-child-list">
						<c:choose>
						<c:when test="${user.VU_STATUS==2}">
						<li><a href="auth_index.jsp"><i class="fa fa-user-secret" aria-hidden="true"></i>投票列表</a></li>
							<li><a href="addvote.jsp"><i class="fa fa-users" aria-hidden="true"></i>新增投票</a></li>
							<li><a href="maintenance.jsp"><i class="fa fa-user" aria-hidden="true"></i>投票维护</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="auth_index.jsp"><i class="fa fa-user-secret" aria-hidden="true"></i>投票列表</a></li>
						</c:otherwise>
						</c:choose>
						</div>
					</ul>
				</li>
				<li class="nav-item">
					<a href="" class="a-item"><i class="fa fa-fire nav-icon"></i><span>测试中心</span></a>
					<ul class="nav-child-item has-child">
						<div class="nav-child-list">
							<li><a href=""><i class="fa fa-dashboard " aria-hidden="true"></i>视图1</a></li>
						</div>
					</ul>
				</li>
				<div class="sidebar-footer">
					<ul class="clearfix">
						<a href="javascript:;" data-title="消息"><i class="fa fa-bell-o"></i><span class="badge">99+</span></a>
						<a href="javascript:;" class="dashboard" tag="style-bar" data-title="配色方案"> <i class="fa fa-dashboard "></i></a>
						<a href="javascript:;" class="user" tag="user-bar" data-title="个人中心"><i class="fa fa-user"></i></a>
						<a href="javascript:;" data-title="公众号"><i class="fa fa-wechat"></i></a>
					</ul>
				</div>
			</ul>
		</div>
		<!--右侧内容-->
		<div class="tpl-right-item">
			<div class="tpl-body">
				<!--头部-->
				<div class="tpl-header">
					<div class="tpl-header-fluid">
						<div class="tpl-button switch-list">
							<i class="fa fa-hand-o-right" ></i>
						</div>
						<div class="tpl-button refresh" title="刷新">
							<i class="fa fa-refresh"></i>
						</div>
						<div class="tpl-button text">
							<a href=""><i class="fa fa-users"></i> 权限控制台</a>
						</div>
						<div class="tpl-userbar">
							<ul>
								<li><a href="javascript:;" class="dashboard" tag="style-bar"><i class="fa fa-dashboard"></i></a></li>
								<li><a href="">欢迎你${user.VU_USER_NAME}</a></li>
								<li><a href="javascript:;" class="logout"><i class="fa fa-power-off"></i>退出登录</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!--内容-->
				<div class="tpl-content">
					<div class="tpl-pannel">
						<div class="tpl-h-title">
							<a href="" class="layui-btn layui-btn-sm layui-btn-normal">搜索列表</a>
						</div>
							<div style="float:right;margin-right:10px;">
			
			</div>
						<table class="layui-table" lay-even="" lay-skin="row">
							<colgroup>
								<col width="33%">
								<col width="33%">
								<col width="120">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>权限名称</th>
									<th>权限路径</th>
									<th>状态</th>
									<th>操作</th>
								</tr> 
							</thead>
							<tbody>
								<c:forEach items="${voteInfors}" var="v">
								<tr>
									<td><a class="returnlist-a"href="VoteServlet?flag=checkvote&vid=${v.voteId}&voteType=${v.voteType}">${v.voteName}</a></td>
									<td>index/index</td>
									<td>启用</td>
									<td class="item-a" data-id="1"><a href="VoteServlet?flag=join&vid=${v.voteId}&voteName=${v.voteName}&voteType=${v.voteType}">参与投票</a></td>
								</tr>
							</c:forEach>
							
	
								</tbody>
								
						</table>
		
					</div>
				</div>
				<!--配色方案-->
				<div class="right-bar style-bar">
					<div class="right-bar-fluid">
						<div class="tpl-header-text">配色方案</div>
						<ul class="style-item clearfix" id="style-list">
							<li data-style="tpl-black-n">
								<div class="header-style"></div>
								<div class="left-style black">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-black-hn">
								<div class="header-style black-child"></div>
								<div class="left-style black">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-red-hn">
								<div class="header-style red-child"></div>
								<div class="left-style red">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-red-n">
								<div class="header-style"></div>
								<div class="left-style red">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-blackred-hn">
								<div class="header-style red"></div>
								<div class="left-style black">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-blackgreen-hn">
								<div class="header-style green"></div>
								<div class="left-style black">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-green-hn">
								<div class="header-style green-child"></div>
								<div class="left-style green">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-green-n">
								<div class="header-style"></div>
								<div class="left-style green">
									<div class="logo-style"></div>
								</div>
							</li>
							<li data-style="tpl-white-hn">
								<div class="header-style"></div>
								<div class="left-style white">
									<div class="logo-style"></div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!--用户信息-->
				<div class="right-bar user-bar">
					<div class="right-bar-fluid">
						<div class="tpl-card">
							<div class="card-pannel">
								<div class="user-item">
									<img class="user-upload" src="static/images/4a251abe82900c79733daa753664f701.jpg">
									<h4 class="user-name">admin</h4>
									<p>时间模糊了很多东西，我依然爱你</p>
									<div class="user-tips">
										<a href="javascript:;" data-tips="13138003682"><i class="fa fa-phone"></i></a>
										<a href="javascript:;" data-tips="you can goin!"><i class="fa fa-star"></i></a>
										<a href="javascript:;" data-tips="547720744"><i class="fa fa-qq"></i></a>
										<a href="javascript:;" data-tips="Mr.LIU"><i class="fa fa-wechat"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--尾部-->
				<div class="tpl-footer">
					<div class="tpl-footer-fluid">
						<a class="f-item-text">来自一位后台工作者的零星之火</a>
						<a class="f-item-text">LeoAdmin</a>
						<a class="fitem-texy">2018 ©</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="static/layui/layui.js"></script>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="static/js/admin.js"></script>
</body>
</html>