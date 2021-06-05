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
    <title>Hello, world!</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand btn btn-outline-info" href="/Admin?action=home">Back to home</a>
    </div>
</nav>
<div class="col-sm-5  border border-secondary border-5 rounded  form-product-1">
    <div class="form-product">
        <form method="post">
            <div class="form-group">
                <label class="form-label" for="name">Nhập tên</label>
                <input name="name" id="name"  class="form-control" type="text" placeholder="tên">
            </div>

            <div class="form-group">
                <label class="form-label" for="email">Nhập email</label>
                <input name="email" id="email"  class="form-control" type="text" placeholder="email">
            </div>

            <div class="form-group">
                <label class="form-label" for="password">Nhập mật khẩu</label>
                <input name="password" id="password" class="form-control"  type="text" placeholder="password">
            </div>

            <div class="form-group">
                <label class="form-label" for="url">Nhập link ảnh</label>
                <input name="url" id="url" class="form-control"  type="text" placeholder="link ảnh">
            </div>

            <div class="form-group">
                <label class="form-label" for="dob">Ngày sinh</label>
                <input name="dob" id="dob" class="form-control"  type="date" placeholder="ngày sinh">
            </div>

            <div class="form-group">
                <label class="form-label" for="address_id">Quê quán</label>
                <select name="address_id" class="form-select" id="address_id">
                    <c:forEach items="${addressList}" var="address">
                        <option value="${address.id}">${address.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label class="form-label" for="status_id">Trạng thái</label>
                <select name="status_id" class="form-select" id="status_id">
                    <c:forEach items="${statusList}" var="status">
                        <option value="${status.id}">${status.name}</option>
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

