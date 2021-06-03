<%--
  Created by IntelliJ IDEA.
  User: vitalik
  Date: 19/05/2021
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cat</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/view">
    <img src="https://static.ngs.ru/news/2020/99/preview/8290b39b6601efa615afd4bbd93d82320a039883_720_405_c.png" alt="Cat">
    <table>
        <tr>
            <td>
                Файл:<label>
                <input type="text" name="fileParam">
            </label>
            </td>
        </tr>
    </table>
    <input type="submit" value="Скачать">
</form>
</body>
</html>
