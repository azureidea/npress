<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp" />
<link rel="stylesheet" href="${PATH}/css/admin.css" />
<script type="text/javascript">
function userLogout(){
  I.want(function(){
    I.net.Rmi.call('action-Login','logout',function(r){
      self.location = '${PATH}/';
    });
  });
}
</script>