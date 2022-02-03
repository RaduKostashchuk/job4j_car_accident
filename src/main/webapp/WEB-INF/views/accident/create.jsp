<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

    <title>Создать заявление</title>
</head>
<body>
<div class="container">
    <p class="h5">Добавить нарушение ПДД</p>
    <form  action="<c:url value='/save'/>" method='POST'>
        <table>
            <tr>
                <td>
                    <label for="nameInput">Название:</label>
                    <input type='text' id="nameInput" name='name' placeholder="<c:out value='${accident.name}'/>">
                </td>
                <td>
                    <label for="typeSelect">Тип:</label>
                    <select name="type.id" id="typeSelect">
                        <c:forEach var="type" items="${types}">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <label for="ruleSelect">Статьи:</label>
                    <select name="ruleIds" id="ruleSelect" multiple>
                        <c:forEach var="rule" items="${rules}">
                            <option value="${rule.id}">${rule.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="Сохранить" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
