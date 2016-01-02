<jsp:useBean id="applicationsCosts" scope="request" type="java.util.List"/>
<jsp:useBean id="malfunctionsCosts" scope="request" type="java.util.List"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<header>
</header>
<body>
<h1>Список неисправностей</h1>
<div align="center">
    <input id="dateSetFrom" type="date">
    <input id="dateSetTo" type="date">
    <button id="btnSortDate" onclick="getApplicationsByDate()">Set</button>
    <button id="btnClear" onclick="clearDate()">Clear</button>
</div>
<div id="addApp" align="right">
    <button id="btnAddApp" onclick="goToAddApplication()">Add application</button>
</div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Номер заявки</th>
            <th>Дата создания</th>
            <th>Дата обновления</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${applications}" var="application">
            <tr>
                <td>${application.applicationId}</td>
                <td>${application.createdDate}</td>
                <td>${application.updatedDate}</td>
                <td><output id="costA${application.applicationId}"></output></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td><b>Название</b></td>
                <td><b>Автомобиль</b></td>
                <td><b>Описание</b></td>
                <td>Стоимость</td>
                <td><button onclick="goToAddMalfunction(${application.applicationId})">Добавить</button></td>
            </tr>
            <c:forEach items="${malfunctions}" var="malfunction">
                <c:if test="${malfunction.applicationId == application.applicationId}">
                <tr id="malfunctionId${malfunction.malfunctionId}">
                    <td></td>
                    <td>${malfunction.name}</td>
                    <td>${malfunction.auto}</td>
                    <td>${malfunction.description}</td>
                    <td><output id="costM${malfunction.malfunctionId}"></output></td>
                    <td></td>
                </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>

<c:forEach items="${applicationsCosts}" var="cost">
    <c:set var="costItem" value="${cost}"/>
    <script>
        $("#costA${costItem.id}").val("${costItem.cost == null ? 0 : costItem.cost}");
    </script>
</c:forEach>

<c:forEach items="${malfunctionsCosts}" var="cost">
    <c:set var="costItem" value="${cost}"/>
    <script>
        $("#costM${costItem.id}").val("${costItem.cost == null ? 0 : costItem.cost}");
    </script>
</c:forEach>

<script>
   function getApplicationsByDate() {
       var dateMin = Date.parse($('#dateSetFrom').val());
       var dateMax = Date.parse($('#dateSetTo').val());
       window.location = '<c:url value="/applicationsByDate"/> ' + '?dateMin=' + dateMin + '&dateMax=' + dateMax;
   }
    function goToAddApplication() {
        window.location = '<c:url value="/application"/>' + '?id=';
    }
    function goToAddMalfunction(id) {
        window.location = '<c:url value="/application"/>' + '?id=' + id.toString();
    }
</script>
</body>
</html>