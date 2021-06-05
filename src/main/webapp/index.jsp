<%--
  Created by IntelliJ IDEA.
  User: CONG
  Date: 6/1/2021
  Time: 3:34 PM
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
  <link rel="stylesheet" href="/style/fontawesome-free-5.15.3-web/css/all.css">

  <style>
    .form-login{
      background-color: rgba(49,57,65,0.7);
      margin: 150px auto;
      border-radius: 5px;
      width: 300px;

    }
    body{
      background-image: url(/style/img/login-background.jpg);
      background-repeat: no-repeat ;

    }
    .form-title{
      height: 50px;
      text-align: center;
      line-height: 50px;
      font-size: 2rem;
    }
    .form-content{
      width: 90%;
      margin: auto;
    }
    .btn-submit{
      width:100%;
      background-color: #009eda;
      margin-bottom: 30px;
    }

  </style>
  <title>Login!</title>
</head>
<body>
<div class="form-login">
  <div class="form-title">
    <p><i class="fas fa-chalkboard-teacher"></i> Login Forms</p>
  </div>
  <div class="form-content">
    <form action="/trang-chu" method="post">
      <div class="mb-3">
        <label for="exampleInputEmail1"  class="form-label">Email</label>
        <input name = "email" type="email" class="form-control"  id="exampleInputEmail1" aria-describedby="emailHelp">
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input name = "password" type="password" class="form-control" id="exampleInputPassword1">
      </div>
      <div class="mb-3 form-check">
        <select name="action" id="">
          <option value="student">học sinh</option>
          <option value="teacher">giáo viên</option>
          <option value="supervisor">giáo vụ</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary btn-submit">Submit</button>
    </form>
  </div>
  <!-- <div class="change-register">
      <span>Already have an account?</span><a href="" class="register-link">Login</a>
  </div> -->
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

</html>