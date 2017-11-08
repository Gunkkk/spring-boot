<%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/11/7
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/resources/css/index.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/back/css/popWindow.css">
    <script src="${ctx}/resources/js/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${ctx}/resources/css/jquery.paginate.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/jquery.yhhDataTable.css" />
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

        tr>th,td{
            text-align: center;
            vertical-align: middle;
        }
    </style>
<script>
    function validateLibraryCode() {
        var code = $('#codeAdd').val();
      //  window.location="/deleteItem.action?itemId="+itemid+"&titleId="+titleid;
        $.ajax({
            url:'/validateLibraryCode.action',
            data:{
                "libraryCode":code,
            },
            type:'get',
            success:function (data) {
                if(data=='yes') {
                    $('#validateLabel').text('可用');
                    $('#addItem').attr("disabled",false);
                }else{
                    $('#validateLabel').text('不可用');
                }
            },
            error:function () {
                alert("错误");
            }
        });
    }
</script>
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
                    用户管理<b class="caret"></b>
                </a>
                <ul  class="dropdown-menu" >
                    <li ><a href="/findAllGraduates.action" target="ibody">研究生用户</a></li>
                    <li><a href="/findAllUndergraduates.action" target="ibody" >本科生用户</a></li>
                    <li><a href="/findAllUser.action" target="ibody" >图书管理员</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    图书管理<b class="caret"></b>
                </a>
                <ul  class="dropdown-menu" >
                    <li ><a href="/adminBook.action" target="ibody">书籍管理</a></li>
                    <li><a href="/adminMagazine.action" target="ibody" >杂志管理</a></li>
                </ul>
            </li>

        </ul>
    </div>
    <div class="index-top-user">
        <a href="/logout.action"> <span class="index-top-user-info"><span class="glyphicon glyphicon-log-out"></span> 退出</span></a>
        <span class="index-top-user-info">${user.username}，您好！</span>
    </div>
</div>


<div class="content">
    <div class="container">
        <div class="container-body">
            <button class="item_button1 btn btn-warning" style="float:right">增加书项</button>
            <div class="box-content">
                <table class="table table-striped table-bordered" id="testtable1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>ID</th>
                        <th>libraryCode</th>
                        <th>书目ID</th>
                        <th>借阅者ID</th>
                        <th>预定者ID</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="BL" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td class="center" id="id">${BL.id}</td>
                        <td class="center">${BL.libraryCode}</td>
                        <td class="center" id="title">${BL.title.id}</td>
                        <td class="center">${BL.loan.borrowerId}</td>
                        <td class="center">${BL.reservation.borrowerId}</td>
                        <td class="center">
                            <a class="btn btn-danger" href="/deleteItem.action?itemId=${BL.id}&titleId=${BL.title.id}">删除</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>

</div>
<div style="display: none;" class="zj_popWindow1">
    <div class="zj_popTop">
        <img onclick="doHideItem1(this)" src="${ctx}/resources/back/img/zj_end.png">
    </div>
    <div class="zj_popBottom">
        <form action="/addItem.action" class="form-inline" name="addForm" id="addForm">
            <div class="form-group">
                <label>libraryCode</label>
                <input type="text" class="form-control" id="codeAdd" name="codeAdd"
                       placeholder="请输入libraryCode">
                <input type="text" id="titleId" name="titleId" value="${titleId}" hidden>
                <label id="validateLabel"></label>
            </div>
        </form>
        <div class="zj_check1">
            <button name="addItem" id="addItem" disabled>
                <img src="${ctx}/resources/back/img/zj_check.png">&nbsp;&nbsp;提交
            </button>
        </div>
        <div class="zj_cancle">
            <button onclick="doHideItem1(this)">
                <img src="${ctx}/resources/back/img/zj_cancle.png">&nbsp;&nbsp;取消
            </button>
        </div>
    </div>
</div>

<div style="display: none;" class="zj_shade"></div>
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
<script>
    $("#addItem").click(function () {
        document.getElementById('addForm').submit();
        $("#addItem").attr("disabled", "disabled");
    });
    $("#codeAdd").blur(function () {
        validateLibraryCode();
    });
    $("#codeAdd").focus(function () {
        $("#addItem").attr("disables","disabled");
    })
</script>
<script type="text/javascript" src="${ctx}/resources/back/js/popWindow.js"></script>
</body>
</html>