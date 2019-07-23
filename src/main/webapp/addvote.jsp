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
				
				</li>
				
				<li class="nav-item">
					<a href="index.html" class="a-item"><i class="fa fa-home nav-icon"></i><span>首页</span></a>
				</li>
				<li class="nav-item">
					<a href="auth_index.html" class="a-item"><i class="fa fa-users nav-icon"></i><span>权限控制台</span></a>
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
							<a href=""><i class="fa fa-"></i> </a>
						</div>
						<div class="tpl-userbar">
							<ul>
								<li><a href="javascript:;" class="dashboard" tag="style-bar"><i class="fa fa-dashboard"></i></a></li>
								<li><a href="">欢迎你:${user.VU_USER_NAME}</a></li>
								<li><a href="javascript:;" class="logout"><i class="fa fa-power-off"></i>退出登录</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!--内容-->
				<div class="tpl-content">
					<div class="tpl-pannel clearfix">
						<div class="tpl-h-title">
							<a href="javascript:;" class="layui-btn layui-btn-sm back"><i class="fa fa-arrow-left"></i> 返回上一页</a>
						</div>
						<form action="VoteServlet?flag=addnew" method="post" onsubmit="return submitTest()">
				<table id="votetable" class="table_addnew">
					<tr>
						<td>投票内容:</td>
						<td><input id="vname" name="vname" type="text" style="width:300px"></td>
					</tr><tr>
						<td>投票类型:</td>
						<td><input type="radio" name="votetype" value="1"  checked="checked">单选<input type="radio" name="votetype" value="0">多选</td>
					</tr><tr>
						<td>投票选项</td>
						<td><input name='voteoption' type="text" style="width:300px"></td>
					</tr>
					<tr>
						<td></td>
						<td><input name='voteoption' type="text" style="width:300px"></td>
					</tr>
				</table>
				<div style="margin-left:118px;margin-bottom:20px;">
					<input class="submit_addnew" type="submit" value="确定">
					<a id="add" class="link_addnew">增加选项</a>
					<a href="auth_index.jsp" class="link_addnew">取消操作</a>
				</div>
			</form>
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
	<script type="text/javascript" src="static/js/admin.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(window).load(function(){
	$("#add").click(function(){
		var newtr="<tr><td></td><td><input name='voteoption' type='text' style='width:300px'><a onclick='deltr(this)'>删除</a></td></tr>";
		$("#votetable").append(newtr);
	});
});
function deltr(tr){
	$(tr).parent().parent().remove();
}
function submitTest(){
	var a=0;
	var str1=document.getElementById("vname").value;
	var values=document.getElementsByName("voteoption");
	if(str1.length==0){
		alert("投票名不能为空!");
		return false;
	}else{
		for(var j=0;j<values.length;j++){
			if((values[j].value).length==0){
				alert("选项输入不能为空!");
				return false;
			}
		}
	}
}
</script>
</body>
</html>