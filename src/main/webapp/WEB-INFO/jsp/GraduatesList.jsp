<%--
  Created by IntelliJ IDEA.
  User: yanyufeng
  Date: 2017/11/1
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
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
                    用户类型选择<b class="caret"></b>
                </a>
                <ul  class="dropdown-menu" >
                    <li ><a href="/findAllGraduates.action" target="ibody">研究生用户</a></li>
                    <li><a href="/findAllUndergraduates.action" target="ibody" >本科生用户</a></li>
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
            <button class="zj_button1 btn btn-warning" style="float:right">增加用户</button>
            <form action="/findGraduatesByConditions.action" class="form-inline" role="form">

                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" id="userNameSelect" name="userNameSelect"
                           placeholder="${username}">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="text" class="form-control" id="passwordSelect" name="passwordSelect"
                           placeholder="${password}">
                </div>
                <div class="form-group">
                    <label>卡号</label>
                    <input type="text" class="form-control" id="cardNoSelect" name="cardNoSelect"
                           placeholder="${cardNo}">
                </div>
                <div class="form-group">
                    <label>学院</label>
                    <input type="text" class="form-control" id="departmentSelect" name="departmentSelect"
                           placeholder="${department}">
                </div>
                <div class="form-group">
                    <label>专业</label>
                    <input type="text" class="form-control" id="majorSelect" name="majorSelect"
                           placeholder="${major}">
                </div>
                <div class="form-group">
                    <label>导师</label>
                    <input type="text" class="form-control" id="directorSelect" name="directorSelect"
                           placeholder="${director}">
                </div>
                <button onclick="form.submit();" class="btn btn-default">查询</button>
            </form>
            <div class="box-content">
                <table id="testtable1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>卡号</th>
                        <th>密码</th>
                        <th>类型</th>
                        <th>学院</th>
                        <th>专业</th>
                        <th>导师</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${GraduateList}" var="BL" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td class="center">${BL.id}</td>
                        <td class="center">${BL.username}</td>
                        <td class="center">${BL.cardNo}</td>
                        <td class="center">${BL.password}</td>
                        <td class="center">${BL.type}</td>
                        <td class="center">${BL.department}</td>
                        <td class="center">${BL.major}</td>
                        <td class="center">${BL.director}</td>
                        <td class="center">
                            <button class="zj_button2 btn btn-success">修改</button>
                            <a class="btn btn-danger" href="/deleteGraduate.action?id=${BL.id}">删除</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>


            </div>

        </div>
    </div>
</div>

</div>
<div style="display: none;" class="zj_popWindow1">
    <div class="zj_popTop">
        <img onclick="doHide1(this)" src="${ctx}/resources/back/img/zj_end.png">
    </div>
    <div class="zj_popBottom">
        <form action="/addGraduate.action" class="form-inline" name="addForm" id="addForm">
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control" id="userNameAdd" name="userNameAdd"
                       placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label>卡号</label>
                <input type="text" class="form-control" id="cardNoAdd" name="cardNoAdd"
                       placeholder="请输入卡号">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="text" class="form-control" id="passwordAdd" name="passwordAdd"
                       placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label>学院</label>
                <input type="text" class="form-control" id="departmentAdd" name="departmentAdd"
                       placeholder="请输入学院">
            </div>
            <div class="form-group">
                <label>专业</label>
                <input type="text" class="form-control" id="majorAdd" name="majorAdd"
                       placeholder="请输入专业">
            </div>
            <div class="form-group">
                <label>导师</label>
                <input type="text" class="form-control" id="directorAdd" name="directorAdd"
                       placeholder="请输入导师">
            </div>

        </form>
        <div class="zj_check1">
            <button name="add" id="add">
                <img src="${ctx}/resources/back/img/zj_check.png">&nbsp;&nbsp;提交
            </button>
        </div>
        <div class="zj_cancle">
            <button onclick="doHide1(this)">
                <img src="${ctx}/resources/back/img/zj_cancle.png">&nbsp;&nbsp;取消
            </button>
        </div>
    </div>
</div>

<div style="display: none;" class="zj_popWindow2">
    <div class="zj_popTop">
        <img onclick="doHide2(this)" src="${ctx}/resources/back/img/zj_end.png">
    </div>
    <div class="zj_popBottom">
        <form action="/updateGraduate.action" class="form-inline" name="editForm" id="editForm">
            <div class="form-group" hidden="hidden">
                <label>id</label>
                <input  readonly="readonly" type="text" class="form-control" id="idUpdate" name="idUpdate"
                        placeholder="">
            </div>
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control" id="userNameUpdate" name="userNameUpdate"
                       placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label>卡号</label>
                <input type="text" class="form-control" id="cardNoUpdate" name="cardNoUpdate"
                       placeholder="请输入卡号">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="text" class="form-control" id="passwordUpdate" name="passwordUpdate"
                       placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label>类型</label>
                <select class="form-control"id="typeUpdate" name="typeUpdate">
                    <option>graduate</option>
                    <option>undergraduate</option>
                </select>
            </div>
            <div class="form-group">
                <label>学院</label>
                <input type="text" class="form-control" id="departmentUpdate" name="departmentUpdate"
                       placeholder="请输入学院">
            </div>
            <div class="form-group">
                <label>专业</label>
                <input type="text" class="form-control" id="majorUpdate" name="majorUpdate"
                       placeholder="请输入专业">
            </div>
            <div class="form-group">
                <label>导师</label>
                <input type="text" class="form-control" id="directorUpdate" name="directorUpdate"
                       placeholder="请输入导师">
            </div>

        </form>
        <div class="zj_check1">
            <button name="edit" id="edit">
                <img src="${ctx}/resources/back/img/zj_check.png">&nbsp;&nbsp;提交
            </button>
        </div>
        <div class="zj_cancle">
            <button onclick="doHide2(this)">
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
    $("#edit").click(function () {
        document.getElementById('editForm').submit();
        $("#edit").attr("disabled", "disabled");
    });
    $("#add").click(function () {
        document.getElementById('addForm').submit();
        $("#add").attr("disabled", "disabled");
    });
</script>
<script type="text/javascript" src="${ctx}/resources/back/js/popWindow.js"></script>

</body>
</html>
