<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="admin_left">
  <div class="form">
    <div id="form">
      <ul>
        <li data-width="10">标题：</li>
        <li><input id="title" type="text" /></li>
      </ul>
      <ul>
        <li data-width="10">图片：</li>
        <li><input id="pic" type="text" /></li>
        <li>
          <div id="picUpload">
            <i>上传图片</i>
            <b></b>
            <form><input type="file" /></form>
          </div>
        </li>
      </ul>
      <ul>
        <li data-width="10">关键词：</li>
        <li><input id="keywords" type="text" /></li>
      </ul>
      <ul>
        <li data-width="10">简介：</li>
        <li><textarea id="desc" rows="10"></textarea></li>
      </ul>
      <ul>
        <li data-width="10">内容：</li>
        <li><textarea id="content" rows="10"></textarea></li>
      </ul>
      <ul>
        <li></li>
        <li data-width="10"><a id="btn">发布</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="admin_right">
  <div class="form">
    <div class="body">
      <jsp:include page="/admin/article/include_cat.jsp" />
    </div>
  </div>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Upload.render('picUpload',{
    width:120,
    height:30,
    checkKlass:'nc-liat6-frame-web-upload-UploadStatus',
    url:'${PATH}/admin-File/uploadPic',
    onSuccess:function(r){
      I.$('pic').value = r.data;
    }
  });
  var editor = I.ui.Editor.render('content',{
    checkKlass:'nc-liat6-frame-web-upload-UploadStatus',
    uploadUrl:'${PATH}/admin-File/uploadBigPic'
  });
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('title',I.$('title').value);
      I.net.Rmi.set('desc',I.$('desc').value);
      I.net.Rmi.set('content',editor.getContent());
      I.net.Rmi.set('pic',I.$('pic').value);
      I.net.Rmi.set('keywords',I.$('keywords').value);
      var l = I.$('name','cats');
      var ps = [];
      for(var i=0;i<l.length;i++){
        if(l[i].checked){
          ps.push(l[i].value);
        }
      }
      I.net.Rmi.set('cats',ps.join(','));
      I.net.Rmi.call('admin-Article', 'add', function(r) {
        I.net.Page.find('admin-Article/pageList');
      });
    }
  });
});
</script>