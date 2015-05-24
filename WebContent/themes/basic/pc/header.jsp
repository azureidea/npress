<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
  <div class="topbar">${WEB_NAME}</div>
  <form id="searchForm" class="search pull-right" method="get" action="${PATH}/action-Search/result">
    <input value="${s}" name="s" type="text" placeholder="输入关键字" x-webkit-speech />
    <a href="javascript:void(0);" onclick="document.getElementById('searchForm').submit();">搜索</a>
  </form>
  <a class="logo" href="${PATH}/"></a>