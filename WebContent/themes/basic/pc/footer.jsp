<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="footer">
  <div class="footer-body">
    <ul class="infos">
      <li><a href="">关于我们</a></li>
      <li><a href="">关于我们</a></li>
      <li><a href="">关于我们</a></li>
      <li><a href="">关于我们</a></li>
    </ul>
    <ul class="links"></ul>
    <div class="space"></div>
    <div>
      <i class="copyright">${WEB_COPYRIGHT}</i>
      <i class="info">基于<a href="https://github.com/6tail/nlf/" target="_blank">NLF框架</a>构建</i>
    </div>
  </div>
</div>
<script type="text/javascript">
I.want(function(){
  I.net.Page.find('action-Link/page',null,I.$('class','links')[0]);
});
</script>