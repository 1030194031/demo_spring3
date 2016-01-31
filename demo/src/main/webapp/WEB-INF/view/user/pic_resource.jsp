<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<link rel="stylesheet" href="${ctx}/static/js/Jcrop/css/jquery.Jcrop.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/Jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="${ctx}/static/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<style type="text/css">

	/**第一个图片的样式**/
	.preview-pane1 {
		width: 240px;
		height: 240px;
		overflow: hidden;
	}
	/**第二个图片的样式**/
	.preview-pane2  {
		width: 180px;
		height: 180px;
		overflow: hidden;
	}
	/**第三个图片的样式**/
	.preview-pane3  {
		width: 100px;
		height: 100px;
		overflow: hidden;
	}
</style>

<script type="text/javascript">
	$(function(){
		//裁剪控件加载
//		jcorpLoad();
		//上传控件加载
		uploadPicLoad('fileupload','imgUrl','target','fileQueue');
	});

	/**
	 * 裁剪控件
	 **/
	function jcorpLoad(){
		jQuery(function($){
			var jcrop_api,
					boundx,
					boundy,

					//第一个
					$pcnt1 = $('.preview-pane1'),
					$pimg1 = $('.preview-pane1 .preview-container img'),
					xsize1 = $pcnt1.width(),
					ysize1 = $pcnt1.height(),

					//第二个
					$pcnt2 = $('.preview-pane2'),
					$pimg2 = $('.preview-pane2 .preview-container img'),
					xsize2 = $pcnt2.width(),
					ysize2 = $pcnt2.height(),

					//第三个
					$pcnt3 = $('.preview-pane3'),
					$pimg3 = $('.preview-pane3 .preview-container img'),
					xsize3 = $pcnt3.width(),
					ysize3 = $pcnt3.height();

			$('#target').Jcrop({
				bgFade:true,//是否使用背景过渡效果
				allowSelect:false,//允许新选框
				bgOpacity:.5,//背景透明度
				addClass:null,//添加样式。假设class名为 "test"，则添加样式后为class="test jcrop-holder"
				bgColor: 'white',//背景颜色。颜色关键字、HEX、RGB 均可。
				minSize: [200,200],//选框最小尺寸
				keySupport:true,//支持键盘控制。按键列表：上下左右（移动）、Esc（取消）、Tab（跳出裁剪框，到下一个）
				onChange: updatePreview,//选框改变时的事件
				onSelect: updatePreview,//选框选定时的事件
				onRelease:null,//取消选框时的事件
				aspectRatio: xsize1 / ysize1,//选框宽高比。说明：width/height
				aspectRatio: xsize2 / ysize2,
				aspectRatio: xsize3 / ysize3
			},function(){
				var bounds = this.getBounds();
				boundx = bounds[0];
				boundy = bounds[1];
				jcrop_api = this;
				jcrop_api.animateTo([80,50,80,20]);
			});

			function updatePreview(c){
				if (parseInt(c.w) > 0){
					//第一个
					var rx1 = xsize1 / c.w;
					var ry1 = ysize1 / c.h;

					//第二个
					var rx2 = xsize2 / c.w;
					var ry2 = ysize2 / c.h;

					//第三个
					var rx3 = xsize3 / c.w;
					var ry3 = ysize3 / c.h;

					$('#x1').val(c.x);
					$('#y1').val(c.y);
					$('#x2').val(c.x2);
					$('#y2').val(c.y2);
					$('#w').val(c.w);
					$('#h').val(c.h);

					//第一个
					$pimg1.css({
						width: Math.round(rx1 * boundx) + 'px',
						height: Math.round(ry1 * boundy) + 'px',
						marginLeft: '-' + Math.round(rx1 * c.x) + 'px',
						marginTop: '-' + Math.round(ry1 * c.y) + 'px'
					});

					//第二个
					$pimg2.css({
						width: Math.round(rx2 * boundx) + 'px',
						height: Math.round(ry2 * boundy) + 'px',
						marginLeft: '-' + Math.round(rx2 * c.x) + 'px',
						marginTop: '-' + Math.round(ry2 * c.y) + 'px'
					});

					//第三个
					$pimg3.css({
						width: Math.round(rx3 * boundx) + 'px',
						height: Math.round(ry3 * boundy) + 'px',
						marginLeft: '-' + Math.round(rx3 * c.x) + 'px',
						marginTop: '-' + Math.round(ry3 * c.y) + 'px'
					});
				}
			};
			//停用裁剪插件
			$('#quxiao').click(function(e) {
				jcrop_api.destroy();
				return false;
			});
		});
	}

	/**
	 * 上传控件加载
	 * @param fileupload
	 * @param ids
	 * @param showId
	 * @param fileQueue
	 */
	function uploadPicLoad(fileupload,ids,showId,fileQueue){
		$("#"+fileupload).uploadify({
			'uploader' : '${ctx}/static/js/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
			'script'  :'${ctx}/front/uploadFile/img',
			'queueID' : fileQueue, //文件队列ID
			'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
			'auto' : true, //选定文件后是否自动上传
			'multi' :false, //是否允许同时上传多文件
			'hideButton' : false,//上传按钮的隐藏
			'buttonText' : 'Browse',//默认按钮的名字
			'buttonImg' : '${ctx}/static/js/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
			'width' : 105,
			'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
			'sizeLimit' : 51200000,//控制上传文件的大小
			'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//出现在上传对话框中的文件类型描述
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//支持的格式，启用本项时需同时声明fileDesc
			'folder' : '/upload',//您想将文件保存到的路径
			'cancelImg' : '${ctx}/static/js/uploadify/cancel.png',
			onSelect : function(event, queueID,fileObj) {
				fileuploadIndex = 1;
				$("#"+fileQueue).html("");
				if (fileObj.size > 51200000) {
					alert('文件太大最大限制51200kb');
					return false;
				}
			},
			onComplete : function(event,queueID, fileObj, response,data) {
				$("#"+ids).val(response);
				$("#"+showId).attr('src','${ctx}'+response);
				$(".jcrop-preview").attr('src','${ctx}'+response);
				$(".pictureWrap").attr('src','${ctx}'+response);

				var img=new Image();
				img.src='${ctx}'+response;
				img.onload=function() {
					var realHeight = img.height;
					var realWidth = img.width;
					if (realHeight > realWidth) {
						var height = 300;
						var width = height * realWidth / realHeight;
						$("#"+showId).attr("height", height);
						$("#"+showId).attr("width", width);
					} else {
						var width = 300;
						var height = width * realHeight / realWidth;
						$("#"+showId).attr("height", height);
						$("#"+showId).attr("width", width);
					}
				}
				//停用上一个裁剪插件
				$("#quxiao").click();
				//重新加载新的裁剪插件
				jcorpLoad();
			},
			onError : function(event, queueID, fileObj,errorObj) {
				$("#"+fileQueue).html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
			}
		});
	}

	/**
	 * 裁剪保存图片
	 */
	function save(){
		var imgsUrl=$("#imgUrl").val();
		if(imgsUrl==null || imgsUrl==''){
			alert('请上传需要裁剪的图片');
			return ;
		}
		$.ajax({
			url:"${ctx}/front/uploadFile/picImg",
			data:{
				"path":imgsUrl,
				"x":$("#x1").val(),
				"y":$("#y1").val(),
				"w":$("#w").val(),
				"h":$("#h").val()
			},
			type:"post",
			dataType:"json",
			cache : false,
			async:false,
			success:function(result){
				//停用裁剪控件
				$("#quxiao").click();
				$("#target").attr('src','${ctx}'+result.entity);
			}
		});
	}
</script>