<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">  
/* 		html{height:100%}   */
/* 		body{height:100%;margin:0px;padding:0px}   */
		body, html,#container {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>  
	<script src="${ctx }/static/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BUuYf2lWpYBQNVPu39PSZGBZ"></script>
	<title>地图展示</title>
</head>
<body>
	<div id="container" style="width: 550px; height: 325px;" ></div> 
	<input id="lng" type="text" value="116.404"/>
	<input id="lat" type="text" value="39.915"/>
	城市名: <input id="cityName" type="text"/>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	
	<script type="text/javascript"> 
		var lng=$('#lng').val();
		var lat=$('#lat').val();
		var myValue;//搜索点击下拉的坐标
		
		var map = new BMap.Map("container");
		var point = new BMap.Point(lng, lat);
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker); // 将标注添加到地图中
		var g=new BMap.Geocoder();//地址逆解析  根据坐标获取名称
		//添加文字标签
		var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(20,-10)});
		marker.setLabel(label);
		
		$(function(){
			loadMap();
			search();
		});
		
		/***加载地图开始*****/
		function loadMap(){
//	 		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的标注点
			map.centerAndZoom(point, 15);//初始化地图
			map.enableScrollWheelZoom(true);//开启滚轮缩放
			//工具条  比例尺 控件
			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
			map.addControl(top_left_control);        
			map.addControl(top_left_navigation);     
			map.addControl(top_right_navigation); 
			marker.enableDragging();//标注点是否可以拖动
			//标注点拖拽监听
		    marker.addEventListener("dragend", function (e) {
		    	//修改文字标签
		    	getLocation(e.point);
		    	
		    	var cp = map.getCenter();
		    	$('#lat').val( e.point.lat);
		    	$('#lng').val( e.point.lng);
		    	map.panTo(new BMap.Point(e.point.lng, e.point.lat));
	        });
			
			//地图点击事件
		    map.addEventListener("click", function(e) { 
		    	//修改文字标签
		    	getLocation(e.point);
		    	
		    	var cp = map.getCenter();
				marker.setPosition(e.point);
				map.panTo(new BMap.Point(e.point.lng, e.point.lat));
			});
		}
		/***加载地图结束*****/
		
		/***搜索开始*****/
		function search(){
			var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
					{"input" : "cityName"
					,"location" : map
				});
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
				var str = "";
					var _value = e.fromitem.value;
					var value = "";
					if (e.fromitem.index > -1) {
						value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
					}    
					str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
					
					value = "";
					if (e.toitem.index > -1) {
						_value = e.toitem.value;
						value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
					}    
					str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
					$("#searchResultPanel").html(str);
				});
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
				var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
				setPlace();
			});
		}
		function setPlace(){
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				map.centerAndZoom(pp, 18);
				var cp = map.getCenter();
				marker.setPosition(cp);
			}
			var local = new BMap.LocalSearch(map, { //智能搜索
			  onSearchComplete: myFun
			});
			local.search(myValue);
		}
		
		//修改文字标签
		function getLocation(poimt){
			g.getLocation(poimt,function(e){
	    		var addComp = e.addressComponents;
	    		var locationName=addComp.street + " " + addComp.streetNumber;
	    		label.setContent(locationName);
	    		$("#cityName").val(locationName);
	    	});
		}
		/***搜索结束*****/
	</script> 
</body>
</html>

