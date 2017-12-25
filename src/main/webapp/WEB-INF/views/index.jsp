<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<%@page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<html lang="en">
<head>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/tabulator.js" var="tabulatorJs" />
<spring:url value="/resources/js/jquery-3.2.1.min.js" var="jQuery" />
<spring:url value="/resources/js/jquery-ui.min.js" var="jQueryUI" />

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/tabulator_bootstrap.min.css"
	var="bootstrapTableCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${bootstrapTableCss}" rel="stylesheet" />
<title>Trade validator</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<b class="navbar-brand">Trade Validator</b>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				Panel Heading
				<div class="form-group">
					<label for="comment">Data:</label>
					<textarea class="form-control" rows="5" id="jsonData"></textarea>
				</div>
				<button id="validateButton" type="button" class="btn">Validate</button>
			</div>
			<div class="panel-body">
				Results:
				<table id="results" class="display" width="100%"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${jQuery}"></script>
	<script type="text/javascript" src="${jQueryUI}"></script>
	<script type="text/javascript" src="${tabulatorJs}"></script>
	<script type="text/javascript" src="${bootstrapJs}"></script>
	<script>
		$(function() {
			$("#validateButton").click(function() {
				$.ajax({
					url : "/validate",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : $("#jsonData")[0].value,
					complete : function(data) {
						if (data.status >= 200 && data.status < 300) {
							var tabledata = data.responseText;
							$("#results").tabulator("setData", tabledata);
						} else {
							alert("Invalid JSON");
						}
					}
				});
			});
			$('#results').tabulator({
				pagination : "local",
				paginationSize : 8,
				layout : "fitData",
				rowFormatter : function(row) {
					if (row.data().data.status == "Ok") {
						row.css({
							"background-color" : "#90EE90"
						});
					} else {
						row.css({
							"background-color" : "#FFA07A"
						});
					}
				},
				columns : [ {
					title : "Trade",
					field : "trade",
					width : "20%",
					sortable : false,
					height : "auto"
				}, {
					title : "Status",
					field : "status",
					width : "10%",
					sortable : false,
					height : "auto"
				}, {
					title : "Errors",
					field : "errors",
					width : "70%",
					sortable : false,
					height : "auto"
				} ]
			});
		});
	</script>
</body>
</html>