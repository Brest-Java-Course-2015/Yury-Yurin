// The root URL for the RESTful services
var USER_URL = "http://localhost:8080/rest/user";
var USERS_DTO_URL = "http://localhost:8080/rest/userdto";

findAll();

// Register listeners
$('#btnSave').click(function () {
    if ($('#userId').val() == '')
        addUser();
    else
        updateUser();
    return false;
});

$('#btnDel').click(function () {
   deleteUser(user);
});

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: USERS_DTO_URL,
        Origin : 'http://localhost:63342/usermanagement/javascript-client/',
        dataType: "json",// data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(user) {
    var row = $("<tr />")
    $("#userList").append(row);
    row.append($("<td>" + '<a href="#" data-identity="' + user.userId + '">' + user.login + '</a></td>'));
    row.append($("<td>" + user.createdDate + "</td>"));
    row.append($('<td id="'+user.login + '">' + '<button id=' + '"btnDel">' +'Delete' + "</button>" + "</td>"));
}

function renderList(data) {
    var dto = data.users == null ? [] : (data.users instanceof Array ? data.users : [data.users]);
    $('#userList tr').remove();
    $.each(dto, function (index, user) {
        drawRow(user);
    });
}

function addUser() {
    console.log('addUser');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: USER_URL + "/" +$('#login').val() + "/" + $('#password').val(),
        Origin : 'http://localhost:63342/usermanagement/javascript-client/',
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User created successfully');
            $('#userId').val(data.userId);
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
            findAll();
        }
    });
}

function deleteUser(user) {
    console.log('deleteUser');
    $.ajax({
        type: 'DELETE',
        url: USER_URL + "/" +$('#login').val() + "/delete",
        Origin : 'http://localhost:63342/usermanagement/javascript-client/',
        success: function (data, textStatus, jqXHR) {
            alert('User delete successfully');
            $('#login').val(data.login);
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
            findAll();
        }
    });
}


function updateUser() {
    console.log('updateUser');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: USER_URL + "/" +$('#userId').val() + "/" + $('#password').val(),
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User updated successfully');
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateUser error: ' + textStatus);
        }
    });
}

function formToJSON() {
    var userId = $('#userId').val();
    return JSON.stringify({
        "userId": userId == "" ? null : userId,
        "login": $('#login').val(),
        "password": $('#password').val()
    });
}