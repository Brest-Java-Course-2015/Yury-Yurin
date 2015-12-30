<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>

</head>
<header>
</header>
<body>
        <c:forEach items="${applications}" var="user">
            <p>${user.applicationId}</p>
           <p>${user.createdDate}</p>
            <p>${user.updatedDate}</p>
    </c:forEach>
</body>
</html>