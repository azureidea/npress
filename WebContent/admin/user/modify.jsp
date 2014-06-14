<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="bar"><label>修改用户</label><a class="fa fa-unlock-alt" href="javascript:void(0);" onclick="modifyPwd();">修改密码</a></div>
<div id="form" class="form"></div>
<nlft:jsbegin>
I.run(function(){
  var form = I.awt.Form.create(I.$('form'));
  form.add({
    id : 'id',
    type:'hidden',
    value:'${id}'
  });
  form.add({
    id : 'account',
    label : '用户名',
    required : true
  });
  form.add({
    id : 'name',
    label : '姓名',
    required : true
  });
  form.add({
    type : 'line'
  });
  form.add({
    id : 'btn',
    type : 'button',
    label : '修改'
  });
  form.get('btn').onclick(function(m, e) {
    form.post('admin-User', 'modify', function(r) {
      find('admin-User/pageAdmin');
      hidePanel();
    });
  });
  form.get('account').setValue(I.$('account').value);
  form.get('name').setValue(I.$('name').value);
});

function modifyPwd(){
  I.run(function(){
    var win = I.z.Win.create({'title':'修改密码','width':400,'height':280});
    var form = I.awt.Form.create(win.getContentPanel());
    form.add({'id':'id','type':'hidden',value:'${u.id}'});
    form.add({'id':'opd','type':'password','label':'原密码','required':true});
    form.add({'id':'npd','type':'password','label':'新密码','required':true});
    form.add({'id':'npd1','type':'password','label':'新密码','required':true});
    form.add({'type':'line'});
    form.add({'id':'btn','type':'button','label':'修改'});
    form.get('btn').onclick(function(m,e){
      form.post('admin-User','modifyPwd',function(r){
        win.close();
      });
    });
  });
}
</nlft:jsbegin>
<textarea id="account" class="hide">${u.account}</textarea>
<textarea id="name" class="hide">${u.name}</textarea>