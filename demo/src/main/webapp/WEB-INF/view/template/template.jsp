<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>模板</title>
	<script type="text/javascript">
		function pub(){
			$.ajax({
				url:'/front/pubVelocity',
				type:"post",
				dataType:'json',
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert("系统繁忙！");
					}
				}
			});
		}
	</script>
</head>
<body>
	<form action="${ctx }/front/updateTemplate" method="post">
		<table class="table table-bordered">
		   <caption>模板</caption>
		   <tbody>
		   	   <tr>
		         <td><label>内容</label></td>
		         <td>
		         	<textarea name="template.content" cols="" class="form-control" rows="3">
		         		<c:out value="${template.content}"></c:out>
		         	</textarea>
				 </td>
		      </tr>
		   </tbody>
		</table>
		<div>
			<input type="hidden" name="template.id" value="${template.id }"/>
		    <button type="submit" class="btn btn-primary" onclick="formSubmit()">1.更新模板</button>
		    <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1)">返回</button>
		    <button type="button" class="btn btn-primary" onclick="pub()">2.发布模板</button>
		    <button type="button" class="btn btn-primary" onclick="javascript:window.location='${ctx}/static/html/index.html'">3.访问</button>
		</div>
		<span>1.先更新模板 2.再发布 3.最后访问</span>
	</form>
</body>
</html>