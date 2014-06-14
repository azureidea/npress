<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<nlft:tpl id="user_info">
<div class="comp_header">
{$
if(data){
$}用户信息{$
}else{
$}用户登录{$
}
$}
</div>
  <div class="comp_body">
  <ul>
{$
if(data){
$}
<li><b>{$=data.name$}</b>已登录，[<a href="${PATH}/admin-Main/page" onclick="userLogout();">后台管理</a>]或[<a href="javascript:void(0);" onclick="userLogout();">退出</a>]</li>
{$
}else{
$}
<li>账号：<input id="account" type="text" class="txt" style="width:230px" /></li>
<li>密码：<input id="password" type="password" class="txt" style="width:230px" /></li>
<li><a class="button putright" href="javascript:void(0);" onclick="userLogin();">登录</a></li>
{$
}
$}
  </ul>
</div>
</nlft:tpl>
<div id="comp_user" class="comp container"></div>
<script type="text/javascript">
I.run(function(){
  I.net.Rmi.call('action-Login','check',function(r){
    I.util.JTpl.rend(r,'user_info','comp_user');
  },function(){
    I.util.JTpl.rend(null,'user_info','comp_user');
  });
});
</script>