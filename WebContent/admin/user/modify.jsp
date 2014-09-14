<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<div class="bar">
  <label>修改用户</label>
  <a class="fa fa-unlock-alt" href="javascript:void(0);" onclick="modifyPwd();">修改密码</a>
</div>
<div class="form">
<div id="form">
  <ul>
    <li data-width="15">用户名：</li>
    <li><input id="account" type="text" value="${u.account}" /></li>
  </ul>
  <ul>
    <li data-width="15">姓名：</li>
    <li><input id="name" type="text" value="${u.name}" /></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="20"><a id="btn">修改</a></li>
  </ul>
</div>
</div>
<nlft:tpl id="modify_pwd">
<div id="mpForm">
  <ul>
    <li data-width="30">原密码：</li>
    <li><input id="opd" type="password" /></li>
  </ul>
  <ul>
    <li data-width="30">新密码：</li>
    <li><input id="npd" type="password" /></li>
  </ul>
  <ul>
    <li data-width="30">新密码：</li>
    <li><input id="npd1" type="password" /></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="30"><a id="btnMod">修改</a></li>
  </ul>
</div>
</nlft:tpl>

<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('id','${id}');
      I.net.Rmi.set('account',I.$('account').value);
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.call('admin-User', 'modify', function(r) {
        I.net.Page.find('admin-User/pageAdmin');
        hidePanel();
      });
    }
  });
});

function modifyPwd(){
  I.want(function(){
    var win = I.z.Win.create({
      title:'修改密码',
      width:400,
      height:200,
      content:I.util.Template.render(null,I.$('TPL_modify_pwd').value)
    });
    I.ui.Form.render('mpForm',{
      border:'0',
      border_hover:'0'
    });
    I.ui.Button.render('btnMod',{
      callback:function(){
        I.net.Rmi.set('id','${id}');
        I.net.Rmi.set('opd',I.$('opd').value);
        I.net.Rmi.set('npd',I.$('npd').value);
        I.net.Rmi.set('npd1',I.$('npd1').value);
        I.net.Rmi.call('admin-User', 'modifyPwd', function(r) {
          win.close();
        });
      }
    });
  });
}
</script>