<%--
  Created by IntelliJ IDEA.
  User: yanyufeng
  Date: 2017/12/7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%request.setAttribute("ctx", request.getContextPath()); %>
<head>
    <meta charset="utf-8">
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
    <script type="text/javascript" src="${ctx}/resources/js/jquery.paginate.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery.yhhDataTable.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/indexForPaging.js" ></script>

    <link rel="stylesheet" href="${ctx}/resources/seat/css/main.css"/>
    <script type="text/javascript" src="${ctx}/resources/seat/js/jquery.seat-charts.min.js"></script>

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
    <script>

    </script>
</head>
<body>

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
                                <li>
                                    <a href="/toSeatsIndex.action" target="_blank">预定座位</a>
                                </li>
                                <li>
                                    <a href="/showMySeats.action" target="_blank">我的座位</a>
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
                                    <th>${floorId}楼区域</th>
                                    <th>座位剩余数/座位总数</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${floorInfo}" var="BL" varStatus="i">
                                <tr>
                                    <td>${BL.partId}</td>
                                    <td class="center">${BL.seatNum - BL.seatedNum}/${BL.seatNum}</td>
                                    <td class="center">
                                        <a class="btn btn-danger" href="/initSeatsMap.action?partId=${BL.partId}&floorId=${floorId}">进入区域</a>
                                    </td>
                                </tr>
                                </tbody>
                                </c:forEach>
                            </table>

                        </div>

                        <%--<div class="index-all">--%>
                            <div class="demo index-demo">
                                <img class="bkg-pic" src='${ctx}/resources/seat/images/seat_bkg.png'/>
                                <div class="layout">
                                    <a onmouseover="javascript:this.style.border='1px solid yellow';" onmouseout="javascript:this.style.border='0px';" href="/initSeatsMap.action?partId=${floorId+2}&floorId=${floorId}" class="layout01"></a>
                                    <a onmouseover="javascript:this.style.border='1px solid yellow';" onmouseout="javascript:this.style.border='0px';" href="/initSeatsMap.action?partId=${floorId+3}&floorId=${floorId}" class="layout02"></a>
                                    <a onmouseover="javascript:this.style.border='1px solid yellow';" onmouseout="javascript:this.style.border='0px';" href="/initSeatsMap.action?partId=${floorId}&floorId=${floorId}" class="layout03"></a>
                                    <a onmouseover="javascript:this.style.border='1px solid yellow';" onmouseout="javascript:this.style.border='0px';" href="/initSeatsMap.action?partId=${floorId+1}&floorId=${floorId}" class="layout04"></a>
                                </div>
                                <div style="clear:both"></div>
                            </div>
                            <br/>
                        <%--</div>--%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
