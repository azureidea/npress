<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="${WEB_KEY}" />
<meta name="description" content="${WEB_DESC}" />
<meta name="author" content="${WEB_AUTHOR}" />
<meta name="copyright" content="${WEB_COPYRIGHT}" />
<meta name="MobileOptimized" content="980" />
<meta name="Iphone-content" content="980" />
<meta name="viewport" content="width=980" />
<meta name="format-detection" content="telephone=no,address=no,email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<link type="text/css" rel="stylesheet" href="${PATH}/themes/${theme}/css/style.css" />
<script type="text/javascript" src="${PATH}/js/icore.js"></script>
<script type="text/javascript">
function userLogin(){
  I.run(function(){
    I.net.Rmi.set('account',I.$('account').value);
    I.net.Rmi.set('password',I.$('password').value);
    I.net.Rmi.call('action-Login','login',function(r){
      if('1'==r){
        self.location = '${PATH}/admin-Main/page';
      }else{
        self.location = '${PATH}/';
      }
    });
  });
}
function userLogout(){
  I.run(function(){
    I.net.Rmi.call('action-Login','logout',function(r){
      self.location = '${PATH}/';
    });
  });
}
</script>