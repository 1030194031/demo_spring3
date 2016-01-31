<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- my97 -->
	<script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
	
	<!-- jqueryTime -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
	<script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js"></script>
	
	<title>My97DatePicker</title>
	<script type="text/javascript">
		//jquery time
		$(function(){
			timeInit();
		});
		function timeInit(){
			var now=new Date();
			var month = now.getMonth() + 1 < 10 ? "0" + (now.getMonth() + 1) : now.getMonth() + 1;
			var currentDate = now.getDate() < 10 ? "0" + now.getDate() : now.getDate();
			var nowTime=now.getFullYear()+"-"+month+"-"+ currentDate;
			$("#beginTime").val(nowTime);
			$("#endTime").val(nowTime);
			
			//datetimepicker  显示时分秒
			$( "#beginTime").datepicker(
					{regional:"zh-CN",
					changeMonth: true,
					minDate:nowTime,
					onSelect: function(dateText, inst) {
						$('#endTime').datepicker('option', 'minDate', new Date(dateText.replace('-',',')));}
			});
			$( "#endTime").datepicker(
					{regional:"zh-CN",
					changeMonth: true,
					minDate:nowTime,
					onSelect: function(dateText, inst) {
						$('#beginTime').datepicker('option', 'maxDate', new Date(dateText.replace('-',',')));}
			});
		}
	</script>
</head>
<body>
	<table class="table table-bordered">
	   <caption>My97DatePicker</caption>
	   <tbody>
	   		<!-- my97 -->
	   	   <tr>
	         <td>
				<input class="Wdate" type="text" onClick="WdatePicker()"> <br/>
				前面的日期不能大于后面的日期且两个日期都不能大于 2020-10-01 <br/>
				<input id="d4311" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
				<input id="d4312" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
				<br/>更多demo请访问官方主页 <a href="http://www.my97.net">http://www.my97.net</a>
			 </td>
	      </tr>
	      
	      <!-- jquery time -->
	      <tr>
	         <td>
	         	jquery time
        		<input type="text" class="form-control" readonly="readonly"  id="beginTime"/>
				<input type="text" class="form-control" readonly="readonly"  id="endTime"/>
			 </td>
	      </tr>
	   </tbody>
	</table>
</body>
</html>