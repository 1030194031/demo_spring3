<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户列表</title>
	<script type="text/javascript">
		function formSubmit(){
			$("#formSubmit").submit();
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
		
		
		//存放数据的数组
		var myArrayMoveStock = new Array();
		//将小页面被选中的入库明细信息带到大页面
		function selectQstList() {
			if (initArray()) {
				//调用父页面的方法
				window.close();
			}
		}
		function initArray() {
			var qstChecked = $("[name=objIds]:checked");
			if (qstChecked.length == 0) {
				alert("请选择用户");
				return;
			}
			qstChecked.each(function() {
				toParentsValue($(this).val());
			});
			opener.addnewId(myArrayMoveStock);
			quxiao();
		}
		// 把选中一条记录放到数组中
		function toParentsValue(obj) {
			myArrayMoveStock.push(obj);
		}
		function quxiao() {
			window.close();
		}
	</script>
</head>
<body>
	<form action="/front/user/selectUserList" id="formSubmit" method="post">
		  <div class="form-inline">
		      <label for="name">名称</label>
		      <input type="text" class="form-control" id="valueId" name="queryUser.nickname" value="${queryUser.nickname }" placeholder="请输入用户名">
		      <button type="button" class="btn btn-primary" onclick="formSubmit()">查询</button>
		      <button type="button" class="btn btn-primary" onclick="selectQstList()">确定</button>
		  </div>
	</form>
	
	<c:if test="${userList.size()>0}">
		<table class="table table-hover">
		   <caption>悬停表格布局</caption>
		   <thead>
		      <tr>
		         <th><input id="allCheck" type="checkbox" onclick="allCheck(this)"/>id</th>
		         <th>用户名</th>
		      </tr>
		   </thead>
		   <tbody>
			  <c:forEach items="${userList}" var="obj">
				 <tr id="rem${obj.id }">
			         <td><input type="checkbox" name="objIds" onClick="thisCheck()" value="${obj.id }"/>${obj.id }</td>
			         <td>${obj.nickname }</td>
		         </tr>
			  </c:forEach>
		   </tbody>
		</table>
	</c:if>
	<c:if test="${userList==null || userList.size()==0 }">
		暂无信息
	</c:if>
</body>
</html>