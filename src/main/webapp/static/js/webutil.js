var ajaxUrl;
var ajaxparameters;
var ajaxcallBack;
function ajaxPage(url,parameters,pageNum,callBack){
	ajaxUrl = url;
	ajaxparameters = parameters;
	ajaxcallBack=callBack;
	parameters='page.currentPage='+pageNum+''+parameters;
	$.ajax({
		type : "POST",
		dataType : "text",
		url:url,
		data : parameters,
		cache : true,
		async : false,
		success : callBack
	});
}
//点击分页
function goPageAjax(pageNum){
	ajaxPage(ajaxUrl,ajaxparameters,pageNum,ajaxcallBack);
}