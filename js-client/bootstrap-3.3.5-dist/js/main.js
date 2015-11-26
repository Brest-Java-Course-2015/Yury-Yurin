// The root URL for the RESTful services
var APPLICATIONS_URL = "http://localhost:8080/rest/applications";
var MALFUNCTIONS_URL = "http://localhost:8080/rest/malfunctions/";
var MALFUNCTION_DELETE = "http://localhost:8080/rest/malfunction/delete/";
var MALFUNCTION_ADD = "http://localhost:8080/rest/malfunction";
var MALFUNCTION_UPDATE = "http://localhost:8080/rest/malfunction/update";
var APPLICATION_UPDATE = "http://localhost:8080/rest/application/update/";

getAllApplications();

// Register listeners
function deleteMalfunction(malfunctionId,applicationId) {
    console.log('deleteMalfunctionById');
    $.ajax({
        type: 'DELETE',
        url: MALFUNCTION_DELETE + malfunctionId,
        Origin: 'http://localhost:63342/usermanagement/js-client/',
        success: function(data) {
            alert('Malfunction succesfully deleted!');
            updateApplication(applicationId);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('delete: ' + textStatus);
        }
    });
}

    function getAllApplications() {
        console.log('getAllApplications');
        $.ajax({
            type: 'GET',
            url: APPLICATIONS_URL,
            Origin: 'http://localhost:63342/usermanagement/js-client/',
            dataType: 'json',// data type of response
            success: function (data) {
                if (data.length > 1) {
                    $('#applicationList tr').remove();
                    for (var i = 0; i < data.length; i++)
                        getAllMalfunctionsByApplicationId(data[i], data[i].applicationId);
                }
                else getAllMalfunctionsByApplicationId(data, data.applicationId);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('findAll: ' + textStatus);
            }
        });
    }

    function getAllMalfunctionsByApplicationId(dataApp, id) {
        console.log('findAll');
        $.ajax({
            type: 'GET',
            url: MALFUNCTIONS_URL + id.toString(),
            dataType: 'json',// data type of response
            success: function (data) {
                drawRow(dataApp, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('findAll: ' + textStatus);
            }
        });
    }

    function drawRow(dataApp, dataMal) {
        var row = $("<tr />");
        var rowForTh = $("<tr />");
        $("#applicationList").append(row);
        row.append($('<td id=\"applicationId' + dataApp.applicationId + '\">' + dataApp.applicationId + "</td>"));
        row.append($('<td id=\"createdDate' + dataApp.applicationId + '\">' + dataApp.createdDate + "</td>"));
        row.append($('<td id=\"updatedDate' + dataApp.applicationId + '\">' + dataApp.updatedDate + "</td>"));
        row.append($("<td></td>"));
        row.append($("<td>" + '<button onclick="drawFormFOrNewMalfunction(' + dataApp.applicationId + ')">Add</button>' + "</td>"));
        row.append($("<td></td>"));
        $("#applicationList").append(rowForTh);
        rowForTh.append($("<td></td>"));
        rowForTh.append($("<td><b>Наименование</b></td>"));
        rowForTh.append($("<td><b>Автомобиль</b></td>"));
        rowForTh.append($("<td><b>Описание</b></td>"));
        rowForTh.append($("<td><b>" + 'Стоимость' + "</b></td>"));
        rowForTh.append($("<td></td>"));
        for (var j = 0; j < dataMal.length; j++) {
            var rowM = $("<tr />");
            $("#applicationList").append(rowM);
            rowM.append($("<td id=\"malfunctionId" + dataMal[j].malfunctionId + "\"></td>"));
            rowM.append($('<td id=\"name' + dataMal[j].malfunctionId + '\">' + dataMal[j].name + "</td>"));
            rowM.append($('<td id=\"auto' + dataMal[j].malfunctionId + '\">' + dataMal[j].auto + "</td>"));
            rowM.append($('<td id=\"description' + dataMal[j].malfunctionId + '\">' + dataMal[j].description + "</td>"));
            //rowM.append($("<td>" +dataMal[j].cost + "</td>"));
            rowM.append($("<td></td>"));
            rowM.append($("<td>" + '<button onclick="deleteMalfunction('
            + dataMal[j].malfunctionId + ','
            +  dataApp.applicationId + ')">Delete</button>'
            + '  <button onclick="drawFormFOrUpdateMalfunction('
            + dataApp.applicationId + ','
            + dataMal[j].malfunctionId + ')">Update</button>' + "</td>"));
        }
    }

function drawFormFOrNewMalfunction(applicationId) {
    var rowName = $("<tr />");
    var rowAuto = $("<tr />");
    var rowDescription = $("<tr />");
    var rowButton = $("<tr />");
    var rowCost = $("<tr />");
    $("#addMalfunction").append(rowName);
    rowName.append($("<td>" + 'Наименование' + "</td>"));
    rowName.append($("<td>" + '<input id="name" type="text">' + "</td>"));
    $("#addMalfunction").append(rowAuto);
    rowAuto.append($("<td>" + 'Авто' + "</td>"));
    rowAuto.append($("<td>" + '<input id="auto" type="text">' + "</td>"));
    $("#addMalfunction").append(rowDescription);
    rowDescription.append($("<td>" + 'Описание' + "</td>"));
    rowDescription.append($("<td>" + '<input id="description" type="text">' + "</td>"));
    $("#addMalfunction").append(rowButton);
    rowButton.append($("<td></td>"));
    rowButton.append($("<td>" + '<button onclick="addMalfunction(' + applicationId +')">Add</button>'+"</td>"));
}

function drawFormFOrUpdateMalfunction(applicationId,malfunctionId) {
    var rowName = $("<tr />");
    var rowAuto = $("<tr />");
    var rowDescription = $("<tr />");
    var rowButton = $("<tr />");
    var rowCost = $("<tr />");
    $("#addMalfunction").append(rowName);
    rowName.append($("<td>" + 'Наименование' + "</td>"));
    rowName.append($("<td>" + '<input id="name" type="text" value=' + document.getElementById("name"+malfunctionId).innerText.toString() + '>' + "</td>"));
    $("#addMalfunction").append(rowAuto);
    rowAuto.append($("<td>" + 'Авто' + "</td>"));
    rowAuto.append($("<td>" + '<input id="auto" type="text" value=' + document.getElementById("auto"+malfunctionId).innerText.toString() + '>'+ "</td>"));
    $("#addMalfunction").append(rowDescription);
    rowDescription.append($("<td>" + 'Описание' + "</td>"));
    rowDescription.append($("<td>" + '<input id="description" type="text" value=' + document.getElementById("description"+malfunctionId).innerText.toString() + '>' + "</td>"));
    $("#addMalfunction").append(rowButton);
    rowButton.append($("<td></td>"));
    rowButton.append($("<td>" + '<button onclick="updateMalfunction('+ malfunctionId + ','+ applicationId +')">Update</button>'+
    '<button onclick="clearForm()">Cancel</button>' + "</td>"));
}

function clearForm() {
    $('#addMalfunction tr').remove();
}

function addMalfunction(applicationId) {
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: MALFUNCTION_ADD,
        dataType: "json",
        data: addMalfunctionFormToJSON(applicationId),
        success: function () {
            alert("Malfunction add success!");
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
        }
    });
    updateApplication(applicationId);
}

function updateApplication(applicationId) {
    console.log('updateApplication');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: APPLICATION_UPDATE + applicationId.toString(),
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

function updateMalfunction(malfunctionId,applicationId) {
    console.log('updateMalfunction');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: MALFUNCTION_UPDATE,
        data: updateMalfunctionFormToJSON(malfunctionId,applicationId),
        success: function () {
            updateApplication(applicationId);
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('addUser error: ' + textStatus);
        }
    });
}

function updateMalfunctionFormToJSON(malfunctionId,applicationId) {
    return JSON.stringify({
        "malfunctionId": malfunctionId,
        "name": $('#name').val(),
        "auto": $('#auto').val(),
        "description": $('#description').val()
    });
}

function addMalfunctionFormToJSON(id) {
        return JSON.stringify({
            "name": $('#name').val(),
            "auto": $('#auto').val(),
            "description": $('#description').val(),
            "applicationId": id
        });
    }

