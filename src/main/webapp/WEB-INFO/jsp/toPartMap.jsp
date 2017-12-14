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

        var price = 80; //票价
        $(document).ready(function() {
            // var $cart = $('#selected-seats'), //座位区
                var $cart = $('#seatForm'),
                $counter = $('#counter'), //总选择座位数
                // $total = $('#total'); //总计金额

                $num = 0,//该用户已选择座位数*****************************************************
                $limit_num = 1;//一人最多座位数***************************************************

            <c:if test="${partId%2==1}">
            var sc = $('#seat-map').seatCharts({
                map: [  //座位图
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___a________',
                    '___a________',
                    '____________',
                    '___a________',
                    '___a________',
                    '____________',
                    '___a________',
                    '___a________',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                    '____________',
                    '___aaaaaa__a',
                    '___aaaaaa__a',
                ],
                naming : {
                    top : false,
                    getLabel : function (character, row, column) {
                        return column;
                    }
                },
                legend : { //定义图例
                    node : $('#legend'),
                    items : [
                        [ 'a', 'available',   '可选座'],
                        [ 'a', 'unavailable', '已售出']
                    ]

                },

                click: function () { //点击事件
                    if (this.status() == 'available') { //可选座
                        if($num < $limit_num){
                            // $('<li>'+(this.settings.row+1)+'_'+this.settings.label+'<span id="cancel-span" class="cancel-cart-item"></span></li>')
                            //     .attr('id', 'cart-item-'+this.settings.id)
                            //     .data('seatId', this.settings.id)
                            //     //取得已选择的座位号************************************************
                            //     .appendTo($cart);

                            $("#row_col").val((this.settings.row+1)+'_'+this.settings.label);

                            // $('<input class="form-control" id="row_col" placeholder='+(this.settings.row+1)+'_'+this.settings.label+'>')
                            //     .appendTo($cart);
                            $counter.text(sc.find('selected').length+1);
                            $num++;
                            // $total.text(recalculateTotal(this));
                            $('#tree-btn').css({'background':'#386de8'}).attr('class','availablebtn')
                            return 'selected';
                        }
                        else
                        {
                            return this.style();
                        }
                    }
                    else if (this.status() == 'selected')//已选中
                    {
                        //更新数量
                        $counter.text(sc.find('selected').length-1);
                        //更新总计
                        // $total.text(recalculateTotal(this));

                        //删除已预订座位
                        $('#cart-item-'+this.settings.id).remove();
                        $num--;
                        //可选座
                        return 'available';
                    }
                    else if (this.status() == 'unavailable') //已售出
                    {
                        return 'unavailable';
                    }
                    else
                    {
                        return this.style();
                    }
                }
            });
            </c:if>
            <c:if test="${partId%2==0}">
            var sc = $('#seat-map').seatCharts({
                map: [  //座位图
                    'aa_aa_aa_aa_aa_aa_aa',
                    'aa_aa_aa_aa_aa_aa_aa',
                    'aa_aa_aa_aa_aa_aa_aa',
                    'aa_aa_aa_aa_aa_aa_aa',
                    '____________________',
                    'aa_aa_aa_aa_aa_aa_aa',
                    'aa_aa_aa_aa_aa_aa_aa',
                    'aa_aa_aa_aa_aa_aa_aa',
                ],
                naming : {
                    top : false,
                    getLabel : function (character, row, column) {
                        return column;
                    }
                },
                legend : { //定义图例
                    node : $('#legend'),
                    items : [
                        [ 'a', 'available',   '可选座'],
                        [ 'a', 'unavailable', '已售出']
                    ]

                },

                click: function () { //点击事件
                    if (this.status() == 'available') { //可选座
                        if($num < $limit_num){
                            // $('<li>'+(this.settings.row+1)+'_'+this.settings.label+'<span id="cancel-span" class="cancel-cart-item"></span></li>')
                            //     .attr('id', 'cart-item-'+this.settings.id)
                            //     .data('seatId', this.settings.id)
                            //     //取得已选择的座位号************************************************
                            //     .appendTo($cart);

                            $("#row_col").val((this.settings.row+1)+'_'+this.settings.label);

                            // $('<input class="form-control" id="row_col" placeholder='+(this.settings.row+1)+'_'+this.settings.label+'>')
                            //     .appendTo($cart);
                            $counter.text(sc.find('selected').length+1);
                            $num++;
                            // $total.text(recalculateTotal(this));
                            $('#tree-btn').css({'background':'#386de8'}).attr('class','availablebtn')
                            return 'selected';
                        }
                        else
                        {
                            return this.style();
                        }
                    }
                    else if (this.status() == 'selected')//已选中
                    {
                        //更新数量
                        $counter.text(sc.find('selected').length-1);
                        //更新总计
                        // $total.text(recalculateTotal(this));

                        //删除已预订座位
                        $('#cart-item-'+this.settings.id).remove();
                        $num--;
                        //可选座
                        return 'available';
                    }
                    else if (this.status() == 'unavailable') //已售出
                    {
                        return 'unavailable';
                    }
                    else
                    {
                        return this.style();
                    }
                }
            });
            </c:if>

            var Ywidth=$('.seatCharts-row div:last-child').width();
            $('div.seatCharts-cell').css('height',Ywidth);
            var Wwidth=$('div.seatCharts-container').width();
            $('div.seatCharts-container').css('width',eval(15+Wwidth)+'px');

            //输入已售出的座位**************************************************************
            sc.get([${seatedString}]).status('unavailable');
            $('#selected-seats').on('click', '.cancel-cart-item', function () {
                //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                sc.get($(this).parents('li:first').data('seatId')).click();
            });
        });

        //计算总金额
        // var total = 0;
        // function recalculateTotal(ths) {
        // 	if(ths.status() == 'available'){
        // 		total += price;
        // 	}else{
        // 		total -= price;
        // 	}
        // 	return total;
        // };
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

                <div id="main">
                    <div class="demo">
                        <c:if test="${partId%2==1}">
                            <img class="seat-pic" src='${ctx}/resources/seat/images/shushu1_03copy.jpg'/>
                        </c:if>
                        <c:if test="${partId%2==0}">
                            <img class="seat-pic" src='${ctx}/resources/seat/images/shushu1_04copy.jpg'/>
                        </c:if>
                        <div id="seat-map">

                        </div>
                        <div style="clear:both"></div>
                    </div>
                    <%--<div class="footer">--%>
                        <%--&lt;%&ndash;<p class="foot-p">您已选择了</p>&ndash;%&gt;--%>
                            <%--<ul id="selected-seats"></ul>--%>
                        <%--<!--<p class="foot-pp">总计：<b>￥<span id="total">0</span></b></p> -->--%>
                            <%--<button id="tree-btn" type="button">确认</button>--%>
                    <%--</div>--%>
                    <div class="footer">
                        <form action="/reserveSeats.action" class="form-inline" name="seatForm" id="seatForm" style="display: none">
                            <div  class="form-group">
                                <input class="form-control" id="floorId" name="floorId" value=${floorId}>
                            </div>
                            <div  class="form-group">
                                <input class="form-control" id="partId" name="partId" value=${partId}>
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="row_col" name="row_col" value="">
                            </div>
                        </form>
                        <button id="tree-btn" name="tree-btn" type="button">确认</button>
                    </div>
                    </div>
                    <br/>
                </div>


            </div>
        </div>
    </div>
</div>
<script>
    $("#tree-btn").click(function () {
        document.getElementById('seatForm').submit();
        $("#tree-btn").attr("disabled", "disabled");
    });
</script>
</body>
</html>
