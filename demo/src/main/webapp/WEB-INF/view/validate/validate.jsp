<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/js/validate/jquery.validate.errorStyle.css"/>
	<script type="text/javascript" src="${ctx}/static/js/validate/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/validate/messages_cn.js"></script>
	<!-- 	//用于表单class="{...}"调用 -->
	<script type="text/javascript" src="${ctx}/static/js/validate/jquery.metadata.js"></script>
	<title>表单验证</title>
	<script type="text/javascript">
		$(function(){
			$("#submitForm").validate({ 
// 				//失去焦点时验证
// 				onfocusout: function (element) {
// 			            $(element).valid();
// 			        },
// 			    //验证成功后显示信息
// 		        success:function(label){
// 	                   label.text("ok!").addClass("success");
// 	                },
				rules:{
				},
			    errorPlacement:function(error,element){
				   error.appendTo(element.parent());
                }, 
				messages : {
					checkChecked:{
						minlength:"请至少选择两项"
					}
				}
			});
		});
		//添加自定义验证
		$.validator.addMethod("isZipCode", function(value, element) {   
		    var tel = /^[0-9]{6}$/;
		    return this.optional(element) || (tel.test(value));
		}, "请正确填写您的邮政编码");
		
		//添加自定义验证
		$.validator.addMethod("checkSelect", function(value, element) {
			if($("#selectOption").val()=="0"){
				return false;
			}else{
				return true;
			}
		}, "请选择选项");
		
		function stringToDate(dat){
			var arr = dat.split("-");
		    var starttime = new Date(arr[0], arr[1], arr[2]);
		    return starttime.getTime();
		}
		
		function submitForm(){
			if($("#submitForm").valid()){
				alert("提交成功！");
			}
		}
	</script>
</head>
<body>
	<form action="" id="submitForm" method="post">
	<table class="table table-bordered">
	   <caption>表单验证</caption>
	   <tbody>
	   	   <tr>
	         <td><label>网址:</label></td>
	         <td>
	         	<input type="text" name="url" value="http://www.baidu.com" class="form-control {required:true,minlength : 6,url:true}"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label>电子邮箱:</label></td>
	         <td>
	         	<input type="text" name="email" value="www.123@qq.com" class="form-control {required:true,email:true}"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label>密码:</label></td>
	         <td>
	         	<input type="password" id="pwd" name="pwd" value="123123" class="form-control {required:true,minlength:6}"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label>确认密码:</label></td>
	         <td>
	         	<input type="password" id="pwdTwo" name="pwdTwo" value="123123" class="form-control {required:true,equalTo:'#pwd'}"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label>自定义验证邮政编码:</label></td>
	         <td>
	         	<input type="text" name="isZipCode" value="100000" class="form-control {isZipCode: true}"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label>自定义验证下拉框:</label></td>
	         <td>
	         	<select id="selectOption" name="selectOption" class="form-control {checkSelect: true}">
					<option value="0">--请选择--</option>
					<option value="1">选项一</option>
					<option value="2">选项二</option>
					<option value="3">选项三</option>
				</select>
			 </td>
	      </tr>
	      <tr>
	         <td><label>自定义复选框:</label></td>
	         <td>
	         	<input type="checkbox" name="checkChecked" value="1" class="{required:true,minlength:2}"/>1
				<input type="checkbox" name="checkChecked" value="2"/>2
				<input type="checkbox" name="checkChecked" value="3"/>3
			 </td>
	      </tr>
	   </tbody>
	</table>
	<div>
	   <input type="button" class="btn btn-primary" onclick="submitForm()" value="提交"/>
    </div>
	</form>
</body>
</html>