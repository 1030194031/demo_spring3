<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="${ctx}/static/js/epiClock/jquery.epiclock.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/epiClock/jquery.countdown.min.js"></script>
	<title>倒计时</title>
	<script type="text/javascript">
		$(function(){
			//时间显示开始
			$('.epiClock').epiclock({ format : ' Y年F月j日　G : i : s　D' });//绑定
			$.epiclock();   //开始运行
			//时间显示插件结束
			
			
			//倒计时插件开始
			$('#clock1').countdown('2015/3/13 00:00:00', function(event) {
			   $(this).html(event.strftime('%D days %H:%M:%S'));
			 });
			 $('#clock2').countdown('2015/3/13 00:00:00', function(event) {
				var $this = $(this).html(event.strftime('<span>%w</span> 周<span> %d</span> 天 <span> %H</span> 小时<span> %M</span> 分钟 <span> %S</span> 秒'));
		     });
			 $('[data-countdown]').each(function() {
			    var $this = $(this), finalDate = $(this).data('countdown');
			    $this.countdown(finalDate, function(event) {
			     $this.html(event.strftime('%D 天 %H:%M:%S'));
			   });
			 });
			 //倒计时插件结束
		});
	</script>
</head>
<body>
	<form action="/front/user/getList" id="formSubmit" method="post">
	<table class="table table-bordered">
	   <caption>倒计时</caption>
	   <tbody>
	   	   <tr>
	         <td>
	         	当前时间显示：<span class="epiClock"></span>
    <br/><br/> 倒计时1：<span id="clock1"></span>
	<br/><br/> 倒计时2：<span id="clock2"></span>
	<br/><br/> 批量倒计时3：
	 <br/><span data-countdown="2016/01/01 12:00:00"></span>
	 <br/><span data-countdown="2017/01/01"></span>
	 <br/><span data-countdown="2018/01/01"></span>
	 <br/><span data-countdown="2019/01/01"></span>
	      </tr>
	   </tbody>
	</table>
	</form>
</body>
</html>