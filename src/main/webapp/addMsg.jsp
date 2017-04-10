<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/button.css">
        <link rel="stylesheet" href="resources/css/main.css">
        <link rel="stylesheet" href="resources/css/input.css">
        <title>Create/Edit View</title>
    </head>
    <body>
              <div clas="div_center">
              <form name="form"  action='${model != null? "editMsg" : "add"}' method="POST">
              <div>
                  <h2 class="headertext">Create/Edit View</h2>
                  <label>User name</label>
                  <input type="hidden" id="id" name="id" value="${model.id_message}" />
                  <input required value='<c:if test="${model != null }">${model.user.name}</c:if>'<c:if test="${model != null }">readonly="readonly"</c:if> type="text" id="name" name="user" placeholder="User name">
              </div>
              <div>
                <label>Message text</label>
                <textarea placeholder="Message..." required id="message" name="message" rows="14"><c:if test="${model != null }">${model.message}</c:if></textarea>
              </div>
              <div>
                  <button class="silver-flat-button">Save</button>
                  <button class="silver-flat-button" onclick="location.href = 'message.jsp'">Cancel</button>
              </div>  
             </form>
              </div>
          
    </body>
</html>
