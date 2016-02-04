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
   <%-- <script>
        $('.clickk').click(function (event) {
            event.preventDefault();
            alert("sssssss");
        });
    </script>--%>
</div>



<div id="popupform">
    <output id="header" style="font-size: large"></output>
    <form id="form-feedback">
        <input type="text" placeholder="Название" name="name" id="name" class="input_text"/>
        <input type="text" placeholder="Авто" name="auto" id="auto" class="input_text"/>
        <input type="text" placeholder="Описание" name="description" id="description" class="input_text"/>
        <input id=btnSubmit type="submit" value="Добавить"/>
    </form>
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
            <c:forEach items="${malfunctions}" var="malfunction">
                <c:if test="${malfunction.applicationId == application.applicationId}">
                <tr id="malfunctionId${malfunction.malfunctionId}">
                    <td></td>
                    <td id="name${malfunction.malfunctionId}">${malfunction.name}</td>
                    <td id="auto${malfunction.malfunctionId}">${malfunction.auto}</td>
                    <td id="description${malfunction.malfunctionId}">${malfunction.description}</td>
                    <td><output id="costM${malfunction.malfunctionId}"></output></td>
                    <td>
                        <button onclick="deleteMalfunction(${malfunction.malfunctionId},${application.applicationId})">Delete</button>
                        <a href="#popupform" id="popupbutton${malfunction.malfunctionId}">
                        <button onclick="goToUpdateMalfunctionForm(${malfunction.malfunctionId},${malfunction.applicationId})">Update</button>
                            </a>
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
        document.getElementById("form-feedback").setAttribute("onsubmit","addMalfunction("+id.toString()+")");
        document.getElementById("btnSubmit").setAttribute("value","Добавить");
        $('#header').val("Добавление неисправности");
        $("#name").val('');
        $("#auto").val('');
        $("#description").val('');
        $("#form-feedback").submit(function (e) {
            e.preventDefault();
            return false;
        });
    }
    function addMalfunction(id) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '<c:url value="/applicationSubmit"/>',
            dataType: "json",
            data: addMalfunctionFormToJSON(id),
            success: function (dataMal) {
                for (var i = 0; i < dataMal.length; i++) {
                    $('#malfunctionId' + dataMal[i].malfunctionId).remove();
                }
                $.fancybox.close();
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
            var rowM = $('<tr id=malfunctionId' + dataMal[j].malfunctionId + '/>');
            $('#applicationList'+id).append(rowM);
            console.log(dataMal[j].malfunctionId);
            rowM.append($("<td id=\"malfunctionId" + dataMal[j].malfunctionId + "\"></td>"));
            rowM.append($('<td id=\"name' + dataMal[j].malfunctionId + '\">' + dataMal[j].name + "</td>"));
            rowM.append($('<td id=\"auto' + dataMal[j].malfunctionId + '\">' + dataMal[j].auto + "</td>"));
            rowM.append($('<td id=\"description' + dataMal[j].malfunctionId + '\">' + dataMal[j].description + "</td>"));
            rowM.append($("<td><output id=\"cost" + dataMal[j].malfunctionId + '\"></td>'));
            //rowM.append($("<td></td>"));
            rowM.append($("<td>" + '<button onclick="deleteMalfunction('
            + dataMal[j].malfunctionId + ','
            + dataMal[j].applicationId + ')">Delete</button>' +
            '<a href="#popupform" id="popupbutton' + dataMal[j].malfunctionId + '">'
            + '  <button onclick="goToUpdateMalfunctionForm('
            + dataMal[j].malfunctionId + ','
            + dataMal[j].applicationId + ')">Update</button></a>' + "</td>"));
        }
    }
    function deleteMalfunction(id1, id2) {
        window.location = '<c:url value="/deleteMalfunction"/>' + '?malId=' + id1.toString() + '&appId=' + id2.toString() + '&adminPage=' + false;
    }
    function goToUpdateMalfunctionForm(id1,id2) {
        $('#popupbutton'+id1.toString()).fancybox({
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
        document.getElementById("form-feedback").setAttribute("onsubmit","updateMalfunction("+id1.toString()+","+id2.toString() +")");
       $('#header').val("Обновление неисправности");
        $("#name").val(document.getElementById("name"+id1.toString()).textContent);
        $("#auto").val(document.getElementById("auto"+id1.toString()).textContent);
        $("#description").val(document.getElementById("description"+id1.toString()).textContent);
        document.getElementById("btnSubmit").setAttribute("value","Обновить");
        $("#form-feedback").submit(function (e) {
            e.preventDefault();
            return false;
        });
    }
    function updateMalfunction(id1,id2) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '<c:url value="/updateMalfunctionSubmit"/>',
            data: updateMalfunctionFormToJSON(id1,id2),
            dataType: "json",
            success: function (dataMal) {
                alert("Malfunction update success!");
                for (var i = 0; i < dataMal.length; i++) {
                    $('#malfunctionId' + dataMal[i].malfunctionId).remove();
                }
                $.fancybox.close();
                replaceRows(dataMal,id2);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    function updateMalfunctionFormToJSON(id1,id2) {
        var a = JSON.stringify({
            "malfunctionId": id1,
            "name": $('#name').val(),
            "auto": $('#auto').val(),
            "description": $('#description').val(),
            "applicationId": id2
        });
        console.log(a.toString());
        return a;
    }
    function deleteApplication(id) {
        window.location = '<c:url value="/deleteApplication"/> ' + '?id=' + id.toString() + '&adminPage=' + false;
    }
</script>
</body>
</html>