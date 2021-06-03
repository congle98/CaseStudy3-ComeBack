<%--
  Created by IntelliJ IDEA.
  User: CONG
  Date: 6/2/2021
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sửa sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <style>
        .form-product-1{
            margin: 75px auto;
            /*height: 570px;*/
        }
        .form-product{
            width: 90%;
            height: 90%;
            margin: auto;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand btn btn-outline-info" href="/Admin?action=home">Thoát ra ngoài</a>
    </div>
</nav>

<div class="col-sm-5  border border-secondary border-5 rounded  form-product-1">
    <div class="form-product">
        <form method="post" >
            <div class="form-group">
                <label class="form-label" for="id">Id giáo vụ</label>
                <input class="form-control" name="id" id="id" value="${supervisor.id}" disabled>
            </div>
            <div class="form-group">
                <label class="form-label" for="name">Tên giáo vụ</label>
                <input class="form-control" name="name" id="name" value="${supervisor.name}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="email">Email</label>
                <input class="form-control" name="email" id="email" value="${supervisor.email}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="password">Mật Khẩu</label>
                <input class="form-control" name="password" id="password" value="${supervisor.password}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="url">Ảnh</label>
                <input class="form-control" name="url" id="url" value="${supervisor.url}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="dob">Ngày sinh</label>
                <input class="form-control" name="dob" id="dob" value="${supervisor.dob}"></br>
            </div>
            <div class="form-group">
                <label class="form-label" for="status_id"></label>
                <select class="form-select" name="status_id"  id="status_id" multiple >
                    <c:forEach items="${listStatus}" var="status" >
                        <option value="${status.id}"
                                <c:forEach items="${statusOfSupervisor}" var="sos">
                                    <c:if test="${status.id == sos.id}">selected="true"</c:if>
                                </c:forEach>>
                                ${status.name}
                        </option>
                    </c:forEach>
                </select></br>
                <%--                <c:forEach items="${categoriesOfBook}" var="categoriesOfBook">--%>
                <%--                    <c:if test="${category.id = categoriesOfBook.id}">selected="true"</c:if>--%>
                <%--                </c:forEach>--%>
            </div>
            <div class="form-group">
                <label class="form-label" for="address_id"></label>
                <select class="form-select" name="address_id"  id="address_id" multiple >
                    <c:forEach items="${listAddress}" var="address" >
                        <option value="${address.id}"
                                <c:forEach items="${addressOfSupervisor}" var="aos">
                                    <c:if test="${address.id == aos.id}">selected="true"</c:if>
                                </c:forEach>>
                                ${address.name}
                        </option>
                    </c:forEach>
                </select></br>
                <%--                <c:forEach items="${categoriesOfBook}" var="categoriesOfBook">--%>
                <%--                    <c:if test="${category.id = categoriesOfBook.id}">selected="true"</c:if>--%>
                <%--                </c:forEach>--%>
            </div>
            <button class="btn btn-danger" type="submit">Xác Nhận</button>
        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</html>

