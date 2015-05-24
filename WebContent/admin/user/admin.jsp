<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table class="list">
    <thead>
        <tr>
            <th class="sn">序号</th>
            <th>用户名</th>
            <th>姓名</th>
            <th class="sn">修改</th>
            <th class="sn">删除</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="5"><nlft:page /></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty nlfPagingData.data}">
        <tr>
            <td colspan="5">暂无管理员</td>
        </tr>
    </c:if>
    <c:forEach items="${nlfPagingData.data}" var="o" varStatus="index">
        <tr>
            <td>${index.count}</td>
            <td>${o.account}</td>
            <td>${o.name}</td>
            <td><a href="javascript:void(0);" onclick="mod('${o.id}');">修改</a></td>
            <td><a href="javascript:void(0);" onclick="del('${o.id}');">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nlft:tpl id="form">
<div id="form">
  <ul>
    <li data-width="20">用户名：</li>
    <li><input id="account" type="text" value="{$=data.account$}" /></li>
  </ul>
  <ul>
    <li data-width="20">姓名：</li>
    <li><input id="name" type="text" value="{$=data.name$}" /></li>
  </ul>
</div>
</nlft:tpl>
<script>
function del(id){
  I.want(function(){
    I.z.Confirm.create({
      content:'您确定要删除吗？',
      width:300,
      height:120,
      mask_opacity:30,
      mask_color:'#000',
      mask_close:false,
      yes:function(){
        var inst = this;
        I.net.Rmi.set('id',id);
        I.net.Rmi.call('admin-User','delete',function(r){
          inst.close();
          dashboard.find('admin-User/pageAdmin');
        });
      }
    });
  });
}
function mod(id){
  I.want(function(){
    I.net.Rmi.set('id',id);
    I.net.Rmi.call('admin-User','detail',function(r){
      var win = I.z.Confirm.create({
        title:'修改',
        width:400,
        height:160,
        mask_opacity:30,
        mask_color:'#000',
        mask_close:false,
        yes_button_label:'确定',
        yes_button_background:'#5bc0de',
        yes_button_border:'1px solid #46b8da',
        yes_button_color:'#FFF',
        yes_button_background_hover:'#31b0d5',
        yes_button_border_hover:'1px solid #269abc',
        yes_button_color_hover:'#FFF',
        content:I.util.Template.render(r,I.$('TPL_form').value),
        yes:function(){
          var inst = this;
          I.net.Rmi.set('id',id);
          I.net.Rmi.set('account',I.$('account').value);
          I.net.Rmi.set('name',I.$('name').value);
          I.net.Rmi.call('admin-User','modify',function(r){
            inst.close();
            dashboard.find('admin-User/pageAdmin');
          });
        }
      });
      I.ui.Form.render('form');
    });
  });
}
</script>