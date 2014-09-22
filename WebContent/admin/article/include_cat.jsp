<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="bar">
  <label>选择分类</label>
  <a href="javascript:void(0);" onclick="addCat();" class="fa fa-plus-square"></a>
</div>
<ul class="cats">
  <c:forEach items="${cats}" var="o" varStatus="index">
  <li>
    <input type="checkbox" name="cats" value="${o.id}" />
    <a href="javascript:void(0);" onclick="modifyCat(this,'${o.id}');" data-cat-type="${o.type}" data-cat-name="${o.name}">${o.name}
    <c:if test="${0 eq o.type}">(隐藏)</c:if></a></li>
  </c:forEach>
</ul>
<nlft:tpl id="add_cat">
<div id="addForm">
  <ul>
    <li data-width="30">分类名称：</li>
    <li><input id="catName" type="text" /></li>
  </ul>
  <ul>
    <li data-width="30">分类类型：</li>
    <li><select id="catType"><option value="1">显示</option><option value="0">隐藏</option></select></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="30"><a id="btnAdd">添加</a></li>
  </ul>
</div>
</nlft:tpl>
<nlft:tpl id="modify_cat">
<div id="modForm">
  <input id="catId" type="hidden" value="{$=data.catId$}" />
  <ul>
    <li data-width="30">分类名称：</li>
    <li><input id="catName" type="text" value="{$=data.catName$}" /></li>
  </ul>
  <ul>
    <li data-width="30">分类类型：</li>
    <li><select id="catType"><option value="1">显示</option><option value="0">隐藏</option></select></li>
  </ul>
  <ul>
    <li data-width="30">URL：</li>
    <li><div>action-Cat/page?id={$=data.catId$}</div></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="30"><a id="btnDel">删除</a></li>
    <li data-width="5"></li>
    <li data-width="30"><a id="btnMod">修改</a></li>
  </ul>
</div>
</nlft:tpl>
<script type="text/javascript">
function addCat(){
  I.want(function(){
    var win = I.z.Win.create({
      title:'添加分类',
      width:400,
      height:140,
      content:I.util.Template.render(null,I.$('TPL_add_cat').value)
    });
    I.ui.Form.render('addForm',{
      border:'0',
      border_hover:'0'
    });
    I.ui.Button.render('btnAdd',{
      callback:function(){
        I.net.Rmi.set('name',I.$('catName').value);
        I.net.Rmi.set('type',I.$('catType').value);
        I.net.Rmi.call('admin-Cat', 'add', function(r) {
          win.close();
          var cats = I.$('class','cats')[0];
          var li = I.insert('li',cats);
          li.innerHTML = '<input type="checkbox" name="cats" value="'+r.id+'" /><a href="javascript:void(0);" onclick="modifyCat(this,\''+r.id+'\');">'+r.name+'</a>';
        });
      }
    });
  });
}
function modifyCat(obj,id){
  I.want(function(){
    var win = I.z.Win.create({
      title:'修改分类',
      width:400,
      height:180,
      content:I.util.Template.render({catId:id,catName:obj.getAttribute('data-cat-name')},I.$('TPL_modify_cat').value)
    });
    I.$('catType').value = obj.getAttribute('data-cat-type');
    I.ui.Form.render('modForm',{
      border:'0',
      border_hover:'0'
    });
    I.ui.Button.render('btnMod',{
      callback:function(){
        I.net.Rmi.set('id',I.$('catId').value);
        I.net.Rmi.set('name',I.$('catName').value);
        I.net.Rmi.set('type',I.$('catType').value);
        I.net.Rmi.call('admin-Cat', 'modify', function(r) {
          win.close();
          obj.innerHTML = r.name;
        });
      }
    });
    I.ui.Button.render('btnDel',{
      callback:function(){
        I.net.Rmi.set('id',I.$('catId').value);
        I.net.Rmi.call('admin-Cat', 'delete', function(r) {
          win.close();
          var op = obj.parentNode;
          op.parentNode.removeChild(op);
        });
      }
    });
  });
}
</script>