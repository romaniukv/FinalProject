<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/table-style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="../topMenu.jsp"%>
    <div class="main">
        <div class="form">
            <form action="creditAccountManagement" method="post">
                <table class="table">
                    <tr>
                        <td>ID</td>
                        <td>${requestScope.creditAccount.id}</td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td>
                            <a href="<%=request.getContextPath()%>/ownerInfo?${requestScope.accountOwner.id}">
                                ${requestScope.accountOwner.lastName} ${requestScope.accountOwner.firstName}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Limit</td>
                        <td>${requestScope.creditAccount.limit}</td>
                    </tr>
                    <tr>
                        <td>Credit rate</td>
                        <td>${requestScope.creditAccount.creditRate}</td>
                    </tr>

                    <tr>
                        <td>Expiration date</td>
                        <td>
                            <input type="text" name="expirationDate" value="${requestScope.creditAccount.expirationDate}" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <select name="department">
                                <c:forEach var="status" items="${requestScope.statuses}">
                                    <option value="${status.value}">${status.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
