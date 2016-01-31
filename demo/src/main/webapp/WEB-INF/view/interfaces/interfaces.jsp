<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>接口获取</title>
	<script type="text/javascript">
		function getWeather(){
			var cityname=$("#cityname").val();
			if(cityname==null||cityname==''){
				alert("请输入要查询的城市名");
				return;
			}
			$.ajax({
				url:'/front/getWeather',
				data:{"cityname":cityname},
				type:"post",
				dataType:'json',
				success:function(result){
					if(result.success){
						var cityinfo1=result.entity;
						var cityinfo = eval('('+result.entity+')');
						if(cityinfo!=null||cityinfo!=''){
							$("#info1").html(cityinfo1);
							var content='<br/>';
							content+='城市名:'+cityinfo.showapi_res_body.cityInfo.c3+"<br/>";
							content+='时间:'+cityinfo.showapi_res_body.f1.day+'<br/>';
							content+='白天天气:'+cityinfo.showapi_res_body.f1.day_weather+'<img src="'+cityinfo.showapi_res_body.f1.day_weather_pic+'"><br/>';
							content+='白天天气温度(摄氏度):'+cityinfo.showapi_res_body.f1.day_air_temperature+"<br/>";
							content+='白天风向编号:'+cityinfo.showapi_res_body.f1.day_wind_direction+"<br/>";
							content+='晚上天气:'+cityinfo.showapi_res_body.f1.night_weather+'<img src="'+cityinfo.showapi_res_body.f1.night_weather_pic+'"><br/>';
							content+='晚上天气温度(摄氏度):'+cityinfo.showapi_res_body.f1.night_air_temperature+"<br/>";
							content+='晚上风向编号:'+cityinfo.showapi_res_body.f1.night_wind_direction+'<br/>';
							content+='-----------------------<br/>';
							content+='时间:'+cityinfo.showapi_res_body.f2.day+'<br/>';
							content+='白天天气:'+cityinfo.showapi_res_body.f2.day_weather+'<img src="'+cityinfo.showapi_res_body.f2.day_weather_pic+'"><br/>';
							content+='白天天气温度(摄氏度):'+cityinfo.showapi_res_body.f2.day_air_temperature+"<br/>";
							content+='白天风向编号:'+cityinfo.showapi_res_body.f2.day_wind_direction+"<br/>";
							content+='晚上天气:'+cityinfo.showapi_res_body.f2.night_weather+'<img src="'+cityinfo.showapi_res_body.f2.night_weather_pic+'"><br/>';
							content+='晚上天气温度(摄氏度):'+cityinfo.showapi_res_body.f2.night_air_temperature+"<br/>";
							content+='晚上风向编号:'+cityinfo.showapi_res_body.f2.night_wind_direction+'<br/>';
							$("#info").html(content);
						}
					}else{
						alert("系统繁忙！");
					}
				}
			});
		}
		function getHAHA(){
			$.ajax({
				url:'/front/getJoke',
				type:"post",
				dataType:'json',
				success:function(result){
					if(result.success){
						var info=result.entity;
						var content = '';
						var list = eval('('+result.entity+')');
						if(list!=null||list!=''){
							$("#hahainfo1").html(info);
							for(i=0;i<list.showapi_res_body.contentlist.length;i++){
								content+='<h2>'+list.showapi_res_body.contentlist[i].title+'</h2><br/>';
								content+='<img src="'+list.showapi_res_body.contentlist[i].img+'"/><br/>';
							}
							
							
							$("#hahainfo").html(content);
						}
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
	   <caption>获取天气</caption>
	   <tbody>
	   	   <tr>
	         <td><label><font color="red">*</font>城市名</label></td>
	         <td>
	         	<input type="text" id="cityname" class="form-control" value="北京" placeholder="请输入城市名">
			 </td>
	      </tr>
	      <tr>
	         <td><label>天气信息信息</label></td>
	         <td>
	         	<span id="info1"></span>
	         	<span id="info"></span>
			 </td>
	      </tr>
	   </tbody>
	</table>
	  <div>
	      <input type="hidden" name="user.pic" id="pic"/>
	      <button type="button" class="btn btn-primary" onclick="getWeather()">获取</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1)">返回</button>
	  </div>
	</form>
	
	<form action="/front/user/getList" id="formSubmit" method="post">
	<table class="table table-bordered">
	   <caption>获取笑话</caption>
	   <tbody>
	      <tr>
	         <td><label>笑话信息</label></td>
	         <td>
	         	<span id="hahainfo1"></span>
	         	<span id="hahainfo"></span>
			 </td>
	      </tr>
	   </tbody>
	</table>
	  <div>
	      <input type="hidden" name="user.pic" id="pic"/>
	      <button type="button" class="btn btn-primary" onclick="getHAHA()">获取</button>
	      <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1)">返回</button>
	  </div>
	</form>
</body>
</html>