<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title><sitemesh:title ></sitemesh:title>-彭嵩鹤</title>
	<!-- js css -->
	<script src="${ctx }/static/js/jquery-1.9.1.js"></script>
	<link href="${ctx }/static/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="${ctx }/static/css/bootstrap/js/bootstrap.min.js"></script>
	<script src="/static/js/webutil.js"></script>
	<script type="text/javascript">
		var ctx='${ctx}';
	</script>
	<sitemesh:head ></sitemesh:head>
</head>
<body class="W-body">
	<sitemesh:body>
	</sitemesh:body>
</body>
</html>
