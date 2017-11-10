<%@ page import="com.course.admin.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yanyufeng
  Date: 2017/11/1
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<%User user = (User) session.getAttribute("user");%>
<head>
    <title></title>

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/resources/css/index.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/back/css/popWindow.css">
    <script src="${ctx}/resources/js/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${ctx}/resources/css/jquery.paginate.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/jquery.yhhDataTable.css" />
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.10.2.min"></script>--%>
    <script type="text/javascript" src="${ctx}/resources/js/jquery.paginate.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery.yhhDataTable.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/indexForPaging.js" ></script>

    <style>
        img{
            float: left;
        }
        .content{
            /*background: #eeeeee;*/
            padding: 20px 0;
        }
        .container{
            /*padding: 20px 0px;*/
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-radius: 3px;
            margin-bottom: 10px;

        }
        .module-box{
            width: 100%;
            margin: 0 auto 20px auto;
            position: relative;
        }
        /*.module-box {*/
        /*border: 1px solid #e0e0e0;*/
        /*border-radius: 3px;*/
        /*margin-bottom: 10px;*/
        /*background: #ffffff;*/

        /*height: auto;*/
        /*}*/
        .module-box img{
            display: inline-block;
            position: absolute;
            z-index: 1;

        }
        .module-box .line {
            width: 100%;
            border-bottom: 1px #000 solid;
            height: 52px;
            position: absolute;
            top: 30px;
            z-index: 0;
            margin: 0 auto;
            line-height: 50px;
        }
        .module-box .categoryp{
            width:250px;
            text-align:center;
            font-size:18px;
            color:#666;
            font-weight:bold;
            border-bottom:5px #000 solid;
            margin:0 0 0 50px;
            line-height:50px ;}
        .module-box .categoryp p{
            margin: 0 0;
        }
        .prif-info{
            padding: 10px 30px 0;
            font-weight:bold;
            font-size: 18px;
            text-indent: 2em;
        }
        .info{
            padding: 150px 30px 0;
            font-weight:bold;
            font-size: 18px;
            text-indent: 2em;

        }

        tr>th,td{
            text-align: center;
            vertical-align: middle;
        }
        .recode{
            margin: 5px 5px;
        }
        #pc{
            max-width: 35px;
            height: 16px;
            width: 40px;
            border-radius: 5px;
            border:1px solid #CCCCCC;
            background-color: #eeffbb;
        }

    </style>
</head>
<body>
<div class="index-top">
    <div class="index-top-head">
        <p class="index-top-head-info ">图书馆管理系统</p></div>
    <div class="menu">
        <!-- 菜单 -->
        <ul class="nav navbar-nav">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    图书管理员事务<b class="caret"></b>
                </a>
                <ul  class="dropdown-menu" >
                    <li><a href="/toWork.action" target="ibody" >借出书物</a></li>
                    <li><a href="/toReservation.action" target="ibody" >浏览预定</a></li>
                </ul>
            </li>

        </ul>
    </div>
    <div class="index-top-user">
        <a href="/logout.action"> <span class="index-top-user-info"><span class="glyphicon glyphicon-log-out"></span> 退出</span></a>
        <span class="index-top-user-info"><%=user.getUsername()%>，您好！</span>
    </div>
</div>


<div class="content">
    <div class="container">
        <div class="container-body">
            <div class="box-content">
                <table class="table table-striped table-bordered" id="testtable1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>书名</th>
                        <th>借阅者ID</th>
                        <th>预定日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="BL" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td class="center">${BL.title.name}</td>
                        <td class="center">${BL.borrowerId}</td>
                        <td class="center">${BL.reserveDate}</td>
                        <td class="center">
                            <a class="btn btn-danger" href="/userCancelReservation.action?reservationId=${BL.id}">取消预定</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
        setHeight();
        $("#ibody").attr("src", "toUploadAndDown.action");
    });
    $(window).resize(setHeight);
    function setHeight() {
        //高度适应
        $("#container,#container-fluid").height($(window).height() - 82);

        //宽度适应
        $("#container").width($(window).width() - (235));
    }
</script>

<script type="text/javascript" src="${ctx}/resources/back/js/popWindow.js"></script>

</body>
</html>
