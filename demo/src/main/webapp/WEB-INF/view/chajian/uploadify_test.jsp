<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>上传插件v3.2测试</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/uploadify/uploadify.css" />
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.js"></script>
	<style type="text/css">
		#some_file_queue {
			background-color: #FFF;
			border-radius: 3px;
			box-shadow: 0 1px 3px rgba(0,0,0,0.25);
			height: 103px;
			margin-bottom: 10px;
			overflow: auto;
			padding: 5px 10px;
			width: 300px;
		}
	</style>
	<script type="text/javascript" src="${ctx}/static/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#file_upload').uploadify(uploadify_config);
		});

		//选择文件返回错误时触发该事件。每一个文件返回错误都会触发该事件。
		var uploadify_onSelectError = function(file, errorCode, errorMsg) {
			var msgText = "上传失败\n";
			switch (errorCode) {
				case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
					this.queueData.errorMsg = "每次最多上传 " + this.settings.uploadLimit + "个文件";
					msgText += "每次最多上传 " + this.settings.uploadLimit + "个文件";
					break;
				case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
					msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
					break;
				case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
					msgText += "文件大小为0";
					break;
				case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
					msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
					break;
				default:
					msgText += "错误代码：" + errorCode + "\n" + errorMsg;
			}
			alert(msgText);
		};

		//上传失败时触发该事件。
		var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {
			// 手工取消不弹出提示
			if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
					|| errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
				return;
			}
			var msgText = "上传失败\n";
			switch (errorCode) {
				case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
					msgText += "HTTP 错误\n" + errorMsg;
					break;
				case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
					msgText += "上传文件丢失，请重新上传";
					break;
				case SWFUpload.UPLOAD_ERROR.IO_ERROR:
					msgText += "IO错误";
					break;
				case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
					msgText += "安全性错误\n" + errorMsg;
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
					msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
					msgText += errorMsg;
					break;
				case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
					msgText += "找不到指定文件，请重新操作";
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
					msgText += "参数错误";
					break;
				default:
					msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"
							+ errorMsg + "\n" + errorString;
			}
			alert(errorCode);
			alert(msgText);
		}

		var uploadify_onUploadSuccess = function(file, data, response) {
			alert('文件：'+file.name + '\n\n上传状态:' + response + '\n\n返回值:' + data);
		};

		var uploadify_config = {
			'swf'      : '${ctx}/static/uploadify/uploadify.swf',	//指定上传控件的主体文件
			'uploader' : '${ctx}/front/uploadFile/file',	//指定服务器端上传处理文件
//				'formData' : {'someKey' : 'someValue', 'someOtherKey' : 1},   //后台传入的参数
			'auto'     : false,	//自动上传
			'debug'    : true,	//当其值为true时，开启SWFUpload调试模式
			'height'   : 30,
			'width'	: 105,
			'method'   : 'post',
			'multi'    : true,	//设置值为false时，一次只能选中一个文件
			'preventCaching' : true,	//如果设置为真，一个随机的值添加到SWF文件的URL，因此它不会缓存。
			'progressData' : 'percentage',	//设置显示在上传进度条中的数据类型，可选项时百分比（percentage）或速度（speed）。
			'buttonCursor' : 'hand',	//可选值为‘hand’(手形) 和 ‘arrow’(箭头)。
			'removeCompleted' : true,	//不设置该选项或者将其设置为false，将使上传队列中的项目始终显示于队列中，直到点击了关闭按钮或者队列被清空。
			'removeTimeout' : 3,	//设置上传完成后从上传队列中移除的时间（单位：秒）。
			'buttonText' : '浏&nbsp;&nbsp;览',
			'fileTypeDesc' : '图片',
			'fileTypeExts' : '*.jpg;',
			'uploadLimit' : 10,	//定义允许的最大上传数量。当达到或者超过该数值时，将触发 onUploadError事件。
//				'queueID'  : 'some_file_queue',	//自定义上传进度条显示位置时使用，queueID选项允许你设置一个拥有唯一ID的DOM元素来作为显示上传队列的容器。若为false，则queueID将动态生成。
			'queueSizeLimit' : 10,		//上传队列中一次可容纳的最大条数。该选项不限制上传文件数量。限制上传文件数量，使用uploadlimit选项。如果上传队列中的数量超过此限制，则触发onselecterror事件。
			'fileSizeLimit' : '100MB',	//如果是字符串类型，允许使用 (B, KB, MB, 或 GB) 为单位。默认使用KB为单位。值为0时，表示不限制上传文件大小
			'onFallback' : function() {alert('未检测到兼容版本的Flash.');},
			'overrideEvents' : [ 'onDialogClose', 'onUploadError','onSelectError', 'onUploadSuccess' ],	//当重写onDialogClose事件后，Uploadify的错误提示信息就都不会提示了。提示信息可直接自定义弹出。
			'onSelectError' : uploadify_onSelectError,
			'onUploadError' : uploadify_onUploadError,
			'onUploadSuccess' : uploadify_onUploadSuccess
		}
	</script>
<body>
	<div id="some_file_queue"></div>
	<input id="file_upload" name="file_upload" type="file" multiple="true">
	<a href="javascript:$('#file_upload').uploadify('cancel')">取消第一个上传文件</a> |
	<a href="javascript:$('#file_upload').uploadify('cancel', '*')">清空队列</a> |
	<a href="javascript:$('#file_upload').uploadify('upload', '*')">上传文件</a>
</body>
</html>