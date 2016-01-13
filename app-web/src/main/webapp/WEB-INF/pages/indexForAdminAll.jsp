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
</div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Номер заявки</th>
            <th>Дата создания</th>
            <th>Дата обновления</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${applications}" var="application">
            <tr>
                <td>${application.applicationId}</td>
                <td>${application.createdDate}</td>
                <td>${application.updatedDate}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td><b>Название</b></td>
                <td><b>Автомобиль</b></td>
                <td><b>Описание</b></td>
                <td><b>Стоимость</b></td>
                <td><button onclick="deleteApplication(${application.applicationId})">Delete</button></td>
            </tr>
            <c:forEach items="${malfunctions}" var="malfunction">
                <c:if test="${malfunction.applicationId == application.applicationId}">
                    <tr id="malfunctionId${malfunction.malfunctionId}">
                        <td></td>
                        <td>${malfunction.name}</td>
                        <td>${malfunction.auto}</td>
                        <td>${malfunction.description}</td>
                        <td><input id="costRepair${malfunction.malfunctionId}"
                                   type="number" value="${malfunction.costRepair}"
                                   style="width: 100px;">
                            <input id="costService${malfunction.malfunctionId}"
                                   type="number" value="${malfunction.costService}"
                                   style="width: 100px;">
                            <input id="additionalExpenses${malfunction.malfunctionId}"
                                   type="number" value="${malfunction.additionalExpenses}"
                                   style="width: 100px;"></td>
                        <td> <button onclick="setCostsForMalfunction(${malfunction.malfunctionId},${malfunction.applicationId})">Set costs</button>
                            <button onclick="deleteMalfunction(${malfunction.malfunctionId},${application.applicationId})">Delete</button>
                        </td>
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
        window.location = '<c:url value="/adminApplicationsByDate"/> ' + '?dateMin=' + dateMin + '&dateMax=' + dateMax;
    }
    function deleteMalfunction(id1, id2) {
        window.location = '<c:url value="/deleteMalfunction"/>' + '?malId=' + id1.toString() + '&appId=' + id2.toString() + '&adminPage=' + true;
    }
    function deleteApplication(id) {
        window.location = '<c:url value="/deleteApplication"/> ' + '?id=' + id.toString() + '&adminPage=' + true;
    }
    function setCostsForMalfunction(malfunctionId,applicationId) {
        if(checkFields(malfunctionId)==true) {
            var str = '<c:url value="/setCosts"/>' + '?id=' + malfunctionId +
                    '&costRepair=' + $("#costRepair" + malfunctionId).val() +
                    '&costService=' + $("#costService" + malfunctionId).val() +
                    '&additionalExpenses=' + $("#additionalExpenses" + malfunctionId).val() +
                    '&applicationId=' + applicationId;
            console.log('updateApplication');
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: str,
                success: function () {
                    alert('All costs was set!');
                    window.location = '<c:url value="/adminApplications"/>';
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('Set costs error: ' + textStatus);
                }
            });
        }
        else alert("Please, set all costs!");
    }
    function checkFields(id) {
        var i=0;
        if(($("#costRepair"+id).val()=="")) i++;
        if(($("#costService"+id).val()=="")) i++;
        if(($("#additionalExpenses"+id).val()=="")) i++;
        if(i==0) return true;
        return false;
    }
</script>
</body>
</html>