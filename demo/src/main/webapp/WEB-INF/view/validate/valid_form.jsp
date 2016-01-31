<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/validForm/css/style.css"/>
	<script type="text/javascript" src="${ctx}/static/js/validForm/Validform_v5.3.2_min.js"></script>
	<!-- 密码长度验证 -->
	<script type="text/javascript" src="${ctx}/static/js/validForm/plugin/passwordStrength/passwordStrength-min.js"></script>
	<title>表单验证</title>
	<script type="text/javascript">
	$(function(){
		var demo=$("#submitForm").Validform({
			tiptype:3,
			label:".tip",
			showAllError:true,
			datatype:{
				"zh1-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/
			},
			usePlugin:{//密码长度验证插件
				passwordstrength:{
					minLen:6,//设置密码长度最小值，默认为0;
					maxLen:18,//设置密码长度最大值，默认为30;
					trigger:function(obj,error){
						//该表单元素的keyup和blur事件会触发该函数的执行;
						//obj:当前表单元素jquery对象;
						//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;
						
						//console.log(error);
						if(error){
							$(".passwordStrength").next(".Validform_checktip").show();
							$(".passwordStrength").hide();	
						}else{
							$(".passwordStrength").next(".Validform_checktip").hide();
							$(".passwordStrength").show();	
						}
					}
				}
			}
		});
		
		demo.tipmsg.w["zh1-6"]="请输入1到6个中文字符！";
		
		demo.addRule(
		[
			{
				ele:".inputxt:eq(0)",
				datatype:"zh2-4"
			},
			{
				ele:".inputxt:eq(1)",
				datatype:"*6-20"
			},
			{
				ele:".inputxt:eq(2)",
				datatype:"*6-20",
				recheck:"pwd"
			},
			{
				ele:"select",
				datatype:"*"
			},
			{
				ele:":radio:first",
				datatype:"*"
			},
			{
				ele:":checkbox:first",
				datatype:"*"
			},
			{
				ele:".inputxt:eq(3)",
				datatype:"m",
				errormsg:"请填写正确手机号码！"
			},
			{
				ele:".inputxt:eq(4)",
				datatype:"e"
			},
			{
				ele:".inputxt:eq(5)",
				datatype:"url",
				errormsg:"请填写正确url！"
			}
		]);
		
	});
	</script>
</head>
<body>
	<form action="http://www.baidu.com" id="submitForm" method="post">
	<table class="table table-bordered">
	   <caption>表单验证</caption>
	   <tbody>
	   	  <tr>
	         <td><label class="tip">中文2~4验证:</label></td>
	         <td>
	         	<input type="text" name="zhongwen" value="中文" class="form-control inputxt"/>
			 </td>
	      </tr>
	   	  <tr>
	         <td><label class="tip">密码:</label></td>
	         <td>
	         	<input type="password" name="pwd" value="" class="form-control inputxt" plugin="passwordStrength" />
	         	<div class="passwordStrength" style="display:none;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">确认密码:</label></td>
	         <td>
	         	<input type="password" name="pwdTwo" value="" class="form-control inputxt"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">手机号码：</label></td>
	         <td>
	         	<input type="text" name="tel" value="18395614061" class="form-control inputxt"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">邮箱：</label></td>
	         <td>
	         	<input type="text" name="email" value="1030194031@qq.com" class="form-control inputxt"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">url：</label></td>
	         <td>
	         	<input type="text" name="url" value="www.baidu.com" class="form-control inputxt"/>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">所在城市：</label></td>
	         <td>
	         	 <select name="province" class="form-control"><option value="">请选择城市</option><option value="1">瑞金市</option></select>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">性别：</label></td>
	         <td>
	         	 <input type="radio" value="1" name="gender"/><label for="male">男</label> <input type="radio" value="2" name="gender"/><label for="female">女</label>
			 </td>
	      </tr>
	      <tr>
	         <td><label class="tip">购物网：</label></td>
	         <td>
	         	 <input name="shoppingsite2" type="checkbox"  value="1" /><label for="shoppingsite21">新蛋</label>
                 <input name="shoppingsite2" type="checkbox"  value="2" /><label for="shoppingsite22">淘宝</label>
                 <input name="shoppingsite2" type="checkbox"  value="3" /><label for="shoppingsite23">京东</label>
			 </td>
	      </tr>
	   </tbody>
	</table>
	<div>
	   <input type="submit" value="提交"/>
    </div>
     <h2>说明：</h2>
     <div class="tipmsg">
     	<p>1、没有找到Validform_checktip时，会根据tiptype自动创建显示提示信息的标签；</p>
         <p>2、没有绑定nullmsg时，默认查找Validform_label标签内的文字做提示，传入了“label”参数时会查找label指定的选择符；</p>
         <p>3、没有绑定errormsg时，自动根据datatype输出相应提示文字；</p>
     </div>
	</form>
</body>
</html>