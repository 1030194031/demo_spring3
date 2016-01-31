<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户列表</title>
	<script type="text/javascript">
		//选择用户
		function showNewwin(){
			window.open('${ctx}/front/user/selectUserList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		} 
		
		//返回选择集合
		function addnewId(newTcIdArr){
			var objIds ='';
			if($("#objIds").val()!=''){
				objIds = $("#objIds").val().split(",");
			}else{
				objIds=new Array();
			}
			for(var i=0;i<newTcIdArr.length;i++){
				var _exist=$.inArray(newTcIdArr[i],objIds);
				if(_exist<0){
					objIds.push(newTcIdArr[i]);
				}
			}
			$("#objIds").val(objIds);
			queryName();
		}
		//查询昵称
		function queryName(){
			var ids = $("#objIds").val();
			if(ids!=null&&ids!=""){
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "${ctx}/front/user/getUserIds",
					data : {
						"ids" : ids
					},
					async : false,
					success : function(result) {
						if (result.success == true) {
							var str = "";
							var userList = result.entity;
							for(var i=0;i<userList.length;i++){
								var tc = userList[i];
								str+='<p style="width:100%;margin: 0 0 0em">'+tc.nickname+tc.id+'&nbsp;&nbsp;<a onclick="delObj(\''+tc.id+'\')" href="javascript:void(0)"> 删除</a></p>';
							}
							$("#tchtml").html(str);
						} 
					}
				});
			}else{
				$("#tchtml").html("");
			}
		}
		//删除学员
		function delObj(id){
			var objIds = $("#objIds").val();
			objIds=","+objIds+",";
			var pattern =","+id+",";
			objIds = objIds.replace(new RegExp(pattern), ",");
			objIds = objIds.split(",").unique();
			$("#objIds").val(objIds);
			queryName();
		}
		//自定义方法-数组去重复
		Array.prototype.unique = function(){
			var newArr = []; //一个新的临时数组
			for(var i = 0; i < this.length; i++){ //遍历当前数组
				if(this[i]==""){
					continue;
				}
				//如果当前数组的第i已经保存进了临时数组，那么跳过，否则把当前项push到临时数组里面
				if (newArr.indexOf(this[i]) == -1){
					newArr.push(this[i]);
				}
			}
			return newArr;
		}
	</script>
</head>
<body>
	<form action="/front/user/getList" id="formSubmit" method="post">
		  <div class="form-inline">
		      <button type="button" class="btn btn-primary" onclick="showNewwin()">选择</button>
		  </div>
	</form>
	<input id="objIds" type="text" name="" value="" />
	<span id="tchtml"></span>
</body>
</html>