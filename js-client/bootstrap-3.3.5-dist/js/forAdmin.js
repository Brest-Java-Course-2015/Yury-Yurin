// The root URL for the RESTful services
var URL_PREFIX = "http://localhost:8282/";
var APPLICATIONS_URL = URL_PREFIX + "applications";
var MALFUNCTIONS_URL = URL_PREFIX + "malfunctions";
var MALFUNCTION_DELETE = URL_PREFIX + "malfunction/delete";
var MALFUNCTION_ADD = URL_PREFIX + "malfunction";
var MALFUNCTION_UPDATE = URL_PREFIX + "malfunction/update";
var APPLICATION_UPDATE = URL_PREFIX + "application/update";
var APPLICATION_DELETE = URL_PREFIX + "application/delete/";
var APPLICATION_ADD = URL_PREFIX + "application";
var APPLICATIONS_BY_DATE = URL_PREFIX + "applications/byDate";
var ADD_COSTS_TO_MALFUNCTIONS = URL_PREFIX + "malfunction/setCosts";
getAllApplications();
// Register listeners

function deleteMalfunction(malfunctionId,applicationId) {
    console.log('deleteMalfunctionById');
    $.ajax({
        type: 'GET',
        url: MALFUNCTION_DELETE + '?id=' + malfunctionId,
        success: function() {
            alert('Malfunction successfully deleted!');
            updateApplication(applicationId);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('delete: ' + textStatus);
        }
    });
}

function getAllApplications() {
    var newData;
    var a = $("#dateSetFrom").val();
    var dateFrom = Date.parse(a);
    var b = $("#dateSetTo").val();
    var dateTo = Date.parse(b);
    var str;
    if(a=="" || b=="")  str = APPLICATIONS_URL;
    else str = APPLICATIONS_BY_DATE + '?minDateTime=' + dateFrom + '&maxDateTime=' + dateTo;
    console.log('get all applications');
    $.ajax({
        type: 'GET',
        url: str,
        dataType: 'json',// data type of response
        success: function (data) {
            newData = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('get All applications: ' + textStatus);
        },
        complete: function() {
            $('#applicationList tr').remove();
            for (var i = 0; i < newData.length; i++)
                getAllMalfunctionsByApplicationId(newData[i], newData[i].applicationId);
        }
    });
}
function clearDate() {
    $("#dateSetFrom").val('');
    $("#dateSetTo").val('');
    getAllApplications();
}

function getAllMalfunctionsByApplicationId(dataApp, id) {
    var newData;
    console.log('get all malfunctions by id applications ');
    $.ajax({
        type: 'GET',
        url: MALFUNCTIONS_URL + '?id=' + id.toString(),
        dataType: 'json',// data type of response
        success: function (data) {
            newData = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('getAllMalfunctionsById: ' + textStatus);
        },
        complete: function() {
            if (newData.length==0) deleteApplication(dataApp.applicationId);
            drawRow(dataApp,newData);
        }
    });
}

function deleteApplication(applicationId) {
    console.log('deleteApplication');
    $.ajax({
        type: 'DELETE',
        url: APPLICATION_DELETE + applicationId,
        success: function() {
            alert("List of malfunction by id=" + applicationId + " is empty, application deleted!")
            getAllApplications();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('deleteApplication: ' + textStatus);
        }
    });
}

function drawRow(dataApp,dataMal) {
    var row = $("<tr />");
    var rowForTh = $("<tr />");
    $("#applicationList").append(row);
    row.append($('<td id=\"applicationId' + dataApp.applicationId + '\">' + dataApp.applicationId + "</td>"));
    row.append($('<td id=\"createdDate' + dataApp.applicationId + '\">' + dataApp.createdDate + "</td>"));
    row.append($('<td id=\"updatedDate' + dataApp.applicationId + '\">' + dataApp.updatedDate + "</td>"));
    row.append($("<td></td>"));
    row.append($("<td></td>"));
    row.append($("<td>" + '<button onclick="deleteApplication(' + dataApp.applicationId + ')">Delete</button>' + "</td>"))
    $("#applicationList").append(rowForTh);
    rowForTh.append($("<td></td>"));
    rowForTh.append($("<td><b>Наименование</b></td>"));
    rowForTh.append($("<td><b>Автомобиль</b></td>"));
    rowForTh.append($("<td><b>Описание</b></td>"));
    rowForTh.append($("<td><b>Стоимость</b></td>"));
    rowForTh.append($("<td></td>"));
    for (var j = 0; j < dataMal.length; j++) {
        var rowM = $("<tr />");
        $("#applicationList").append(rowM);
        rowM.append($("<td id=\"malfunctionId" + dataMal[j].malfunctionId + "\"></td>"));
        rowM.append($('<td id=\"name' + dataMal[j].malfunctionId + '\">' + dataMal[j].name + "</td>"));
        rowM.append($('<td id=\"auto' + dataMal[j].malfunctionId + '\">' + dataMal[j].auto + "</td>"));
        rowM.append($('<td id=\"description' + dataMal[j].malfunctionId + '\">' + dataMal[j].description + "</td>"));
        rowM.append($('<td><input id=\"costRepair' + dataMal[j].malfunctionId +'\" type="number" style="width: 100px;">' +
        '<input id=\"costService' + dataMal[j].malfunctionId +'\" type="number" style="width: 100px;">' +
        '<input id=\"additionalExpenses' + dataMal[j].malfunctionId +'\" type="number" style="width: 100px;">' + '</td>'));
        rowM.append($("<td>" + '<button onclick="deleteMalfunction('
        + dataMal[j].malfunctionId + ','
        + dataApp.applicationId + ')">Delete</button>'
        + '  <button onclick="addCosts('
        + dataMal[j].malfunctionId + ',' + dataMal[j].applicationId + ')">Set costs</button>' + "</td>"));
       setCosts(dataMal[j]);
       // $.("#costService"+dataMal.malfunctionId).val(dataMal.costService);
       // $.("#additionalExpenses"+dataMal.malfunctionId).val(dataMal.additionalExpenses);
    }
}

function setCosts(dataMal) {
    document.getElementById('costRepair'+dataMal.malfunctionId).setAttribute('value',dataMal.costRepair);
    document.getElementById('costService'+dataMal.malfunctionId).setAttribute('value',dataMal.costService);
    document.getElementById('additionalExpenses'+dataMal.malfunctionId).setAttribute('value',dataMal.additionalExpenses);
}


function updateApplication(applicationId) {
    var time = Date.now();
    console.log('updateApplication');
    $.ajax({
        type: 'POST',
        url: APPLICATION_UPDATE + '?id=' + applicationId.toString() + '&time=' + time ,
        success: function () {
            alert("Application update success!");
            getAllApplications();
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Application update error: ' + textStatus);
        }
    });
}

function addCosts(malfunctionId,applicationId) {
    if(checkFields(malfunctionId)==true) {
        var str = ADD_COSTS_TO_MALFUNCTIONS + '?id=' + malfunctionId +
            '&costRepair=' + $("#costRepair" + malfunctionId).val() +
            '&costService=' + $("#costService" + malfunctionId).val() +
            '&additionalExpenses=' + $("#additionalExpenses" + malfunctionId).val();
        console.log('updateApplication');
        $.ajax({
            type: 'POST',
            url: str,
            success: function () {
                updateApplication(applicationId);
                getAllApplications();
                $('#addMalfunction tr').remove();
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
