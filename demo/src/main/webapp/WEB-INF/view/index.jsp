<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搭建的第一个ssm</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/mailAutoComplete/style.css"/>
	<script type="text/javascript" src="${ctx}/static/js/validate/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/mailAutoComplete/jquery.mailAutoComplete-3.1.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/layer-v2.1/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/layer-v2.1/layer/extend/layer.ext.js"></script>

	<script type="text/javascript">
		$(function(){
			//邮箱提示下拉
			$("#customTest").mailAutoComplete({
				boxClass: "out_box", //外部box样式
				listClass: "list_box", //默认的列表样式
				focusClass: "focus_box", //列表选样式中
				markCalss: "mark_box", //高亮样式
				autoClass: false,
				textHint: true, //提示文字自动隐藏
				hintText: "请输入邮箱地址"
			});

			//发送邮件弹窗
			$("#sendEmail").on("click",function(){
				layer.prompt({
					title: '输入您的邮箱'
				},function(val,index){
					sendEmail(val);
					//关闭弹窗
					layer.close(index);
				});
			})
		});
		/**
		 * 发送邮件
		 **/
		function sendEmail(email){
			$.ajax({
				url:"${ctx}/front/ajax/sendEmail",
				data:{"email":email},
				type:"post",
				dataType:"json",
				cache : false,
				async:false,
				success:function(result){
					alert(result.message);
				}
			});
		}
		/**
		 * 获取图形验证码
		 */
		function changeVerifyCode(){
			$("#verifyCode").attr("src","${ctx }/VerifyCode/code?"+Math.random());
		}
	</script>
</head>
<body>
	<form action="/front/getUserList" id="formSubmit" method="post">
	  <div class="form-inline">
	      <label>操作</label>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/user/getList'">用户增删改查</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/fileupload/list'">上传下载文件</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/template'">velocity</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/chooseUser'">弹窗选择用户</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/toValidate'">validate表单验证</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/toValidform'">validForm表单验证</button>
		      邮箱自动补全:<input type="text" id="customTest"  class="form-control" name="" value="">
		  <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/epiClock'">倒计时</button>
		  <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/My97DatePicker'">My97DatePicker</button>
	      <img alt="" id="verifyCode" src="${ctx }/VerifyCode/code" onclick="changeVerifyCode()" style="width:90px;height:40px;">
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/subject/subjectList'">ztree</button>
	      <button id="sendEmail" type="button" class="btn btn-primary" onclick="javascript:void(0)">邮件发送</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/interfaces'">接口</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/baiduMap'">百度地图</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.location='/front/userAjaxPage'">ajax分页</button>
		  <button type="button" class="btn btn-primary" onclick="javascript:window.location='${ctx}/front/toMd5'">md5加解密</button>
		  <button type="button" class="btn btn-primary" onclick="javascript:window.location='${ctx}/front/userPic'">Jcrop头像裁剪</button>
		  <button type="button" class="btn btn-primary" onclick="javascript:window.location='${ctx}/front/uploadifyTest'">上传插件测试</button>
	  </div>
	</form>
</body>
</html>