<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/admin/include.jsp" />
<title>控制台 - ${WEB_NAME}</title>
</head>
<body>
<script>
var dashboard;
I.want(function(){
  dashboard = I.ui.Dashboard.create({
    title_bar_height:0,
    foot_height:0,
    footer_height:0,
    tool_bar_height:60
  });
  dashboard.setMenu([
    {text:'文章管理',icon:'fa fa-file-text',children:[{text:'写文章',icon:'fa fa-plus',link:'admin-Article/pageAdd'},{text:'文章列表',icon:'fa fa-bars',link:'admin-Article/pageList'}]},
    {text:'模块管理',icon:'fa fa-plug',children:[{text:'新增模块',icon:'fa fa-plus',link:'admin-Module/pageAdd'},{text:'模块列表',icon:'fa fa-bars',link:'admin-Module/pageList'}]},
    {text:'用户管理',icon:'fa fa-user',children:[{text:'管理员',icon:'fa fa-user-secret',link:'admin-User/pageAdmin'}]},
    {text:'友情链接',icon:'fa fa-link',children:[{text:'添加网站',icon:'fa fa-plus',link:'admin-Link/pageAdd'},{text:'网站列表',icon:'fa fa-bars',link:'admin-Link/pageList'}]},
    {text:'系统设置',icon:'fa fa-cog',children:[{text:'网站配置',icon:'fa fa-cog',link:'admin-Config/page'},{text:'缓存管理',icon:'fa fa-save',link:'admin-Cache/page'}]}
  ]);
  
  var div = I.insert('div','before',dashboard.tools[0]);
  I.util.Boost.addStyle(div,'margin-left:20px;margin-right:20px;float:left;line-height:'+dashboard.config.tool_bar_height+'px;color:#FFF;font-size:18px');
  div.innerHTML='控制台';
  
  dashboard.addItem(dashboard.toolBar,[
    {type:'head',content:'${PATH}/img/head.png'},
    {type:'text',content:'${user.name}'},
    {type:'text',content:'注销'}
  ],function(index){
    switch(index){
      case 2:
        I.net.Rmi.call('action-Login','logout',function(r){
          self.location = '${PATH}/action-Login/page';
        });
      break;
    }
  });
  var items = I.$(dashboard.toolBar,'tag','li');
  I.util.Boost.addStyle(items[items.length-1],'cursor:pointer');
  
  dashboard.addBread(dashboard.head);

  //内容区
  I.util.Boost.addStyle(dashboard.content,'padding:20px;');
  
  dashboard.find('admin-Main/home');
});
</script>
</body>
</html>