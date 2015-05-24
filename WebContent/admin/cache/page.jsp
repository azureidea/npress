<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="form">
  <ul>
    <li data-width="15">缓存目录：</li>
    <li><div class="remark">${dir}</div></li>
    <li></li>
  </ul>
  <ul>
    <li data-width="15">缓存文件数：</li>
    <li><div class="remark">${fileCount}</div></li>
    <li></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="15"><a id="btn">清空缓存</a></li>
    <li></li>
  </ul>
</div>
<script>
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    background:'#5bc0de',
    border:'1px solid #46b8da',
    color:'#FFF',
    background_hover:'#31b0d5',
    border_hover:'1px solid #269abc',
    color_hover:'#FFF',
    callback:function(){
      I.net.Rmi.call('admin-Cache', 'clear', function(r) {
        dashboard.find('admin-Cache/page');
      });
    }
  });
});
</script>