<%-- 
    Document   : message
    Created on : 02.04.2017, 23:15:55
    Author     : Dimon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="resources/js/jquery/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="resources/css/table.css">
        <link rel="stylesheet" href="resources/css/button.css">
        <link rel="stylesheet" href="resources/css/main.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main View</title>
    </head>
    <body>
        <h2 id="header" class="headertext"></h2>
        <table id="messages" border="1">
        </table>
        <div id="button" class="div-center">
        </div>
    </body>
</html>
<script>
    $( document ).ready(function() {
        var elements = '<thead><tr>'
                     + '<th>Message text</th>'
                     + '<th>User name</th>'
                     + '<th colspan="2">Actions</th>'
                     + '</tr></thead> ';
        $.ajax({
            type: 'GET',
            url: "msgList",
            dataType: "json",
            success: function (response) {
                if (response) {
                    elements += '<tbody>';
                    for(var i in response){
                        elements += '<tr id="' + response[i]['id_message'] + '">';
                        elements += '<td>' + response[i]['message'] + '</td>';
                        elements += '<td>' + (response[i]['user'] === null? '':response[i]['user']['name']) + '</td>';
                        elements += '<td><form method="POST" action="add"><input name="id" id="id" type="hidden" value="' + response[i]['id_message'] + '" /><input class="silver-flat-button" type="submit" value="Edit" /></form></td>';
                        elements += '<td><button class="silver-flat-button" onclick="del(' + response[i]['id_message'] + ');">Delete</button></td>';
                    }
                    elements += '</tbody>';
                    $("#header").append('Main View');
                    $("#messages").append(elements);
                    $("#button").append('<br /><button  class="silver-flat-button" onclick="location.href = \'addMsg.jsp\'">Add new message</button>');
                }
            },
            eroor: function (error) {
                console.error("Failed retrive messages." + error);
            }
        });
        
    });

    function del(id) {
        $.ajax({
            type: 'DELETE',
            url: "deleteMsg"+"/"+id,
            dataType: "json",
            success: function (response) {
                if (response) {
                    $('table#messages tr#' + id).remove();
                    console.log('Delete message from table with id '+id);
                }
            },
            eroor: function (error) {
                console.error("Failed delete message with id = " + id + "." + error);
            }
        });
    }
</script>
