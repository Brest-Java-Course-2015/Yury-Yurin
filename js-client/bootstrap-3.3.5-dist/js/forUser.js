// The root URL for the RESTful services
var URL_PREFIX = "http://localhost:8282/";
var APPLICATIONS_URL = URL_PREFIX + "applications";
var MALFUNCTIONS_URL = URL_PREFIX + "malfunctions";
var MALFUNCTION_DELETE = URL_PREFIX + "malfunction/delete/";
var MALFUNCTION_ADD = URL_PREFIX + "malfunction";
var MALFUNCTION_UPDATE = URL_PREFIX + "malfunction/update";
var APPLICATION_UPDATE = URL_PREFIX + "application/update/";
var APPLICATION_DELETE = URL_PREFIX + "application/delete/";
var APPLICATION_ADD = URL_PREFIX + "application";
var APPLICATIONS_BY_DATE = URL_PREFIX + "applications/byDate";
var MALFUNCTION_COST = URL_PREFIX + "malfunction/getCostsMalfunctions";
var APPLICATION_COST = URL_PREFIX + "malfunction/getCostsApplications"

getAllApplications();
// Register listeners

/*function setC(data) {
    for(var i=0;i<data.size;i++) {
        $("#costA${applicationsCosts.get(i).id}").val(${applicationsCosts.get(i).cost});
    }*/
function deleteMalfunction(malfunctionId,applicationId) {
    console.log('deleteMalfunctionById');
    $.ajax({
        type: 'DELETE',
        url: MALFUNCTION_DELETE + malfunctionId,
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
                $.each(newData, function(index,value) {
                    getAllMalfunctionsByApplicationId(value, value.applicationId);
                });
            }
        });
}

function all() {
    getCostsForMalfunctions();
    getCostApplications();
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
            if (newData == null) deleteApplication(dataApp.applicationId);
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
            row.append($("<td><output id=\"costApp" + dataApp.applicationId + '\"></td>'));
            row.append($("<td>" + '<button onclick="drawFormFOrNewMalfunction(' + dataApp.applicationId + ')">Add</button>' + "</td>"));
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
                rowM.append($("<td><output id=\"cost" + dataMal[j].malfunctionId + '\"></td>'));
                    //rowM.append($("<td></td>"));
                    rowM.append($("<td>" + '<button onclick="deleteMalfunction('
                    + dataMal[j].malfunctionId + ','
                    + dataApp.applicationId + ')">Delete</button>'
                    + '  <button onclick="drawFormFOrUpdateMalfunction('
                    + dataApp.applicationId + ','
                    + dataMal[j].malfunctionId + ')">Update</button>' + "</td>"));
        }
        all();
    }

function getCostsForMalfunctions() {
        console.log('get cost Malfunction ');
        $.ajax({
            type: 'GET',
            url: MALFUNCTION_COST,
            dataType: 'json',
            success: function (data) {
                for(var i=0;i<data.length;i++)
                {
                    if(data[i].cost != null)
                    $("#cost" + data[i].id).val(data[i].cost.toString());
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('getAllMalfunctionsById: ' + textStatus);
            }
        });
}

function getCostApplications() {
    console.log('get cost Application');
    $.ajax({
        type: 'GET',
        url: APPLICATION_COST,
        dataType: 'json',
        success: function (data) {
            for(var i=0;i<data.length;i++) {
                if(data[i].cost != null)
                $("#costApp" + data[i].id).val(data[i].cost.toString());
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('getAllMalfunctionsById: ' + textStatus);
        }
    });
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
    if(applicationId==null)
    rowButton.append($("<td>" + '<button onclick="createApplication(' + applicationId +')">Add</button>'+
    '<button onclick="clearForm()">Cancel</button>' + "</td>"));
    else rowButton.append($("<td>" + '<button onclick="addMalfunction(' + applicationId +')">Add</button>'+
    '<button onclick="clearForm()">Cancel</button>' + "</td>"));

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
        type: 'POST',
        contentType: 'application/json',
        url: MALFUNCTION_ADD,
        dataType: "json",
        data: addMalfunctionFormToJSON(applicationId),
        success: function () {
            alert("Malfunction add success!");
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addMalfunction error: ' + textStatus);
        }
    });
   if(applicationId!=null) updateApplication(applicationId);
}

function createApplication(applicationId) {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: APPLICATION_ADD,
        data: addApplicationFormToJSON(applicationId),
        success: function (data) {
            alert("Application created success");
            addMalfunction(data)
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('create application error: ' + textStatus);
        }
    });
}
function updateApplication(applicationId) {
    var time = Date.now();
    console.log('updateApplication');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
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

function updateMalfunction(malfunctionId,applicationId) {
    console.log('updateMalfunction');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: MALFUNCTION_UPDATE,
        data: updateMalfunctionFormToJSON(malfunctionId,applicationId),
        success: function () {
            updateApplication(applicationId);
            $('#addMalfunction tr').remove();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('Update malfunction error: ' + textStatus);
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

function addApplicationFormToJSON(id) {
    var date = Date.now();
    return JSON.stringify({
        "applicationId": null,
        "createdDate" : date,
        "updatedDate" : date
    });
}

