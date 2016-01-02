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
<h1>Добавление неисправности</h1>

<table align="center">
    <tbody id="addMalfunction">
    <tr>
        <td>Название</td>
        <td><input id="name" type="text"></td>
    </tr>
    <tr>
        <td>Авто</td>
        <td><input id="auto" type="text"></td>
    </tr>
    <tr>
        <td>Описание</td>
        <td><input id="description" type="text"></td>
    </tr>
    <tr>
        <td><button onclick="goBack()">Отмена</button></td>
        <td><button onclick="addMalfunction()">Добавить</button></td>
    </tr>
    </tbody>
</table>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>

<script>
    function addMalfunction() {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '<c:url value="/malfunctionSubmit"/>',
            dataType: "json",
            data: addMalfunctionFormToJSON(${id}),
            success: function () {
                alert("Malfunction add success!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addMalfunction error: ' + textStatus);
            }
        });
        if(${id!=null}) updateApplication(${id});
    }

    function updateApplication(id) {
        var time = Date.now();
        console.log('updateApplication');
        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: '<c:url value="/applicationUpdate"/>' + '?id=' + id.toString() + '&time=' + time.toString() ,
            success: function () {
                alert("Application update success!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('Application update error: ' + textStatus);
            }
        });
    }
    function addMalfunctionFormToJSON(id) {
        var a = '${id}';
        return JSON.stringify({
            "name": $('#name').val(),
            "auto": $('#auto').val(),
            "description": $('#description').val(),
            "applicationId": a
        });
    }
    function goBack() {
        window.location = '<c:url value="/applications"/>';
    }
</script>
</body>
</html>