<%--
  Created by IntelliJ IDEA.
  User: 84074
  Date: 2017/10/31
  Time: 9:36
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
            <button class="magazine_button1 btn btn-warning" style="float:right">增加杂志</button>
            <form action="/queryMagazine.action" class="form-inline" role="form">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" class="form-control" id="id" name="id"
                           placeholder="id">
                </div>
                <div class="form-group">
                    <label>书名</label>
                    <input type="text" class="form-control" id="name" name="name"
                           placeholder="书名">
                </div>
                <div class="form-group">
                    <label>作者</label>
                    <input type="text" class="form-control" id="author" name="author"
                           placeholder="作者">
                </div>
                <div class="form-group">
                    <label>ISBN</label>
                    <input type="text" class="form-control" id="isbn" name="isbn"
                           placeholder="">
                </div>

                <button onclick="form.submit();" class="btn btn-default">查询</button>
            </form>
            <div class="box-content">
                <table class="table table-striped table-bordered" id="testtable1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>ID</th>
                        <th>书名</th>
                        <th>作者</th>
                        <th>类型</th>
                        <th>ISBN</th>
                        <th>被借数</th>
                        <th>总数</th>
                        <th>价格</th>
                        <th>出版社</th>
                        <th>Volume</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page}" var="BL" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td class="center">${BL.id}</td>
                        <td class="center">${BL.name}</td>
                        <td class="center">${BL.author}</td>
                        <td class="center">${BL.type}</td>
                        <td class="center">${BL.isbn}</td>
                        <td class="center">${BL.borrowedNumber}</td>
                        <td class="center">${BL.totalNumber}</td>
                        <td class="center">${BL.price}</td>
                        <td class="center">${BL.press}</td>
                        <td class="center">${BL.volume}</td>
                        <td class="center">
                            <button class="magazine_button2 btn btn-success">修改</button>
                            <a class="btn btn-danger" href="/deleteMagazine.action?id=${BL.id}">删除</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>

<%--                <div align="right">
                    <table>
                        <tr>
                            &lt;%&ndash;<form height="16px" class="recode">&ndash;%&gt;
                            <td><span id="recode1" class="recode">总记录数：${page.getTotalElements()}条</span></td>
                            <td><a href=""><img src="${ctx}/resources/image/page/page_home.png" class="recode"></a></td>
                            <c:if test="${page.getNumber()>0}">
                                <td><a href="/queryBook.action?currentPage=0"><img src="${ctx}/resources/image/page/page_prev.png" class="recode"></a></td>
                            </c:if>
                            <c:if test="${page.getNumber()+1<page.getTotalPages()}">
                                <td><a href=""><img src="${ctx}/resources/image/page/page_next.png" class="recode"></a></td>
                            </c:if>
                            <td><a href="javascript:page()"><img src="${ctx}/resources/image/page/page_end.png" class="recode"></a></td>
                            <td><span id="recode2" class="recode">第${(page.getNumber()+1)}页/共${page.getTotalPages()}页&nbsp;&nbsp;转到</span></td>


                            &lt;%&ndash;下面的input标签顺序不要变&ndash;%&gt;
                            &lt;%&ndash;<input type="hidden" name="no" value="${pb.beanList.no}">&ndash;%&gt;
                            <td>
                                <input  type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id="pc">
                            </td>
                            <td>
                                &lt;%&ndash;<input type="hidden" id="url" value="${pb.url}">&ndash;%&gt;
                                <input type="hidden" id="tp" value="${page.getTotalPages()}">
                                <input type="image" class="recode" src="${ctx}/resources/image/page/page_go.png" onclick="go();return false;"/>
                            </td>

                            &lt;%&ndash;<td><a href="checkOneProject.action?no=1&projectId=20170400&pc=${projectUser.pc}"><img src="../../../../image/page/page_go.png"></a></td>&ndash;%&gt;

                        </tr>
                    </table>
                </div>--%>

            </div>
        </div>
    </div>

</div>
<div style="display: none;" class="zj_popWindow1">
    <div class="zj_popTop">
        <img onclick="doHideMagazine1(this)" src="${ctx}/resources/back/img/zj_end.png">
    </div>
    <div class="zj_popBottom">
        <form action="/addMagazine.action" class="form-inline" name="addForm" id="addForm">
            <div class="form-group">
                <label>书名</label>
                <input type="text" class="form-control" id="nameAdd" name="nameAdd"
                       placeholder="请输入杂志名">
            </div>
            <div class="form-group">
                <label>作者</label>
                <input type="text" class="form-control" id="authorAdd" name="authorAdd"
                       placeholder="请输入作者">
            </div>
            <div class="form-group">
                <label>ISBN</label>
                <input type="text" class="form-control" id="isbnAdd" name="isbnAdd"
                       placeholder="请输入ISBN">
            </div>
            <div class="form-group">
                <label>总数</label>
                <input type="text" class="form-control" id="totalNumberAdd" name="totalNumberAdd"
                       placeholder="请输入总数">
            </div>
            <div class="form-group">
                <label>价格</label>
                <input type="text" class="form-control" id="priceAdd" name="priceAdd"
                       placeholder="请输入价格">
            </div>
            <div class="form-group">
                <label>出版社</label>
                <input type="text" class="form-control" id="pressAdd" name="pressAdd"
                       placeholder="请输入出版社">
            </div>
            <div class="form-group">
                <label>Volume</label>
                <input type="text" class="form-control" id="volumeAdd" name="volumeAdd"
                       placeholder="请输入Volume">
            </div>

        </form>
        <div class="zj_check1">
            <button name="addUser" id="addUser">
                <img src="${ctx}/resources/back/img/zj_check.png">&nbsp;&nbsp;提交
            </button>
        </div>
        <div class="zj_cancle">
            <button onclick="doHideMagazine1(this)">
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
        <form action="/changeMagazine.action" class="form-inline" name="addForm" id="editForm">
            <div class="form-group" hidden="hidden">
                <label>ID</label>
                <input type="text" class="form-control" id="idUpdate" name="idUpdate"
                       placeholder="" readonly>
            </div>
            <div class="form-group">
                <label>书名</label>
                <input type="text" class="form-control" id="nameUpdate" name="nameUpdate"
                       placeholder="请输入杂志名">
            </div>
            <div class="form-group">
                <label>作者</label>
                <input type="text" class="form-control" id="authorUpdate" name="authorUpdate"
                       placeholder="请输入作者">
            </div>
            <div class="form-group">
                <label>ISBN</label>
                <input type="text" class="form-control" id="isbnUpdate" name="isbnUpdate"
                       placeholder="请输入ISBN">
            </div>
            <div class="form-group">
                <label>总数</label>
                <input type="text" class="form-control" id="totalNumberUpdate" name="totalNumberUpdate"
                       placeholder="请输入总数">
            </div>
            <div class="form-group">
                <label>价格</label>
                <input type="text" class="form-control" id="priceUpdate" name="priceUpdate"
                       placeholder="请输入价格">
            </div>
            <div class="form-group">
                <label>出版社</label>
                <input type="text" class="form-control" id="pressUpdate" name="pressUpdate"
                       placeholder="请输入出版社">
            </div>
            <div class="form-group">
                <label>Volume</label>
                <input type="text" class="form-control" id="volumeUpdate" name="volumeUpdate"
                       placeholder="请输入Volume">
            </div>


        </form>
        <div class="zj_check1">
            <button name="editUser" id="editUser">
                <img src="${ctx}/resources/back/img/zj_check.png">&nbsp;&nbsp;提交
            </button>
        </div>
        <div class="zj_cancle">
            <button onclick="doHideMagazine2(this)">
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
