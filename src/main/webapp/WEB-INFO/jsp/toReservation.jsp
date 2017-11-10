<%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/11/8
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.course.admin.entity.Borrower" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%Borrower borrower = (Borrower)session.getAttribute("borrower");%>
<%request.setAttribute("ctx", request.getContextPath()); %>
<head>
    <title>预定处理页面</title>
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
            line-height:50px ;}
        .module-box .categoryp p{
            margin: 0 0;
        }

        tr>th,td{
            font-weight:bold;
            border-bottom:5px #000 solid;
            margin:0 0 0 50px;
            text-align: center;
            vertical-align: middle;
        }
    </style>
    <script type="text/javascript">

        $(function() {
            $('.dropdown-toggle').dropdown();
            $('#reserve').click(function(){
                $('#modallogin').modal('show');
            });

            $('#myCarousel').carousel('cycle');
        });
        function validateReservationLibraryCode(){
            var code = $('#libraryCode').html();
            $.ajax({
                url:'/validateLibraryCode.action',
                data:{
                    "libraryCode":code,
                },
                type:'get',
                success:function (data) {
                    if(data=='yes') {
                        $('#msg').text('可预定');
                        $('#submitButton').attr("disabled",false);
                    }else{
                        $('#msg').text('不可预定');
                    }
                },
                error:function () {
                    alert("错误");
                }
            });
        }
        function check() {
            var data=$('#form2').serialize();
                $.ajax({
                    url:'/addReservation.action',
                    data:data,
                    type:'post',
                    success:function (data) {
                        data=eval("("+data+")");
                        if(data.result=='yes'){
                            location.reload();
                        }else {
                            $('#msg').html(data.msg);
                            setTimeout(function (){
                            },5000);
                        }
                    }
                });
        }
    </script>
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
                    </button>
                    <a class="navbar-brand" href="/index">图书馆</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">预定状况</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">功能菜单<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/toAddReservation.action">预定书籍</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="/queryLoan.action">查询借阅状况</a>
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
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">预定<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a id="reserve" href="#">预定</a>
                                    <%--<a data-toggle="modal" id="reserve" href="#">预定</a>--%>
                                </li>
                            </ul>
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
                                    <th>预定日期</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="BL" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="center">${BL.title.name}</td>
                                    <td class="center">${BL.reserveDate}</td>
                                    <td class="center">
                                        <a class="btn btn-danger" href="/cancelReservation.action?reservationId=${BL.id}">取消预定</a>
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
</div>
<div class="modal fade" id="modallogin" tabindex="-1" role="dialog" aria-labelledby="modallogin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">预定页面</h4>
            </div>
            <form name="form2" id="form2" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="control-label">LibraryCode</label>
                        <input type="text" class="form-control" id="libraryCode" name="libraryCode">
                    </div>
                    <div class="modal-footer">
                        <span ><label class="msg" id="msg" name="msg" value='msg'></label></span>
                        <button type="button" class="btn btn-default" id="submitButton" onclick="javascript:check(); disabled">提交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    $("#libraryCode").blur(function () {
        validateReservationLibraryCode();
    });
    $("#libraryCode").focus(function () {
        $("#submitButton").attr("disables","disabled");
    })
</script>
</body>
</html>
