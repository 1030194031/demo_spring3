<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>

<c:if test="${not empty page}">
	<span>当前第${page.currentPage}页/共${page.totalPageSize}页</span>
	<span>${page.totalCount}条记录</span>
	<ul class="pagination" style="float: right;">
		<c:choose>
			<c:when test="${page.first}">
				<li><a href="javascript:void(0)">首页</a></li>
				<li><a href="javascript:void(0)">上一页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:goPage(1)">首页</a></li>
				<li><a href="javascript:goPage(${page.currentPage-1 })">上一页</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${page.last}">
				<li id="nextpage"><a href="javascript:void(0)">下一页</a></li>
				<li><a href="javascript:void(0)">尾页</a></li>
			</c:when>
			<c:otherwise>
				<li id="nextpage" ><a href="javascript:goPage(${page.currentPage+1})">下一页</a></li>
				<li><a href="javascript:goPage(${page.totalCount})">尾页</a></li>
			</c:otherwise>
		</c:choose>
		 <li class="c_333">
	           	<tt class="ml10 disIb">第</tt>
	           	<input id="pageNoIpt" type="text" size="4" style="height:16px; margin-top:2px; width:24px; border:1px solid #999999;" /><tt class="ml10 disIb">页</tt>
	           	<input class="btn btn-y ml10" type="button" onclick="goPageByInput()" value="确定" name="确定">
	           	&nbsp;&nbsp;
	       	</li>    
	</ul>
</c:if>

<script type="text/javascript">
	var currentPage = parseInt('${page.currentPage-1}')<1?1:parseInt('${page.currentPage}');//当前页码
	var totalPageSize='${page.totalPageSize}';//总页码
	$(function(){
		 showPageNumber();//显示分页中间页码
	});
	
	function goPage(pageNum){
	   	if(/^\d+$/.test(pageNum)==false) {
	   		return;
	   	}
	   	if(pageNum < 1) {
	   		pageNum = 1;
	   	}
	   	if(pageNum > totalPageSize) {
	   		if(totalPageSize>0){
	   			pageNum = totalPageSize;
	   		}else{
	   			pageNum=1;
	   		}
	   	}
		$("#pageCurrentPage").val(pageNum);
		$("#searchForm").submit();
	}
	//显示中间页码
	function showPageNumber() {
	   	var pageHtml="";
	   	var maxNum_new = currentPage>4?6:7-currentPage;//最大显示页码数
	   	var discnt=1;
	   	for(var i=4; i>0; i--) {
	   		if(currentPage>i) {
	   			pageHtml = pageHtml + "<li><a href='javascript:goPage("+(currentPage-i)+")'>"+ (currentPage-i) +"</a></li>";
	   			discnt++;
	   		};
	   	}
	   	pageHtml = pageHtml + '<li class="active"><a href="javascript:void(0)">'+currentPage+'</a></li>';
	   	for(var i=1; i<maxNum_new; i++) {
	   		if(currentPage+i<=totalPageSize && discnt<6) {
	   			pageHtml = pageHtml + "<li><a href='javascript:goPage("+(currentPage+i)+")'>"+ (currentPage+i) +"</a></li>";
	   			discnt++;
	   		} else {
	   			break;
	   		};
	   	}
	   	$(pageHtml).insertBefore("#nextpage");
	}
	 //跳转到页面
    function goPageByInput() {
    	var pageNo = document.getElementById("pageNoIpt").value;
    	if(/^\d+$/.test(pageNo)==false) {
    		alert("只能输入整数，请重新输入！");
    		document.getElementById("pageNoIpt").value='';
    		return;
    	}
    	goPage(pageNo);
    }
</script>