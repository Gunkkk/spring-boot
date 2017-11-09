<%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/11/9
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<head>
    <title>ERROR</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${ctx}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>
</head>
<body style ="background-image:url(${ctx}/resources/image/bg1.jpg)">
<div class="container"  style ="background-image:url(${ctx}/resources/image/bg1.jpg)">
    <div  style="height:130px">
        <img style="margin-left:100px" src="${ctx}/resources/image/logo.png"> </img>
    </div>
    <div class="row clearfix" >
        <div class="col-md-12 column" >
            <nav class="navbar navbar-default" role="navigation" style="opacity:0.7">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    </button> <a class="navbar-brand" href="#">图书馆</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/index">首页</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${msg}<strong class="caret"></strong></a>

                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" id="search" name="search"placeholder="书名/作者名" />
                        </div> <button type="submit" class="btn btn-default" formaction="/borrowerSearch.action">Go！</button>
                    </form>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">更多<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="row" >
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        错误页面，请返回主页！
                        <div>${url}</div>
                        <div>${e.message}</div>
                    </div>
                </div>
                <div class="row clearfix">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
