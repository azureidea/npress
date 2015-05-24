<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="${WEB_KEY}${empty art?'':(empty art.keywords?'':','.concat(art.keywords))}" />
<meta name="description" content="${WEB_DESC}" />
<meta name="author" content="${WEB_AUTHOR}" />
<meta name="copyright" content="${WEB_COPYRIGHT}" />
<meta name="MobileOptimized" content="1024" />
<meta name="Iphone-content" content="1024" />
<meta name="viewport" content="width=1024" />
<meta name="format-detection" content="telephone=no,address=no,email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<link type="text/css" rel="stylesheet" href="${PATH}/themes/${theme}/pc/css/style.css" />
<link type="text/css" rel="stylesheet" href="${PATH}/css/font-awesome.min.css" />
<script type="text/javascript" src="${PATH}/js/icore.js"></script>