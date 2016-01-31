<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="${ctx}/static/js/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<title>上传文件列表</title>
	<script type="text/javascript">
		$(function(){
			//加载图片上传控件
			$("#fileupload").uploadify({
				'uploader' : '${ctx}/static/js/uploadify/uploadify.swf',
			    'script'  :'${ctx}/front/uploadFile/file',//后台处理程序的相对路径
				'queueID' : 'fileQueue',//文件队列ID。与div的id一致
				'fileDataName' : 'fileupload',// 您的文件在上传服务器脚本阵列的名称。默认值='Filedata'
				'auto' : true,//是否自动上传
				'multi' : true,//是否多文件上传
				'hideButton' : false,//上传按钮的隐藏
				'buttonText' : 'Browse',//默认按钮的名字
				'buttonImg' : '${ctx}/static/js/uploadify/liulan.png',
				'width' : 105,
				'simUploadLimit' : 1,//允许同时上传的个数
				'sizeLimit' : 1048576,//控制上传文件的大小，单位byte
				'queueSizeLimit' : 2,//当允许多文件生成时，设置选择文件的个数
				'fileDesc' : '*',//上传文件格式控制
				'fileExt' : '*',//上传文件格式控制
				'folder' : '/img',//上传文件存放的目录 
				'cancelImg' : '${ctx}/static/js/uploadify/cancel.png',
				onSelect : function(event, queueID,fileObj) {
					$("#fileQueue").html("");
				},
				onComplete : function(event,queueID, fileObj, response,data) {
					$("#fileQueue").html("<br/><font color='green'>"+ fileObj.name + "上传成功</font>");
					window.location.reload();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
				}
			});
		});
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
		//批量下载
		function downObjBatch(){
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
			window.location="${ctx}/front/downFile?ids="+str;
		}
	</script>
</head>
<body>
	<form action="/front/user/getList" id="formSubmit" method="post">
		  <div class="form-inline">
		      <label for="name">文件</label>
		      <input type="file" class="form-control" id="fileupload" />
			  <div id="fileQueue" class="mt10"></div> 
		      <button type="button" class="btn btn-primary" onclick="javascript:downObjBatch()">批量下载</button>
		  </div>
	</form>
	
	<c:if test="${fileList.size()>0}">
		<table class="table table-hover">
		   <caption>悬停表格布局</caption>
		   <thead>
		      <tr>
		         <th><input id="allCheck" type="checkbox" onclick="allCheck(this)"/>id</th>
		         <th>文件名</th>
		         <th>上传时间</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
			  <c:forEach items="${fileList}" var="obj">
				 <tr id="rem${obj.id }">
			         <td><input type="checkbox" name="objIds" onClick="thisCheck()" value="${obj.id }"/>${obj.id }</td>
			         <td>${obj.name }</td>
			         <td><fmt:formatDate value="${obj.createTime }" type="date" dateStyle="long"/></td>
			         <td><a href="${ctx}/front/downFile?ids=${obj.id}">下载</a></td>
		         </tr>
			  </c:forEach>
		   </tbody>
		</table>
	</c:if>
	<c:if test="${fileList==null || fileList.size()==0 }">
		暂无信息
	</c:if>
</body>
</html>