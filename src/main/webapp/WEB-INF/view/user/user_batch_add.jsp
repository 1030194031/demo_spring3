<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户批量导入</title>
	<script type="text/javascript">
		function formSubmit(){
			$("#formSubmit").submit();
		}
	</script>
</head>
<body>
	<form action="/front/user/importExcel" id="formSubmit" method="post" enctype="multipart/form-data">
	  <div class="form-inline">
	  	  <a href="${ctx }/static/excel/import_student.xls">模板下载</a>
	      <input id="myFile" type="file" value="" name="myFile"/>
	      <button type="button" class="btn btn-primary" onclick="formSubmit()">保存</button>
	      
	      ${msg }
	  </div>
	</form>
</body>
</html>