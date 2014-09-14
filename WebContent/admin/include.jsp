<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<link type="text/css" rel="stylesheet" href="${PATH}/css/admin.css" />
<link type="text/css" rel="stylesheet" href="${PATH}/css/font-awesome.css" />
<script type="text/javascript" src="${PATH}/js/icore.js"></script>
<script type="text/javascript">
function userLogin(){
  I.want(function(){
    I.net.Rmi.from('account','password');
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
  I.want(function(){
    I.net.Rmi.call('action-Login','logout',function(r){
      self.location = '${PATH}/';
    });
  });
}
</script>