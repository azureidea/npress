<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include.jsp" />
<title>登录 - ${WEB_NAME}</title>
<style type="text/css">
html{
  font:normal 14px/1.5 "Hiragino Sans GB","Microsoft YaHei", "hei", Arial, "Lucida Grande", Verdana;
}
</style>
</head>
<body>
<nlft:tpl id="login">
<div id="loginForm">
  <ul>
    <li data-width="23">用户名：</li>
    <li><input id="account" type="text" /></li>
  </ul>
  <ul>
    <li data-width="23">密码：</li>
    <li><input id="password" type="password" /></li>
  </ul>
</div>
</nlft:tpl>
<script type="text/javascript">
I.want(function(){
  var win = I.z.Confirm.create({
    title:'用户登录',
    width:300,
    height:120,
    mask_opacity:50,
    mask_color:'#000',
    mask_close:false,
    yes_button_icon:'fa fa-lock',
    yes_button_label:'&nbsp;登录',
    yes_button_background:'#5bc0de',
    yes_button_border:'1px solid #46b8da',
    yes_button_color:'#FFF',
    yes_button_background_hover:'#31b0d5',
    yes_button_border_hover:'1px solid #269abc',
    yes_button_color_hover:'#FFF',
    content:I.util.Template.render(null,I.$('TPL_login').value),
    no:function(){
      self.location = '${PATH}/';
    },
    yes:function(){
      login();
    }
  });
  I.ui.Form.render('loginForm');
  function login(){
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
  I.listen(I.$('password'),'keyup',function(m,e){
    if(13==(e.keyCode|e.which)){
      login();
    }
  });
});
</script>
</body>
</html>