<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="admin_left">
  <div class="form"></div>
</div>
<div class="admin_right">
  <div class="form">
        <div class="body">
          <div class="bar"><label>选择分类</label><a href="javascript:void(0);" onclick="addCat();" class="fa fa-plus-square"></a></div>
          <input type="hidden" id="hid_cat" />
          <ul class="cats">
          <c:forEach items="${cats}" var="o" varStatus="index">
          <li><input type="checkbox" name="cats" value="${o.id}" /><a href="javascript:void(0);" onclick="modifyCat(this,'${o.id}');">${o.name}</a></li>
          </c:forEach>
          </ul>
        </div>
  </div>
</div>
<nlft:jsbegin>
function addCat(){
  I.run(function(){
    var win = I.z.Win.create({'title':'添加分类','width':400,'height':180});
    var form = I.awt.Form.create(win.getContentPanel());
    form.add({id:'name',label:'分类名',required:true});
    form.add({type:'line'});
    form.add({id:'btn',type:'button',label:'确定'});
    form.get('btn').onclick(function(m,e){
      form.post('admin-Cat','add',function(r){
        win.close();
        var cats = I.$('class','cats')[0];
        var li = I.insert('li',cats);
        li.innerHTML = '<input type="checkbox" name="cats" value="'+r.id+'" /><a href="javascript:void(0);" onclick="modifyCat(this,\''+r.id+'\');">'+r.name+'</a>';
      });
    });
  });
}
function modifyCat(obj,id){
  I.run(function(){
    var win = I.z.Win.create({'title':'修改分类','width':400,'height':180});
    var form = I.awt.Form.create(win.getContentPanel());
    form.add({id:'id',type:'hidden',value:id});
    form.add({id:'name',label:'分类名',required:true,value:obj.innerHTML});
    form.add({type:'line'});
    form.add({id:'btnMod',type:'button',label:'修改'});
    form.add({id:'btnDel',type:'button',label:'删除'});
    form.get('btnMod').onclick(function(m,e){
      form.post('admin-Cat','modify',function(r){
        win.close();
        obj.innerHTML = r.name;
      });
    });
    form.get('btnDel').onclick(function(m,e){
      form.post('admin-Cat','delete',function(r){
        win.close();
        var op = obj.parentNode;
        op.parentNode.removeChild(op);
      });
    });
  });
}
I.run(function(){
  var form = I.awt.Form.create(I.$('class','form')[0]);
  form.add({
    id : 'title',
    label : '标题',
    required : true
  });
  form.add({
    id : 'desc',
    type : 'textarea',
    label : '简介'
  });
  form.add({
    id : 'content',
    type : 'textarea',
    label : '内容'
  });
  form.add({
    type : 'line'
  });
  form.add({
    id : 'btn',
    type : 'button',
    label : '发布'
  });
  form.get('btn').onclick(function(m, e) {
    var l = I.$('name','cats');
    var ps = [];
    for(var i=0;i<l.length;i++){
      if(l[i].checked){
        ps.push(l[i].value);
      }
    }
    I.$('hid_cat').value = ps.join(',');
    form.bind({'cats':'hid_cat'});
    form.post('admin-Article', 'add', function(r) {
    });
  });
  form.get('title').getInstance().layer.style.width = '300px';
});
</nlft:jsbegin>