<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<table class="list">
    <thead>
        <tr>
            <th class="sn">序号</th>
            <th>名称</th>
            <th>位置</th>
            <th>类型</th>
            <th class="sn">修改</th>
            <th class="sn">删除</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="6"></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty modules}">
        <tr>
            <td colspan="6">暂无模块</td>
        </tr>
    </c:if>
    <c:forEach items="${modules}" var="o" varStatus="index">
        <tr>
            <td>${o.index}</td>
            <td>${o.name}</td>
            <td>
              <c:choose>
                <c:when test="${0 eq o.pos}">顶部导航</c:when>
                <c:when test="${1 eq o.pos}">底部导航</c:when>
                <c:when test="${2 eq o.pos}">文章详情底部插件</c:when>
                <c:when test="${3 eq o.pos}">右侧边栏底部插件</c:when>
                <c:when test="${4 eq o.pos}">文章详情顶部插件</c:when>
                <c:when test="${5 eq o.pos}">右侧边栏顶部插件</c:when>
              </c:choose>
            </td>
            <td>
              <c:choose>
                <c:when test="${0 eq o.type}">本窗口打开的本站URL</c:when>
                <c:when test="${1 eq o.type}">新窗口打开的本站URL</c:when>
                <c:when test="${2 eq o.type}">本窗口打开的外站URL</c:when>
                <c:when test="${3 eq o.type}">新窗口打开的外站URL</c:when>
                <c:when test="${4 eq o.type}">本窗口打开的页面</c:when>
                <c:when test="${5 eq o.type}">新窗口打开的页面</c:when>
                <c:when test="${6 eq o.type}">插件</c:when>
              </c:choose>
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
    <li data-width="10">名称：</li>
    <li><input id="name" type="text" value="{$=data.name$}" /></li>
  </ul>
  <ul>
    <li data-width="10">序号：</li>
    <li><input id="index" type="text" value="{$=data.index$}" /></li>
  </ul>
  <ul>
    <li data-width="10">位置：</li>
    <li>
      <select id="pos">
        <option value="0">顶部导航</option>
        <option value="1">底部导航</option>
        <option value="2">文章详情底部插件</option>
        <option value="3">右侧边栏底部插件</option>
        <option value="4">文章详情顶部插件</option>
        <option value="5">右侧边栏顶部插件</option>
      </select>
    </li>
  </ul>
  <ul>
    <li data-width="10">类型：</li>
    <li>
      <select id="type">
        <option value="0">本窗口打开的本站URL</option>
        <option value="1">新窗口打开的本站URL</option>
        <option value="2">本窗口打开的外站URL</option>
        <option value="3">新窗口打开的外站URL</option>
        <option value="4">本窗口打开的页面</option>
        <option value="5">新窗口打开的页面</option>
        <option value="6">插件</option>
      </select>
    </li>
  </ul>
  <ul>
    <li data-width="10">URL：</li>
    <li><input id="url" type="text" value="{$=data.url$}" /></li>
  </ul>
  <ul>
    <li data-width="10">内容：</li>
    <li>&lt;textarea id="content" rows="10"&gt;{$=data.content$}&lt;/textarea&gt;</li>
  </ul>
  <ul>
    <li data-width="10">是否首页：</li>
    <li>
      <select id="home">
        <option value="0">否</option>
        <option value="1">是</option>
      </select>
    </li>
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
        I.net.Rmi.call('admin-Module','delete',function(r){
          inst.close();
          dashboard.find('admin-Module/pageList');
        });
      }
    });
  });
}

function mod(id){
  I.want(function(){
    I.net.Rmi.set('id',id);
    I.net.Rmi.call('admin-Module','detail',function(r){
      var editor;
      var win = I.z.Confirm.create({
        title:'修改',
        width:800,
        height:480,
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
          I.net.Rmi.set('pos',I.$('pos').value);
          I.net.Rmi.set('type',I.$('type').value);
          I.net.Rmi.set('content',editor.getContent());
          I.net.Rmi.set('url',I.$('url').value);
          I.net.Rmi.set('home',I.$('home').value);
          I.net.Rmi.call('admin-Module','modify',function(r){
            inst.close();
            dashboard.find('admin-Module/pageList');
          });
        }
      });
      I.ui.Form.render('form');
      I.$('type').value = r.type;
      I.$('pos').value = r.pos;
      I.$('home').value = r.home;
      editor = I.ui.Editor.render('content',{
        checkKlass:'nc-liat6-frame-web-upload-UploadStatus',
        uploadUrl:'${PATH}/admin-File/uploadBigPic'
      });
    });
  });
}
</script>