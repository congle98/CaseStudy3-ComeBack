<%--
  Created by IntelliJ IDEA.
  User: CONG
  Date: 6/2/2021
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Hello, world!</title>
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
        <a class="navbar-brand btn btn-outline-info" href="/Teacher?action=home">Back to home</a>
        <h3 style="color: red">Teacher : ${teacher.name}</h3>
        <a class="navbar-brand btn btn-outline-info" href="/Teacher?action=logOut">LogOut</a>
    </div>
</nav>
<div class="col-sm-5  border border-secondary border-5 rounded   form-product-1">
    <p>${message}</p>
    <div class="form-product">
        <form method="post">
            <div class="form-group">
                <label class="form-label" for="description">Nhập nội dung</label>
                <input name="description" id="description"  class="form-control" type="text" placeholder="nội dung">
            </div>
            <div class="form-group">
                <label class="form-label" for="date">Nhập ngày đăng</label>
                <input name="date" id="date"  class="form-control" type="date" placeholder="ngày đăng">
            </div>
            <div class="form-group">
                <label class="form-label" for="class_id">Lớp</label>
                <select name="class_id" id="class_id" class="form-select">
                    <c:forEach items="${classList}" var="cl">
                        <option value="${cl.id}">${cl.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button  class="btn btn-danger" type="submit">Thêm mới</button>
            </div>
        </form>
    </div>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</html>


