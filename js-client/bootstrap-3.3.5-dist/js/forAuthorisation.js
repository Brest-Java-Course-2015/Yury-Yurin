// The root URL for the RESTful services
getAllUsers();
var URL_PREFIX = 'http://localhost:8282/';
var GET_ALL_USERS = URL_PREFIX + 'users';

function drawData(data) {
    for(var i=0;i<data.length;i++) {
        var row = $("<tr />");
        $("#applicationList").append(row);
        row.append($('<td>' + data[i].login + "</td>"));
        row.append($('<td>' + data[i].hash + "</td>"));
        row.append($('<td>' + data[i].salt + "</td>"));
        row.append($('<td>' + data[i].n + "</td>"));
    }
}
function getAllUsers() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8282/users',
        dataType: 'json',
        success: function (data) {
            drawData(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('delete: ' + textStatus);
        }
    });
}







