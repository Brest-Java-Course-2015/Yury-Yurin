<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<header>
</header>
<body>
<h1>Обновление неисправности</h1>

<table align="center">
    <tbody id="addMalfunction">
    <tr>
        <td>Name</td>
        <td><input id="name" type="text"></td>
    </tr>
    <tr>
        <td>Auto</td>
        <td><input id="auto" type="text"></td>
    </tr>
    <tr>
        <td>Description</td>
        <td><input id="description" type="text"></td>
    </tr>
    <tr>
        <td><button onclick="goBack()">Cancel</button></td>
        <td><button onclick="updateMalfunction()">Update</button></td>
    </tr>
    </tbody>
</table>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>

<script>
    $("#name").val('${malfunction.name}');
    $("#auto").val('${malfunction.auto}');
    $("#description").val('${malfunction.description}');
    function updateMalfunction() {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '<c:url value="/updateMalfunctionSubmit"/>',
            data: updateMalfunctionFormToJSON(),
            success: function () {
                alert("Malfunction update success!");
                window.location = '<c:url value="/applications"/> '
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }

    function updateMalfunctionFormToJSON() {
        var a = JSON.stringify({
            "malfunctionId": '${malfunction.malfunctionId}',
            "name": $('#name').val(),
            "auto": $('#auto').val(),
            "description": $('#description').val(),
            "applicationId": '${malfunction.applicationId}'
        });
        console.log(a.toString());
        return a;
    }
    function goBack() {
        window.location = '<c:url value="/applications"/>';
    }
</script>
</body>
</html>