<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<table class="list">
    <thead>
        <tr>
            <th class="sn">序号</th>
            <th>网站名称</th>
            <th>URL</th>
            <th class="sn">修改</th>
            <th class="sn">删除</th>
        </tr>
    </thead>
    <tbody>
    <c:if test="${empty links}">
        <tr>
            <td colspan="5">暂无友情链接</td>
        </tr>
    </c:if>
    <c:forEach items="${links}" var="o">
        <tr>
            <td>${o.index}</td>
            <td>${o.name}</td>
            <td>
              <a href="${o.url}" target="_blank">${o.url}</a>
            </td>
            <td><a href="javascript:void(0);" onclick="mod('${o.id}');">修改</a></td>
            <td><a href="javascript:void(0);" onclick="del('${o.id}');">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nlft:tpl id="form">
<div id="form">
  <ul>
    <li data-width="20">网站名称：</li>
    <li><input id="name" type="text" value="{$=data.name$}" /></li>
    <li data-width="30"></li>
  </ul>
  <ul>
    <li data-width="20">序号：</li>
    <li><input id="index" type="text" value="{$=data.index$}" /></li>
    <li data-width="30"><div class="remark">输入数字</div></li>
  </ul>
  <ul>
    <li data-width="20">URL：</li>
    <li><input id="url" type="text" value="{$=data.url$}" /></li>
    <li data-width="30"></li>
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
        I.net.Rmi.call('admin-Link','delete',function(r){
          inst.close();
          dashboard.find('admin-Link/pageList');
        });
      }
    });
  });
}
function mod(id){
  I.want(function(){
    I.net.Rmi.set('id',id);
    I.net.Rmi.call('admin-Link','detail',function(r){
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
          I.net.Rmi.set('name',I.$('name').value);
          I.net.Rmi.set('index',I.$('index').value);
          I.net.Rmi.set('url',I.$('url').value);
          I.net.Rmi.call('admin-Link','modify',function(r){
            inst.close();
            dashboard.find('admin-Link/pageList');
          });
        }
      });
      I.ui.Form.render('form');
    });
  });
}
</script>