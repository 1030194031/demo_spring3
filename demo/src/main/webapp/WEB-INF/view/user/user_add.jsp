<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="${ctx}/static/js/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/js/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${ctx }/static/js/kindeditor/kindeditor-all.js"></script>
	<title>用户添加</title>
	<script type="text/javascript">
		$(function(){
			//加载图片上传控件
			$("#fileupload").uploadify({
				'uploader' : '${ctx}/static/js/uploadify/uploadify.swf',
			    'script'  :'${ctx}/front/uploadFile/img',//后台处理程序的相对路径
				'queueID' : 'fileQueue',//文件队列ID。与div的id一致
				'fileDataName' : 'fileupload',// 您的文件在上传服务器脚本阵列的名称。默认值='Filedata'
				'auto' : true,//是否自动上传
				'multi' : true,//是否多文件上传
				'hideButton' : false,//上传按钮的隐藏
				'buttonText' : 'Browse',//默认按钮的名字
				'buttonImg' : '${ctx}/static/js/uploadify/liulan.png',
				'width' : 105,
				'simUploadLimit' : 1,//允许同时上传的个数
				'sizeLimit' : 51200,//控制上传文件的大小，单位byte
				'queueSizeLimit' : 2,//当允许多文件生成时，设置选择文件的个数
				'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//上传文件格式控制
				'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//上传文件格式控制
				'folder' : '/img',//上传文件存放的目录 
				'cancelImg' : '${ctx}/static/js/uploadify/cancel.png',
				onSelect : function(event, queueID,fileObj) {
					$("#fileQueue").html("");
				},
				onComplete : function(event,queueID, fileObj, response,data) {
					$("#imgUrl").attr("src",'${ctx}'+response);
					$("#pic").val(response);
					$("#imgUrl").show();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
				}
			});
			
			//加载编辑器
			initKindEditor_addblog('kindEditor','576px','400px');
		});
	
		//添加博文页面编辑器
		function initKindEditor_addblog(id, width, height) {
			EditorObject = KindEditor.create('textarea[id=' + id + ']', {
				resizeType : 1,
				filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
				allowPreviewEmoticons : false,
				allowUpload : true,// 允许上传
				urlType : 'domain',// absolute
				newlineTag : 'br',// 回车换行br|p
				width : width,
				height : height,
				minWidth : '10px',
				minHeight : '10px',
				uploadJson : '${ctx}/front/editorUploadImg',// 图片上传路径
				afterBlur : function() {
					this.sync();
				},
				allowFileManager : false,
				items : [ 'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
						'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
						'justifyleft', 'justifycenter', 'justifyright',
						'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
						'image', 'link','plainpaste' ]
			});
		}
		function formSubmit(){
			$.ajax({
				url:'/front/user/add',
				data:$('#formSubmit').serialize(),
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
	<form action="/front/user/getList" id="formSubmit" method="post">
	<table class="table table-bordered">
	   <caption>添加用户</caption>
	   <tbody>
	   	   <tr>
	         <td><label>用户名</label></td>
	         <td>
	         	<input type="text" class="form-control" name="user.nickname" value="" placeholder="请输入用户名">
			 </td>
	      </tr>
	      <tr>
	         <td><label>头像</label></td>
	         <td>
	         	<input type="file" class="form-control" id="fileupload" />
				<img src="" alt="" width="400" height="300" style="display: none;" id="imgUrl" />
			    <div id="fileQueue"></div> 
			 </td>
	      </tr>
	      <tr>
	         <td><label>用户信息</label></td>
	         <td>
	         	<textarea rows="" cols="" name="user.content"  id="kindEditor"></textarea>
			 </td>
	      </tr>
	   </tbody>
	</table>
	  <div>
	      <input type="hidden" name="user.pic" id="pic"/>
	      <button type="button" class="btn btn-primary" onclick="formSubmit()">保存</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1)">返回</button>
	  </div>
	</form>
</body>
</html>