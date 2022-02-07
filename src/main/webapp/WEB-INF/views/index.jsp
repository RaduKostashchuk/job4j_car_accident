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

    <title>Нарушения ПДД</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-2">
            <p class="h5 m-1">Нарушения ПДД</p>
        </div>
        <div class="col-2 offset-6">
            <a class="btn btn-primary m-1" href="<c:url value='/create'/>">Добавить инцидент</a>
        </div>
        <div class="col-2">
            <p>Вы зашли как: ${user.username}</p>
        </div>
    </div>
    <table class="table table-striped">
        <thead class="table-light">
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Адрес</th>
            <th>Тип</th>
            <th>Статьи</th>
            <th>Действие</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td>
                    <c:out value="${accident.name}"/>
                </td>
                <td>
                    <c:out value="${accident.description}"/>
                </td>
                <td>
                    <c:out value="${accident.address}"/>
                </td>
                <td>
                    <c:out value="${accident.type.name}"/>
                </td>
                <td>
                    <c:forEach items="${accident.rules}" var="rule">
                    <c:out value="${rule.name}"/>
                    <br/>
                    </c:forEach>
                </td>
                <td>
                    <a class="btn btn-primary m-1" href="<c:url value='/update?id=${accident.id}'/>">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>