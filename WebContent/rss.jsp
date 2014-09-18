<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><?xml version="1.0" encoding='UTF-8'?>
<rss version="2.0">
<channel>
<title>RSS-${WEB_NAME}</title>
<image>
<title>${WEB_NAME}</title>
<link>http://${DOMAIN}${PATH}</link>
<url>http://${DOMAIN}${PATH}/favicon.png</url>
</image>
<description><![CDATA[${WEB_DESC}]]></description>
<link>http://${DOMAIN}${PATH}</link>
<language>zh-cn</language>
<generator>${WEB_AUTHOR}</generator>
<ttl>5</ttl>
<copyright><![CDATA[${WEB_COPYRIGHT}]]></copyright>
<pubDate>${pubTime}</pubDate>
<lastBuildDate>${pubTime}</lastBuildDate>
<c:forEach items="${articles}" var="o" varStatus="index">
<item>
  <title><![CDATA[${o.title}]]></title>
  <link>http://${DOMAIN}${PATH}/action-Article/detail?id=${o.id}</link>
  <author>${WEB_AUTHOR}</author>
  <guid>http://${DOMAIN}${PATH}/action-Article/detail?id=${o.id}</guid>
  <pubDate>${o.time}</pubDate>
  <c:forEach items="${o.cats}" var="cat" varStatus="idx">
    <category>${cat.name}</category>
  </c:forEach>
  <comments />
  <description><![CDATA[${o.description}]]></description>
</item>
</c:forEach>
</channel>
</rss>