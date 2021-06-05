<%--
  Created by IntelliJ IDEA.
  User: CONG
  Date: 6/4/2021
  Time: 9:20 AM
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
            background:#e9ecef;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand btn btn-outline-info" href="/Teacher?action=home">Back to home</a>
        <a class="navbar-brand btn btn-outline-info" href="/Teacher?action=classManager">Classroom Management</a>
        <h3 style="color: red">Teacher : ${teacher.name}</h3>
        <a class="navbar-brand btn btn-outline-info" href="/Teacher?action=logOut">LogOut</a>
    </div>
</nav>
<div class="col-sm-5  border border-secondary border-5 rounded  form-product-1">
    <div class="form-product">
        <form method="post" >
            <div class="form-group">
                <label class="form-label" for="id">Id giáo viên</label>
                <input class="form-control" name="id" id="id" readonly value="${teacher.id}">
            </div>
            <div class="form-group">
                <label class="form-label" for="name">Tên giáo viên</label>
                <input class="form-control" name="name" id="name" value="${teacher.name}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="email">Email</label>
                <input class="form-control" name="email" id="email" value="${teacher.email}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="password">Mật Khẩu</label>
                <input class="form-control" name="password" id="password" value="${teacher.password}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="url">Ảnh</label>
                <input class="form-control" name="url" id="url" value="${teacher.url}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="dob">Ngày sinh</label>
                <input class="form-control" name="dob" id="dob"  type="date" value="${teacher.dob}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="address_id">Địa chỉ</label>
                <select class="form-select" name="address_id"  id="address_id"  >
                    <c:forEach items="${addressList}" var="address" >
                        <option value="${address.id}"
                                <c:if test="${address.id == teacher.address.id}">selected="true"</c:if>>${address.name}
                        </option>
                    </c:forEach>
                </select></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="status_id">Trạng thái</label>
                <input class="form-control" name="status_id"  id="status_id" readonly value="${teacher.status.id}" >
            </div>
            <button class="btn btn-danger" type="submit">Xác Nhận</button>
        </form>
    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</html>

