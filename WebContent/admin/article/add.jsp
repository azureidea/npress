<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="form">
  <ul>
    <li data-width="20">
      <div id="form-left">
        <jsp:include page="/admin/article/include_cat.jsp" />
      </div>
    </li>
    <li>
      <div id="form-right">
        <ul>
          <li data-width="10">标题：</li>
          <li><input id="title" type="text" /></li>
        </ul>
        <ul>
          <li data-width="10">缩略图：</li>
          <li><input id="pic" type="text" /></li>
          <li>
            <div id="picUpload">
              <i>上传</i>
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
          <li><textarea id="desc" rows="6"></textarea></li>
        </ul>
        <ul>
          <li data-width="10">内容：</li>
          <li><textarea id="content"></textarea></li>
        </ul>
        <ul>
          <li></li>
          <li data-width="10"><a id="btn-preview">预览</a></li>
          <li data-width="1"></li>
          <li data-width="10"><a id="btn">发布</a></li>
        </ul>
      </div>
    </li>
  </ul>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Form.render('form-left');
  I.ui.Form.render('form-right');
  I.ui.Upload.render('picUpload',{
    width:80,
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
  I.ui.Button.render('btn-preview',{
    callback:function(){
      var form = I.insert('form');
      form.style.display = 'none';
      form.action = '${PATH}/admin-Article/preview';
      form.target = '_blank';
      form.method = 'post';
      var input = I.insert('input',form);
      input.name = 'title';
      input.value = I.$('title').value;

      input = I.insert('input',form);
      input.name = 'content';
      input.value = I.$('content').value;

      input = I.insert('input',form);
      input.name = 'title';
      input.value = I.$('title').value;
      form.submit();
    }
  });
  I.ui.Button.render('btn',{
    background:'#5bc0de',
    border:'1px solid #46b8da',
    color:'#FFF',
    background_hover:'#31b0d5',
    border_hover:'1px solid #269abc',
    color_hover:'#FFF',
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
          ps.push(l[i].getAttribute('data-id'));
        }
      }
      I.net.Rmi.set('cats',ps.join(','));
      I.net.Rmi.call('admin-Article','add',function(r){});
    }
  });
});
</script>