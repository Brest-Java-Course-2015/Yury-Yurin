<jsp:useBean id="applicationsCosts" scope="request" type="java.util.List"/>
<jsp:useBean id="malfunctionsCosts" scope="request" type="java.util.List"/>
<jsp:useBean id="malfunctions" scope="request" type="java.util.List"/>
<jsp:useBean id="applications" scope="request" type="java.util.List"/>
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
    <script src="resources/js/jquery.js"></script>
    <script type="text/javascript" src="resources/js/jquery.fancybox.pack.js"></script>
    <script type="text/javascript" src="resources/js/jquery.mousewheel-3.0.6.pack.js"></script>
    <link rel="stylesheet" href="resources/js/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
    <link rel="stylesheet" href="resources/css/popup.css" type="text/css" media="screen" />
    <script type="text/javascript">
        $(document).ready(function() {
            $(".fancybox").fancybox();
        });
    </script>
</head>
<header>
</header>
<body>
<h1>Список неисправностей</h1>
<div align="center">
    <input id="dateSetFrom" type="date">
    <input id="dateSetTo" type="date">
    <button id="btnSortDate" onclick="getApplicationsByDate()">Set</button>
    <a href="#popupform" id="popupbutton"><button onclick="goToAddMalfunctionForm(1)">Всплывающее окно</button></a>
   <div id="clickk">Нажми</div>
   <%-- <script>
        $('.clickk').click(function (event) {
            event.preventDefault();
            alert("sssssss");
        });
    </script>--%>
</div>



<div id="popupform">
    <h2>Обратная связь</h2>
    <div class="comment">Оставьте Ваши данные и мы свяжемся с Вами</div>
    <form id="form-feedback">
        <input type="text" placeholder="Название" name="name" id="name" class="input_text"/>
        <div id="bthrow_error_name"></div>
        <input type="text" placeholder="Авто" name="auto" id="auto" class="input_text"/>
        <div id="bthrow_error_auto"></div>
        <input type="text" placeholder="Описание" name="description" id="description" class="input_text"/>
        <div id="bthrow_error_description"></div>
        <input type="submit" value="Add"/>
        <div class="throw_error"></div>
    </form>
    <span class="under-form">Мы не занимаемся рассылкой рекламных сообщений, а так же не передаем контактные данные третьим лицам.</span>
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
            <th></th>
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
                <td><b>Стоимость</b></td>
                <td>
                    <a href="#popupform" id="popupbutton${application.applicationId}">
                    <button onclick="goToAddMalfunctionForm(${application.applicationId})">Add malfunction</button>
                        </a>
                    <button onclick="deleteApplication(${application.applicationId})">Delete</button></td>
            </tr>
            <tbody id="applicationList${application.applicationId}">
                <div id="malfunctionList${application.applicationId}">
            <c:forEach items="${malfunctions}" var="malfunction">
                <c:if test="${malfunction.applicationId == application.applicationId}">
                <tr id="malfunctionId${malfunction.malfunctionId}">
                    <td></td>
                    <td>${malfunction.name}</td>
                    <td>${malfunction.auto}</td>
                    <td>${malfunction.description}</td>
                    <td><output id="costM${malfunction.malfunctionId}"></output></td>
                    <td> <button onclick="updateMalfunction(${malfunction.malfunctionId})">Update</button>
                        <button onclick="deleteMalfunction(${malfunction.malfunctionId},${application.applicationId})">Delete</button>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
                </div>
            </tbody>
        </c:forEach>
    </table>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
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
    function goToAddMalfunctionForm(id) {
        $('#popupbutton'+id.toString()).fancybox({
            'padding': 37,
            'overlayOpacity': 0.87,
            'overlayColor': '#fff',
            'transitionIn': 'none',
            'transitionOut': 'none',
            'titlePosition': 'inside',
            'centerOnScroll': true,
            'maxWidth': 400,
            'minHeight': 310
        });
        document.getElementById("form-feedback").setAttribute("onsubmit","AddMalfunction("+id.toString()+")");
        $("#form-feedback").submit(function (e) {
            e.preventDefault();
            return false;
        });
    }
    function AddMalfunction(id) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '<c:url value="/applicationSubmit"/>',
            dataType: "json",
            data: addMalfunctionFormToJSON(id),
            success: function (dataMal) {
                $("#malfunctionList"+id.toString()).remove();
                var rowM = $('<tbody id="malfunctionList' + id.toString() + '"/>');
                $("#applicationList"+id.toString()).append(rowM);
                alert("Malfunction add success!");
                replaceRows(dataMal,id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addMalfunction error: ' + textStatus);
            }
        });
    }
    function addMalfunctionFormToJSON(id) {
        var a;
        if(id==null) a=null;
        else a=id.toString();
        return JSON.stringify({
            "name": $('#name').val(),
            "auto": $('#auto').val(),
            "description": $('#description').val(),
            "applicationId": a
        });
    }
    function replaceRows(dataMal,id) {
        for(var j=0;j<dataMal.length;j++) {
            var rowM = $("<tr />");
            $('#malfunctionList'+id).append(rowM);
            console.log(dataMal[j].malfunctionId);
            rowM.append($("<td id=\"malfunctionId" + dataMal[j].malfunctionId + "\"></td>"));
            rowM.append($('<td id=\"name' + dataMal[j].malfunctionId + '\">' + dataMal[j].name + "</td>"));
            rowM.append($('<td id=\"auto' + dataMal[j].malfunctionId + '\">' + dataMal[j].auto + "</td>"));
            rowM.append($('<td id=\"description' + dataMal[j].malfunctionId + '\">' + dataMal[j].description + "</td>"));
            rowM.append($("<td><output id=\"cost" + dataMal[j].malfunctionId + '\"></td>'));
            //rowM.append($("<td></td>"));
            rowM.append($("<td>" + '<button onclick="deleteMalfunction('
            + dataMal[j].malfunctionId + ','
            + dataMal[j].applicationId + ')">Delete</button>'
            + '  <button onclick="drawFormFOrUpdateMalfunction('
            + dataMal[j].applicationId + ','
            + dataMal[j].malfunctionId + ')">Update</button>' + "</td>"));
        }
    }
    function deleteMalfunction(id1, id2) {
        window.location = '<c:url value="/deleteMalfunction"/>' + '?malId=' + id1.toString() + '&appId=' + id2.toString() + '&adminPage=' + false;
    }
    function updateMalfunction(id) {
        window.location = '<c:url value="/updateMalfunction"/> ' + '?id=' + id.toString();
    }
    function deleteApplication(id) {
        window.location = '<c:url value="/deleteApplication"/> ' + '?id=' + id.toString() + '&adminPage=' + false;
    }
</script>
</body>
</html>