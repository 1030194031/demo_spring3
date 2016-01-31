<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>ajax用户列表</title>
	<script type="text/javascript">
	</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty userList}">
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
			<div>
				<jsp:include page="/WEB-INF/view/common/ajax_page.jsp"/>
			</div>
		</c:when>
		<c:otherwise>暂无信息</c:otherwise>
	</c:choose>
</body>
</html>