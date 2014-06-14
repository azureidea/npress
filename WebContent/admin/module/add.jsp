<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="form" class="form"></div>
<nlft:jsbegin>
I.run(function(){
  var form = I.awt.Form.create(I.$('form'));
  form.add({
    id : 'name',
    label : '名称',
    required : true
  });
  form.add({
    id : 'index',
    label : '序号',
    required : true,
    value:'0'
  });
  form.add({
    id : 'type',
    type : 'select',
    label : '类型',
    options:[
    {text:'本窗口打开的本站URL',value:'0'},
    {text:'新窗口打开的本站URL',value:'1'},
    {text:'本窗口打开的外站URL',value:'2'},
    {text:'新窗口打开的外站URL',value:'3'},
    {text:'本窗口打开的页面',value:'4'},
    {text:'新窗口打开的页面',value:'5'}
    ]
  });
  form.add({
    id : 'url',
    label : 'URL'
  });
  form.add({
    id : 'content',
    label : '内容',
    type:'textarea'
  });
  form.add({
    id : 'home',
    label : '首页',
    type:'select',
    options:[
      {text:'是',value:'1'},
      {text:'否',value:'0'}
    ]
  });
  form.add({
    type : 'line'
  });
  form.add({
    id : 'btn',
    type : 'button',
    label : '确定'
  });
  form.get('btn').onclick(function(m, e) {
    form.post('admin-Module', 'add', function(r) {
      find('admin-Module/pageList');
      hidePanel();
    });
  });
});
</nlft:jsbegin>