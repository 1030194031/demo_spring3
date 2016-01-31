<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户ajax分页</title>
	<script type="text/javascript">
		function loadAjax(){
			ajaxPage("/front/user/ajaxList",'',1,callBack);
		}
		function callBack(result){
			$("#showAjaxList").html(result);
		}
	</script>
</head>
<body>
<a class="btn btn-primary" onclick="loadAjax()">ajax加载分页</a>
<span id="showAjaxList"></span>
</body>
</html>