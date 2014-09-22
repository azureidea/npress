<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="form">
<div id="form">
  <ul>
    <li data-width="10">缓存目录：</li>
    <li><div>${dir}</div></li>
  </ul>
  <ul>
    <li data-width="10">缓存文件数：</li>
    <li><div>${fileCount}</div></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="10"><a id="btn">清空缓存</a></li>
  </ul>
</div>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.call('admin-Cache', 'clear', function(r) {
        I.net.Page.find('admin-Cache/page');
      });
    }
  });
});
</script>