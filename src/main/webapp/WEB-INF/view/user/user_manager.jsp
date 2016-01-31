<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户列表</title>
	<script type="text/javascript">
		function searchForm(){
			$("#searchForm").submit();
		}
		//点击全选chenckbox
		function allCheck(cb){
			$("input[name=objIds]").prop('checked', cb.checked);
		}
		//点击单独checkbox
		function thisCheck(){
			var $tmp=$("[name=objIds]:checkbox");
			$('#allCheck').prop('checked',$tmp.length==$tmp.filter(':checked').length);
		}
		//批量删除
		function delObjBatch(){
			var objIds=$("[name=objIds]");
			var str="";
			var checked=true;
			$(objIds).each(function(){
				if($(this).prop("checked")){
					str+=this.value+",";
					checked=false;
				}
			});
			str=str.substring(0,str.length-1);
	
			if(checked){
				alert("请至少选择一条信息");
				return;
			}
			if(confirm("确定删除吗")){
				$.ajax({
					url:"${ctx}/front/user/del",
					data:{"ids":str},
					type:"post",
					dataType:"json",
					success:function(result){
						if(result.success){
							alert(result.message);
							window.location.reload();
						}else{
							alert("系统繁忙稍后重试");
							return;
						}
					}
				});
			}
		}
		//单条删除
		function delObj(id){
			if(id==''){
				alert("请至少选择一条信息");
				return;
			}
			if(confirm("确定删除吗")){
				$.ajax({
					url:"${ctx}/front/user/del",
					data:{"ids":id},
					type:"post",
					dataType:"json",
					success:function(result){
						if(result.success){
							alert(result.message);
							window.location.reload();
						}else{
							alert("系统繁忙稍后重试");
							return;
						}
					}
				});
			}
		}
	</script>
</head>
<body>
	<form action="${ctx}/front/user/getList" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/> 
		  <div class="form-inline">
		      <label for="name">名称</label>
		      <input type="text" class="form-control" id="valueId" name="queryUser.nickname" value="${queryUser.nickname }" placeholder="请输入用户名">
		      <button type="button" class="btn btn-primary" onclick="searchForm()">查询</button>
		      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/user/to_add'">添加用户</button>
		      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/user/to_batch_add'">批量导入用户</button>
		      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/user/export'">批量导出用户</button>
		      <button type="button" class="btn btn-primary" onclick="delObjBatch()">批量删除用户</button>
		  </div>
	</form>
	
	<c:if test="${userList.size()>0}">
		<table class="table table-hover">
		   <caption>悬停表格布局</caption>
		   <thead>
		      <tr>
		         <th><input id="allCheck" type="checkbox" onclick="allCheck(this)"/>id</th>
		         <th>用户名</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
			  <c:forEach items="${userList}" var="obj">
				 <tr id="rem${obj.id }">
			         <td><input type="checkbox" name="objIds" onClick="thisCheck()" value="${obj.id }"/>${obj.id }</td>
			         <td>${obj.nickname }</td>
			         <td><a href="/front/user/to_update/${obj.id}">修改</a>&nbsp;<a href="javascript:delObj(${obj.id })">删除</a></td>
		         </tr>
			  </c:forEach>
		   </tbody>
		</table>
		<div>
			<jsp:include page="/WEB-INF/view/common/page.jsp"/>
		</div>
	</c:if>
	<c:if test="${userList==null || userList.size()==0 }">
		暂无信息
	</c:if>
</body>
</html>