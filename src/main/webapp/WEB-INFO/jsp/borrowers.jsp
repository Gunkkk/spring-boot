<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 10:37
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

    <script>
        function go() {
            var url = document.getElementById("url").value;
            var pc = document.getElementById("pc").value;
            var tp = document.getElementById("tp").value;
            if (parseInt(pc)<1 ) {
                pc=1;
            }else if (parseInt(pc)>parseInt(tp)) {
                pc=tp;
            }
            var fUrl = url + "&pc=" + pc;
            window.location.href = fUrl;
        }
    </script>
    <%--<script>--%>
        <%--var  =${user};--%>
    <%--</script>--%>


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
                    其他<b class="caret"></b>
                </a>
                <ul  class="dropdown-menu" >
                    <li ><a href="/findAllBorrowers.action" target="ibody">用户管理</a></li>
                    <li id="groupSearch" ><a href="/toGroupSearch.action" target="ibody" >团体查询</a></li>
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
            <form action="/selectBorrowers.action" class="form-inline" role="form">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" class="form-control" id="idSelect" name="idSelect"
                           placeholder="${id}">
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" id="userNameSelect" name="userNameSelect"
                           placeholder="${username}">
                </div>
                <div class="form-group">
                    <label>类型</label>
                    <input type="text" class="form-control" id="userTypeSelect" name="userTypeSelect"
                           placeholder="${type}">
                </div>

                <button onclick="form.submit();" class="btn btn-default">查询</button>
            </form>
            <div class="box-content">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>密码</th>
                        <th>类型</th>
                        <th>卡号</th>
                        <th>学院</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.getContent()}" var="BL" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + page.getNumber()*page.getSize()}</td>

                        <td class="center">${BL.id}</td>
                        <td class="center">${BL.username}</td>
                        <td class="center">${BL.password}</td>
                        <td class="center">${BL.type}</td>
                        <td class="center">${BL.cardNo}</td>
                        <td class="center">${BL.department}</td>
                        <td class="center">
                            <button class="zj_button2 btn btn-success">修改</button>
                            <a class="btn btn-danger" href="/deleteBorrower.action?id=${BL.id}">删除</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>

                <div align="right">
                    <table>
                        <tr>
                            <%--<form height="16px" class="recode">--%>
                            <td><span id="recode1" class="recode">总记录数：${page.getTotalElements()}条</span></td>
                            <td><a href=""><img src="${ctx}/resources/image/page/page_home.png" class="recode"></a></td>
                            <c:if test="${page.getNumber()>0}">
                                <td><a href="/findAllBorrowers.action?currentPage=0"><img src="${ctx}/resources/image/page/page_prev.png" class="recode"></a></td>
                            </c:if>
                            <c:if test="${page.getNumber()+1<page.getTotalPages()}">
                                <td><a href="/findAllBorrowers.action?currentPage=${page.getNumber()+1}"><img src="${ctx}/resources/image/page/page_next.png" class="recode"></a></td>
                            </c:if>
                            <td><a href="/findAllBorrowers.action?currentPage=${page.getTotalPages()-1}"><img src="${ctx}/resources/image/page/page_end.png" class="recode"></a></td>
                            <td><span id="recode2" class="recode">第${(page.getNumber()+1)}页/共${page.getTotalPages()}页&nbsp;&nbsp;转到</span></td>


                            <%--下面的input标签顺序不要变--%>
                            <%--<input type="hidden" name="no" value="${pb.beanList.no}">--%>
                            <td>
                                <input  type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id="pc">
                            </td>
                            <td>
                                <%--<input type="hidden" id="url" value="${pb.url}">--%>
                                <input type="hidden" id="tp" value="${page.getTotalPages()}">
                                <input type="image" class="recode" src="${ctx}/resources/image/page/page_go.png" onclick="go();return false;"/>
                            </td>

                            <%--<td><a href="checkOneProject.action?no=1&projectId=20170400&pc=${projectUser.pc}"><img src="../../../../image/page/page_go.png"></a></td>--%>

                        </tr>
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
        <form action="/addUser.action" class="form-inline" name="addForm" id="addForm">
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control" id="userNameAdd" name="userNameAdd"
                       placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="text" class="form-control" id="userPasswordAdd" name="userPasswordAdd"
                       placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label>类型</label>
                <input type="text" class="form-control" id="userTypeAdd" name="userTypeAdd"
                       placeholder="请输入类型">
            </div>
            <div class="form-group">
                <label>卡号</label>
                <input type="text" class="form-control" id="stuNameAdd" name="stuNameAdd"
                       placeholder="请输入卡号">
            </div>
            <div class="form-group">
                <label>学院</label>
                <input type="text" class="form-control" id="stuClassAdd" name="stuClassAdd"
                       placeholder="请输入学院">
            </div>

        </form>
        <div class="zj_check1">
            <button name="addUser" id="addUser">
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
        <form action="/editUser.action" class="form-inline" name="addForm" id="editForm">
            <div class="form-group">
                <label>id</label>
                <input  readonly="readonly" type="text" class="form-control" id="userIdEdit" name="userIdEdit"
                       placeholder="">
            </div>
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control" id="userNameEdit" name="userNameEdit"
                       placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="text" class="form-control" id="userPasswordEdit" name="userPasswordEdit"
                       placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label>类型</label>
                <input type="text" class="form-control" id="userTypeEdit" name="userTypeEdit"
                       placeholder="请输入类型">
            </div>
            <div class="form-group">
                <label>卡号</label>
                <input type="text" class="form-control" id="stuNameEdit" name="stuNameEdit"
                       placeholder="请输入卡号">
            </div>
            <div class="form-group">
                <label>学院</label>
                <input type="text" class="form-control" id="stuClassEdit" name="stuClassEdit"
                       placeholder="请输入学院">
            </div>

        </form>
        <div class="zj_check1">
            <button name="editUser" id="editUser">
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
    $("#editUser").click(function () {
    document.getElementById('editForm').submit();
    $("#editUser").attr("disabled", "disabled");
    });
    $("#addUser").click(function () {
        document.getElementById('addForm').submit();
        $("#addUser").attr("disabled", "disabled");
    });
</script>
<script type="text/javascript" src="${ctx}/resources/back/js/popWindow.js"></script>
</body>
</html>