<%@ page import="com.course.admin.entity.Borrower" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<%Borrower borrower = (Borrower)session.getAttribute("borrower");%>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${ctx}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>

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
                        }
                    }
                });
            }
            else {
                $('#msg').html(msg);
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
    <title>首页</title>
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
                            <a href="http://www.whut.edu.cn" target="_blank">武汉理工大学主页</a>
                        </li>
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
                            <ul class="dropdown-menu">
                                <li>
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
            <div id="myCarousel" class="carousel slide col-md-8">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${cfx}/resources/image/photo1.jpg" width="600" height="800" align="middle" alt="First slide">
                        <div class="carousel-caption">图书馆</div>
                    </div>
                    <div class="item">
                        <img src="${cfx}/resources/image/photo2.jpg" width="600" height="800" align="middle" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="${cfx}/resources/image/photo3.jpg"width="600" height="800" align="middle" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;
                </a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;
                </a>
            </div>
            <div class="jumbotron col-md-4" style="opacity:0.7" >
                <h1>
                    欢迎！
                </h1>
                <p>
                    这是图书馆首页！
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="/login.action">管理入口!</a>
                </p>
            </div>
        </div>
    </div>
</div>
<div class="row clearfix">


    <div class="col-md-4 column">

    </div>
</div>
<div class="row clearfix">

</div>
<div class="row clearfix">
    <div class="col-md-2 column">
    </div>
    <div class="col-md-6 column">
        <address> <strong>联系方式：</strong><br /><br>email:840745950@qq.com</br> </address>
    </div>
    <div class="col-md-4 column">
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
