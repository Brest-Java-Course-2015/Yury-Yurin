// The root URL for the RESTful services
var APPLICATIONS_URL = "http://localhost:8080/rest/applications";
var MALFUNCTIONS_URL = "http://localhost:8080/rest/malfunctions/";
var MALFUNCTION_DELETE = "http://localhost:8080/rest/malfunction/delete/";
var MALFUNCTION_ADD = "http://localhost:8080/rest/malfunction";

getAllApplications();

// Register listeners
function deleteMalfunction(malfunctionId) {
    console.log('deleteMalfunctionById');
    $.ajax({
        type: 'DELETE',
        url: MALFUNCTION_DELETE + malfunctionId,
        Origin: 'http://localhost:63342/usermanagement/js-client/',
        success: alert('Malfunction succesfully deleted!'),
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('delete: ' + textStatus);
        }
    })
    getAllApplications();
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
            Origin: 'http://localhost:63342/usermanagement/js-client/',
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
        row.append($("<td>" + dataApp.applicationId + "</td>"));
        row.append($("<td>" + dataApp.createdDate + "</td>"));
        row.append($("<td>" + dataApp.updatedDate + "</td>"));
        row.append($("<td></td>"));
        row.append($("<td>" + '<button onclick="drawFormFOrNewMalfunction(' + dataApp.applicationId + ')">Add</button>' + "</td>"));
        row.append($("<td></td>"));
        $("#applicationList").append(rowForTh);
        rowForTh.append($("<td></td>"));
        rowForTh.append($("<td><b>Наименование</b></td>"));
        rowForTh.append($("<td><b>Автомобиль</b></td>"));
        rowForTh.append($("<td><b>Описание</b></td>"));
        rowForTh.append($("<td></td>"));
        rowForTh.append($("<td></td>"));
        for (var j = 0; j < dataMal.length; j++) {
            var rowM = $("<tr />");
            $("#applicationList").append(rowM);
            rowM.append($("<td></td>"));
            rowM.append($("<td>" + dataMal[j].name + "</td>"));
            rowM.append($("<td>" + dataMal[j].auto + "</td>"));
            rowM.append($("<td>" + dataMal[j].description + "</td>"));
            rowM.append($("<td id=\"ss\" />"));
            rowM.append($("<td>" + '<button onclick="deleteMalfunction('+ dataMal[j].malfunctionId +')">Delete</button>'+"</td>"));
        }
    }

function drawFormFOrNewMalfunction(id) {
    var rowName = $("<tr />");
    var rowAuto = $("<tr />");
    var rowDescription = $("<tr />");
    var rowButton = $("<tr />");
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
    rowButton.append($("<td>" + '<button onclick="addMalfunction(' + id +')">Add</button>'+"</td>"))
}

function addMalfunction(id) {
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: MALFUNCTION_ADD,
        dataType: "json",
        data: formToJSON(id),
        success: function (data, textStatus, jqXHR) {
            getAllApplications();
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
        }
    });
}

function formToJSON(id) {
    return JSON.stringify({
        "name": $('#name').val(),
        "auto": $('#auto').val(),
        "description": $('#description').val(),
        "applicationId": id
    });
}
