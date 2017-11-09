<%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/11/8
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.course.admin.entity.Borrower" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<%Borrower borrower = (Borrower)session.getAttribute("borrower");%>
<head>
    <meta charset="utf-8">
    <title>搜索结果</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/resources/css/index.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/back/css/popWindow.css">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.js"></script>
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
    <script type="text/javascript">

        $(function() {
            $('.dropdown-toggle').dropdown();
            $('#login').click(function(){
                $('#modallogin').modal('show');
            });

            $('#myCarousel').carousel('cycle');
        });
        function check() {
            var form = document.forms['form2'];
            var msg = "";
            if (form.username.value == "" || form.username.value == null) {
                msg += "请输入用户名\n";
            }
            if (form.password.value == "" || form.password.value == null) {
                msg += "请输入密码";
            }

            if (msg == "") {
                var data=$('#form2').serialize();
                $.ajax({
                    url:'/toBorrowerLogin.action',
                    data:data,
                    type:'post',
                    success:function (data) {
                        if(data=='success'){
                            location.reload();
                        }else {
                            data=eval("("+data+")");
                            $('#msg').html(data.msg);
                            setTimeout(function (){
                            },5000);
                        }
                    }
                });
                /*       form.action = "/toBorrowerLogin.action";
                 form.submit();*/
            }
            else {
                $('#msg').html(msg);
            }
        }
        function borrowerQueryCheck(data) {
           if (${borrower!=null}){
           window.location = "/borrowerQueryItem.action?titleId="+data;
           }else{
               alert("请先登录！");
           }
    }
        function loanLoginCheck(){
            if (${borrower!=null}){
                window.location='/queryLoan.action';
            }else{
                alert("请先登录！");
            }
        }
        function reservationLoginCheck(){
            if (${borrower!=null}){
                window.location='/toAddReservation.action';
            }else{
                alert("请先登录！");
            }
        }
    </script>
</head>
<body style ="background-image:url(${ctx}/resources/image/bg1.jpg)">
<img style="margin-left:100px" src="${ctx}/resources/image/logo.png"> </img>
<div class="container"  style ="background-image:url(${ctx}/resources/image/bg1.jpg)">
    <div  style="height:130px">
    </div>

    <div class="row clearfix" >
        <div class="col-md-12 column" >
            <nav class="navbar navbar-default" role="navigation" style="opacity:0.7">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    </button> <a class="navbar-brand" href="/index">主页</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">功能菜单<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:reservationLoginCheck()" target="_blank">预定书籍</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="javascript:loanLoginCheck()" target="_blank">查询借阅状况</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" id="search" name="search"placeholder="书名/作者名" />
                        </div> <button type="submit" class="btn btn-default" formaction="/borrowerSearch.action">Go！</button>
                    </form>

                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${borrower==null}" >
                            <li>
                                <a id="login"data-toggle="modal" href="#" >登录</a>
                            </li>
                        </c:if>
                        <c:if test="${borrower!=null}" >
                            <li >
                                <a data-toggle="modal"><%=borrower.getUsername()%>,你好！</a>
                            </li>
                            <li>
                                <a id="logout"data-toggle="modal" href="/borrowerLogout.action" >注销</a>
                            </li>
                        </c:if>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">更多<strong class="caret"></strong></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="row" >
    <div class="container">
        <div class="container-body">
            <div class="box-content">
                <table class="table table-striped table-bordered" id="testtable1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>书名</th>
                        <th>书目ID</th>
                        <th>作者</th>
                        <th>类型</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="BL" varStatus="i">
                        <td>${i.index + 1}</td>
                        <td class="center">${BL.name}</td>
                        <td class="center">${BL.id}</td>
                        <td class="center">${BL.author}</td>
                        <td class="center">${BL.type}</td>
                        <td class="center">${BL.price}</td>
                        <td class="center">
                            <a class="btn btn-default" href="javascript:borrowerQueryCheck(${BL.id})">查询库存</a>
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
</div>
<div class="modal fade" id="modallogin" tabindex="-1" role="dialog" aria-labelledby="modallogin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录页面</h4>
            </div>
            <form name="form2" id="form2" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group">
                        <label class="control-label">密码:</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="modal-footer">
                        <span ><label class="msg" id="msg" name="msg" value='msg'></label></span>
                        <button type="button" class="btn btn-default" onclick="javascript:check();">提交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
