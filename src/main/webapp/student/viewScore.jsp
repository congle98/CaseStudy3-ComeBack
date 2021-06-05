<%--
  Created by IntelliJ IDEA.
  User: CONG
  Date: 6/4/2021
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TEACHER HOME</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <style>
        .form-product-1{
            margin: 75px auto;
        }
        .form-product{
            width: 90%;
            height: 90%;
            margin: auto;

        }
        body{
            background: #e9ecef;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand btn btn-outline-info" href="/Student?action=accountManager">Account Management</a>

        <a class="navbar-brand btn btn-outline-info" href="/Student?action=home">Back to home</a>

        <h3 style="color: red">Student : ${student.name}</h3>
        <a class="navbar-brand btn btn-outline-info" href="/Student?action=logOut">LogOut</a>
    </div>
</nav>
<div style="margin-top: 70px">

    <table class="table table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id module</th>
            <th scope="col">Tên module</th>
            <th scope="col">Ngày bắt đầu</th>
            <th scope="col">Ngày kết thúc</th>
            <th scope="col">Số điểm</th>

        </tr>
        </thead>
        <tbody>
            <tr>
                <td scope="row">${module.id}</td>
                <td>${module.name}</td>
                <td>${module.dateStart}</td>
                <td>${module.dateEnd}</td>
                <td><c:if test = "${score > 0}">
                    ${score}
                </c:if></td>
            </tr>
        </tbody>
    </table>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</html>
>
