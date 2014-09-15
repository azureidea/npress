<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include.jsp" />
<title>登录 - ${WEB_NAME}</title>
<style type="text/css">
body{
  font-size:12px;
}
</style>
</head>
<body>
<nlft:tpl id="login">
<div id="loginForm">
  <ul>
    <li data-width="20">用户名：</li>
    <li><input id="account" type="text" /></li>
  </ul>
  <ul>
    <li data-width="20">密码：</li>
    <li><input id="password" type="password" /></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="25"><a id="btn">登录</a></li>
  </ul>
</div>
</nlft:tpl>
<script type="text/javascript">
I.want(function(){
  var win = I.z.Win.create({
    title:'登录',
    width:400,
    height:200,
    content:I.util.Template.render(null,I.$('TPL_login').value),
    callback:function(){
      self.location = '${PATH}/';
    }
  });
  I.ui.Form.render('loginForm',{
    border:'0',
    border_hover:'0',
    background_hover:'#FFF'
  });
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('account',I.$('account').value);
      I.net.Rmi.set('password',I.$('password').value);
      I.net.Rmi.call('action-Login','login',function(r){
        if('1'==r){
          self.location = '${PATH}/admin-Main/page';
        }else{
          self.location = '${PATH}/';
        }
      });
    }
  });
});
</script>
</body>
</html>