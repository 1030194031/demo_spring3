<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>MD5加解密</title>
	<script type="text/javascript">
		/**
		 * MD5加密
		 */
		function getMd5(){
			var originalContent=$("#originalContent").val();
			$.ajax({
				url:ctx+"/front/ajax/stringMd5",
				data:{"txt":originalContent},
				type:"post",
				dataType:"json",
				cache : false,
				async:false,
				success:function(result){
					$("#md5Content").val(result.entity);
				}
			});
		}
		/**
		 * MD5解密
		 */
		function convertMd5(){
			var md5Content=$("#md5Content").val();
			$.ajax({
				url:ctx+"/front/ajax/convertMD5",
				data:{"txt":md5Content},
				type:"post",
				dataType:"json",
				cache : false,
				async:false,
				success:function(result){
					$("#originalContent").val(result.entity);
				}
			});
		}
	</script>
</head>
<body>
	<table border="1">
		<tr>
			<td>原内容：</td>
			<td><textarea id="originalContent" rows="10" cols="200"></textarea></td>
		</tr>
		<tr>
			<td>MD5:</td>
			<td><textarea id="md5Content" rows="10" cols="200"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a href="javascript:void(0)" onclick="getMd5()">加密</a>&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="convertMd5()">解密</a>
			</td>
		</tr>
	</table>
</body>
</html>