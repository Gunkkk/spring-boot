<%@ page import="com.course.login.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/10/31
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<%User user = (User) session.getAttribute("user");%>
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
        //还书检查是否逾期
        function checkCompensation() {
            var code = $('#libraryCodeReturn').val();
            $.ajax({
                url:'/checkCompensation.action',
                data:{
                    "libraryCode":code,
                },
                type:'get',
                success:function (data) {
                    if(data=='-2')
                    {
                        $('#checkCompensation').text('不存在该libraryCode！');
                    }
                    else if(data=='-1')
                    {
                        $('#checkCompensation').text('该书项未被借出！');
                    }
                    else if(data=='0') {
                        $('#checkCompensation').text('在截止日期之前！');
                        $('#returnItem').attr("disabled",false);
                    }else{
                        $('#checkCompensation').text('在截止日期之后！请偿还'+data+'元');
                        $('#returnItem').attr("disabled",false);
                    }
                },
                error:function () {
                    alert("图书码输入错误");
                }
            });
        }

        //丢失书项查看赔偿金额
        function checkLoseCompensation() {
            var code = $('#libraryCodeLose').val();
            var No = $('#cardNoLose').val();
            $.ajax({
                url:'/checkLoseCompensation.action',
                data:{
                    "libraryCodeLose":code,
                    "cardNoLose":No,
                },
                type:'get',
                success:function (data) {
                    if (data == '-3') {
                        $('#checkLoseCompensation').text('不存在该libraryCode！');
                    }
                    else if (data == '-2') {
                        $('#checkLoseCompensation').text('该书项未被借出！');
                    }
                    else if (data == '-1') {
                        $('#checkLoseCompensation').text('该学生未借过这本书！');
                    }
                    else {
                        $('#checkLoseCompensation').text('丢失+逾期(如果有)，共偿还' + data + '元');
                        $('#loseItem').attr("disabled", false);
                    }
                }
                error:function () {
                    alert("图书码输入错误");
                }
            });
        }

        //借书检查卡号是否有效
        function checkCardNo() {
            var code = $('#cardNo').val();
            $.ajax({
                url:'/checkCardNo.action',
                data:{
                    "cardNo":code,
                },
                type:'get',
                success:function (data) {
                    if(data=='验证通过') {
                        $('#checkCardNo').text('验证通过！');
                    }else{
                        $('#checkCardNo').text(data);
                    }
                },
                error:function () {
                    alert("错误");
                }
            });
        }
        //丢失书项时 检查卡号是否有效
        function checkCardNoLose() {
            var code = $('#cardNoLose').val();
            $.ajax({
                url:'/checkCardNo.action',
                data:{
                    "cardNo":code,
                },
                type:'get',
                success:function (data) {
                    if(data=='验证通过') {
                        $('#checkCardNoLose').text('验证通过！');
                    }else{
                        $('#checkCardNoLose').text(data);
                    }
                },
                error:function () {
                    alert("错误");
                }
            });
        }
        //借书时查看是否可借阅
        function checkCanLoan() {
            var code = $('#libraryCodeLoan').val();
            var No = $('#cardNo').val();
            $.ajax({
                url:'/checkCanLoan.action',
                data:{
                    "libraryCodeLoan":code,
                    "cardNo":No
                },
                type:'get',
                success:function (data) {
                    if(data=='已预约')
                    {
                        $('#checkCanLoan').text('已预约，可以借阅！');
                        $('#loanItem').attr("disabled",false);
                    }
                    else if(data=='可借阅')
                    {
                        $('#checkCanLoan').text('可以借阅！');
                        $('#loanItem').attr("disabled",false);
                    }
                    //'已借出或已被预约'或者'不存在'
                    else
                    {
                        $('#checkCanLoan').text(data);
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

            <label>借书</label>
                <form action="/loanItem.action" class="form-inline" role="form">
                     <div class="form-group">
                        <label>学生卡号</label>
                        <input type="text" class="form-control" id="cardNo" name="cardNo"
                               placeholder="cardNo" value="${cardNo}">
                        <label id="checkCardNo"></label>

                        <label>请输入libraryCode</label>
                        <input type="text" id="libraryCodeLoan" name="libraryCodeLoan" class="form-control" placeholder="${libraryCode}">
                        <button name="loanItem" id="loanItem" disabled>借阅</button>
                        <label id="checkCanLoan"></label>
                     </div>
                </form>

        </div>

        <div class="container-body">
            <label>还书</label>
            <form action="/returnItem.action" class="form-inline" role="form">
                <div class="form-group">
                    <label>请输入libraryCode</label>
                    <input type="text" id="libraryCodeReturn" name="libraryCodeReturn" class="form-control" placeholder="${libraryCodeReturn}">
                    <button name="returnItem" id="returnItem" disabled>返还</button>
                    <label id="checkCompensation"></label>
                </div>
            </form>

        </div>

        <div class="container-body">
            <label>丢失处理</label>
            <form action="loseItem.action" class="form-inline" role="form">
                <div class="form-group">
                    <label>学生卡号</label>
                    <input type="text" class="form-control" id="cardNoLose" name="cardNoLose"
                           placeholder="cardNoLose" value="${cardNoLose}">
                    <label id="checkCardNoLose"></label>

                    <label>请输入libraryCode</label>
                    <input type="text" id="libraryCodeLose" name="libraryCodeLose" class="form-control" placeholder="${libraryCodeLose}">
                    <button name="loseItem" id="loseItem" disabled>上报丢失</button>
                    <label id="checkLoseCompensation"></label>
                </div>
            </form>

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
<script>
    $("#returnItem").click(function () {
        document.getElementById('returnItem').submit();
        $("#returnItem").attr("disabled", "disabled");
    });
    $("#libraryCodeReturn").blur(function () {
        checkCompensation();
    });
    $("#libraryCodeReturn").focus(function () {
        $("#returnItem").attr("disabled","disabled");
    })


    $("#loanItem").click(function () {
        document.getElementById('loanItem').submit();
        $("#loanItem").attr("disabled", "disabled");
    });
    $("#cardNo").blur(function () {
        checkCardNo();
    });
    $("#cardNo").focus(function () {
        $("#loanItem").attr("disabled","disabled");
    })
    $("#libraryCodeLoan").blur(function () {
        checkCanLoan();
    });
    $("#libraryCodeLoan").focus(function () {
        $("#loanItem").attr("disabled","disabled");
    })

    $("#loseItem").click(function () {
        document.getElementById('loseItem').submit();
        $("#loseItem").attr("disabled", "disabled");
    });
    $("#cardNoLose").blur(function () {
        checkCardNoLose();
    });
    $("#cardNoLose").focus(function () {
        $("#loseItem").attr("disabled","disabled");
    })
    $("#libraryCodeLose").blur(function () {
        checkLoseCompensation();
    });
    $("#libraryCodeLose").focus(function () {
        $("#loseItem").attr("disabled","disabled");
    })
</script>

</body>
</html>
