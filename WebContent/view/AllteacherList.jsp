<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>管理员列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'全校老师填报列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"SchoolOfficeServlet?method=AllteacherList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        remoteSort: false,
	        columns: [[  
	        	{field:'id',title:'学号',width:50, sortable: true},    
 		        {field:'name',title:'姓名',width:50, sortable: true},    
 		        {field:'college',title:'学院',width:60},
 		        {field:'date',title:'填报日期',width:120, sortable: true},
 		        {field:'place',title:'现所在地',width:80, sortable: true},
 		        {field:'wuhan',title:'武汉籍',width:50, sortable: true},
 		        {field:'hubei',title:'湖北籍',width:50, sortable: true},
 		        {field:'wuhancontact',title:'武汉接触史',width:50, sortable: true},
 		  	    {field:'hubeicontact',title:'湖北接触史',width:50, sortable: true},
 		        {field:'suspected',title:'疑似',width:50, sortable: true},
 		        {field:'confirm',title:'是否确诊',width:200},
	 		]], 
	        toolbar: "#toolbar"
	    }); 
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	 }); 
	
	//搜索按钮监听
  	$("#search").click(function(){
  		$('#dataList').datagrid('load',{
  			college:$("#search-studentcollege").combobox('getValue'),
  		
  			confirm:$("#search-confirm").combobox('getValue')
  		})
  		});
  
  	
	</script>
</head>
<body>
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table>
	<table id="dataList2" cellspacing="0" cellpadding="0"> 
	    
	</table>  
	<!-- 工具栏 -->
	<div id="toolbar">
	</form>
</body>
</html>