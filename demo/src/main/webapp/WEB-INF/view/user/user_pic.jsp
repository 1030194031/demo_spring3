<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Jcrop头像裁剪</title>
</head>
<body>

	<!--引入裁剪头像等控件-->
	<jsp:include page="/WEB-INF/view/user/pic_resource.jsp"></jsp:include>
	<!--引入裁剪头像等控件-->

	<table border="1">
		<tr>
			<td>
				<input id="fileupload" type="file" class="vam" />
				<input id="imgUrl" type="text" />
				<div id="fileQueue" class="mt10"></div>
			</td>
			<td>
				<a href="javascript:void(0)" onclick="save()">截取</a>
			</td>
		</tr>
		<tr>
			<td>
				<!--切图图片 开始-->
				<input id="quxiao" type="hidden" value="取消"/>
				<img id="target" src="${ctx}/static/js/Jcrop/demos/demo_files/demo.jpg" style="height: 500px;width: 500px;" alt="[Jcrop Example]" class="pictureWrap"/>
				<div>
					<label>X1 <input type="text" size="4" id="x1" name="x1" /></label>
					<label>Y1 <input type="text" size="4" id="y1" name="y1" /></label>
					<label>X2 <input type="text" size="4" id="x2" name="x2" /></label>
					<label>Y2 <input type="text" size="4" id="y2" name="y2" /></label>
					<label>W <input type="text" size="4" id="w" name="w" /></label>
					<label>H <input type="text" size="4" id="h" name="h" /></label>
				</div>
				<!--切图图片 结束-->
			</td>
			<td>
				<div class="preview-pane1">
					<div class="preview-container">
						<img src="${ctx}/static/js/Jcrop/demos/demo_files/demo.jpg" style="width: 240px;height: 240px;"  class="jcrop-preview" />
					</div>
				</div>
				<div>240px*240px</div>
			</td>
			<td>
				<div class="preview-pane2">
					<div class="preview-container">
						<img src="${ctx}/static/js/Jcrop/demos/demo_files/demo.jpg" style="width: 180px;height: 180px;"  class="jcrop-preview"/>
					</div>
				</div>
				<div>180px*180px</div>
			</td>
			<td>
				<div class="preview-pane3">
					<div class="preview-container">
						<img src="${ctx}/static/js/Jcrop/demos/demo_files/demo.jpg" style="width: 100px;height: 100px;" class="jcrop-preview"/>
					</div>
				</div>
				<div>100px*100px</div>
			</td>
		</tr>
	</table>
</body>
</html>