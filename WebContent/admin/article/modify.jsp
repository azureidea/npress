<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="bar">
  <label>修改文章</label>
</div>
<div class="form">
  <div class="body">
    <jsp:include page="/admin/article/include_cat.jsp" />
  </div>
</div>
<div class="form">
  <div id="form">
    <ul>
        <li data-width="15">标题：</li>
        <li><input id="title" type="text" value="${art.title}" /></li>
      </ul>
      <ul>
        <li data-width="15">图片：</li>
        <li><input id="pic" type="text" value="${art.pic}" /></li>
        <li>
          <div id="picUpload">
            <i>上传图片</i>
            <b></b>
            <form><input type="file" /></form>
          </div>
        </li>
      </ul>
      <ul>
        <li data-width="15">简介：</li>
        <li><textarea id="desc" rows="10">${art.description}</textarea></li>
      </ul>
      <ul>
        <li data-width="15">内容：</li>
        <li><textarea id="content" rows="10">${art.content}</textarea></li>
      </ul>
      <ul>
        <li></li>
        <li data-width="20"><a id="btn">确定修改</a></li>
      </ul>
  </div>
</div>

<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Upload.render('picUpload',{
    width:120,
    height:30,
    checkKlass:'nc-liat6-frame-web-upload-UploadStatus',
    url:'${PATH}/admin-File/upload',
    onSuccess:function(r){
      I.$('pic').value = r.data;
    }
  });
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('id','${id}');
      I.net.Rmi.set('title',I.$('title').value);
      I.net.Rmi.set('desc',I.$('desc').value);
      I.net.Rmi.set('content',I.$('content').value);
      I.net.Rmi.set('pic',I.$('pic').value);
      var l = I.$('name','cats');
      var ps = [];
      for(var i=0;i<l.length;i++){
        if(l[i].checked){
          ps.push(l[i].value);
        }
      }
      I.net.Rmi.set('cats',ps.join(','));
      I.net.Rmi.call('admin-Article', 'modify', function(r) {
        I.net.Page.find('admin-Article/pageList');
        hidePanel();
      });
    }
  });
  var arts = {};
  <c:forEach items="${artCats}" var="o" varStatus="index">
  arts['${o.id}'] = true;
  </c:forEach>
  var l = I.$('name','cats');
  for(var i=0;i<l.length;i++){
    if(arts[l[i].value]){
      l[i].checked = 'checked';
    }
  }
});
</script>