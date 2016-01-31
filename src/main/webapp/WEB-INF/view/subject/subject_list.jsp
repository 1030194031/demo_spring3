<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户列表</title>
	<link rel="stylesheet" href="${ctx}/static/js/ztree/css/zTreeStyle.css" type="text/css" />
	<link rel="stylesheet" href="${ctx }/static/js/ztree/css/demo.css">
	<script type="text/javascript" src="${ctx}/static/js/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
		$(function(){
			var zTreeNodes=${subjectListString};
			var treeObj= $.fn.zTree.init($("#tree"), setting, zTreeNodes);
			treeObj.expandAll(true);
			
			var checked = treeObj.getNodes()[0].checked;
		});
		var setting = {
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }
				},
				view: {
					showIcon: true,
					showLine: true
				},
				edit: {
					enable: true,
					editNameSelectAll: true,
					renameTitle: "编辑专业名称",
					removeTitle: "删除专业"
				},
				data: {
					key: {
						title: "name",
						name:"name"
					},
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					}
				},
				callback: {
					beforeRename: beforeRename,
					onRename: updateSubject,
					beforeRemove: beforeDelSubject,
					onDrop: updateSubjectParent
				}
		};
		//修改名称前检验数据
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				alert("专业名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("tree");
				setTimeout(function(){zTree.editName(treeNode)}, 10);
				return false;
			}
			return true;
		}
		//修改专业名称
		function updateSubject(event, treeId, treeNode, isCancel) {
			if(treeNode.name==''){
				alert("请填写名称");
				return false;
			}
			$.ajax({
				url:'${ctx}/front/subject/updateSubject',
				type:'post',
				dataType:'json',
				data:{
					'subject.id':treeNode.id,
					'subject.name':treeNode.name
				},
				success:function(result){
					if(result.success==false){
						alert('系统繁忙');
					}
				}
			});
		}
		//删除专业
		function beforeDelSubject(treeId, treeNode){
			var tip='确定删除';
			if(treeNode.isParent){
				tip+='父节点"'+treeNode.name+'"';
			}else{
				tip+='"'+treeNode.name+'"'
			}
			tip+='及其子节点吗？';
			if(confirm(tip)){
				$.ajax({
					url:'${ctx}/front/subject/delSubject',
					type:'post',
					dataType:'json',
					data:{
						'ids':treeNode.id
					},
					success:function(result){
						if(result.success==false){
							alert('系统繁忙');
						}else{
							return true;
						}
					}
				});
			}else{
				return false;
			}
		}
		//修改专业父节点
		function updateSubjectParent(event, treeId, treeNodes, targetNode, moveType) {
			var id=treeNodes[0].id;
			var pId=0;
			if(targetNode!=null){
				//拖拽目标不是根目录
				pId=targetNode.id;
			}
			$.ajax({
				url:'${ctx}/front/subject/updateSubjectParent',
				type:'post',
				dataType:'json',
				data:{
					'subject.id':id,
					'subject.parentId':pId
				},
				success:function(result){
					if(result.success==false){
						alert('系统繁忙');
					}else{
						return true;
					}
				}
			});
		}
		//创建专业节点
		function createSubject(){
			var zTree = $.fn.zTree.getZTreeObj("tree"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			var pId=0;
			if (treeNode) {
				pId=treeNode.id;
				treeNode = zTree.addNodes(treeNode, {pId:treeNode.id, isParent:false, name:"新建专业"});
			} else{
				treeNode = zTree.addNodes(null, { pId:0, isParent:false, name:"新建专业"});
			}
			$.ajax({
				url:'${ctx}/front/subject/createSubject',
				type:'post',
				dataType:'json',
				data:{
					'subject.name':'新建专业',
					'subject.parentId':pId
				},
				success:function(result){
					if(result.success==false){
						alert('系统繁忙');
					}else{
						return true;
					}
				}
			});
		}
		//获得勾选checkbox的节点
		function getSelectSubject(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			var names='';
			for(var i=0;i<nodes.length;i++){
				names+="["+nodes[i].id+nodes[i].name+"]";
			}
			alert(names);
		}
		//全选
		function selectAllSubject(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			treeObj.checkAllNodes(true);//false
		}
		
		//checkNodecheckNodecheckNodecheckNodecheckNodecheckNode选中节点
	</script>
</head>
<body>
	<ul id="tree" class="ztree"></ul>
	<input type="button" class="btn btn-primary" value="全选"  onClick="selectAllSubject()"/>
	<input type="button" class="btn btn-primary" value="添加分类"  onClick="createSubject()"/>
	<input type="button" class="btn btn-primary" value="获得选中节点的id"  onClick="getSelectSubject()"/><br/>
	
------------------------------------------------------------------------------------------------------------------------------------------------
	<script type="text/javascript">
		$(function(){
			var zTreeNodes=${subjectListString};
			$.fn.zTree.init($("#treeDemo"), settimg, zTreeNodes);
		});
		var settimg = {
				view: {
					dblClickExpand: false
				},
				data: {
					key: {
						title: "name",
						name:"name"
					},
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					}
				},
				callback: {
					beforeClick: beforeClick,
					onClick: onClick
				}
			};

	 		function beforeClick(treeId, treeNode) {
	 			var check = (treeNode && !treeNode.isParent);
	 			if (!check) alert("只能选择子节点...");
	 			return check;
	 		}
	 		
	 		function onClick(e, treeId, treeNode) {
	 			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	 			nodes = zTree.getSelectedNodes(),
	 			v = "";
	 			nodes.sort(function compare(a,b){return a.id-b.id;});
	 			for (var i=0, l=nodes.length; i<l; i++) {
	 				v += nodes[i].name + ",";
	 			}
	 			if (v.length > 0 ) v = v.substring(0, v.length-1);
	 			var cityObj = $("#citySel");
	 			cityObj.attr("value", v);
	 		}

	 		function showMenu() {
	 			var cityObj = $("#citySel");
	 			var cityOffset = $("#citySel").offset();
	 			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	 			$("body").bind("mousedown", onBodyDown);
	 		}
	 		function hideMenu() {
	 			$("#menuContent").fadeOut("fast");
	 			$("body").unbind("mousedown", onBodyDown);
	 		}
	 		function onBodyDown(event) {
	 			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
	 				hideMenu();
	 			}
	 		}

	</script>
	<ul>
		<li>&nbsp;&nbsp;<span>选择城市时，按下 Ctrl 或 Cmd 键可以进行多选</span></li>
		<li>&nbsp;&nbsp;城市：<input id="citySel" onclick="showMenu();" type="text" readonly value="" style="width:120px;"/></li>
	</ul>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
</body>
</html>