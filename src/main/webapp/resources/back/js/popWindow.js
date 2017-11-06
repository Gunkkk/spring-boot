$(".zj_button1").click(function(){
    var $this = $(this);
	$(".zj_popWindow1").show();
	$(".zj_shade").show();
});

$(".zj_button2").click(function(){
    var $this = $(this);
    $(".zj_popWindow2").show();
    $(".zj_shade").show();
    //主要语句
    $("input[name='idUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='userNameUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='cardNoUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().text());
    $("input[name='passwordUpdate']").val($this.parent().prev().prev().prev().prev().prev().text());
    $("input[name='typeUpdate']").val($this.parent().prev().prev().prev().prev().text());
    $("input[name='departmentUpdate']").val($this.parent().prev().prev().prev().text());
    $("input[name='majorUpdate']").val($this.parent().prev().prev().text());
    $("input[name='directorUpdate']").val($this.parent().prev().text());

});
$(".zj_button3").click(function(){
    var $this = $(this);
    $(".zj_popWindow2").show();
    $(".zj_shade").show();
    //主要语句
    $("input[name='idUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='userNameUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().text());
    $("input[name='cardNoUpdate']").val($this.parent().prev().prev().prev().prev().prev().text());
    $("input[name='passwordUpdate']").val($this.parent().prev().prev().prev().prev().text());
    $("input[name='typeUpdate']").val($this.parent().prev().prev().prev().text());
    $("input[name='departmentUpdate']").val($this.parent().prev().prev().text());
    $("input[name='majorUpdate']").val($this.parent().prev().text());

});
$(".book_button1").click(function(){
    var $this = $(this);
    $(".zj_popWindow1").show();
    $(".zj_shade").show();
});
$(".book_button2").click(function(){
    var $this = $(this);
    $(".zj_popWindow2").show();
    $(".zj_shade").show();
    //主要语句
    $("input[name='idUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='nameUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='authorUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='isbnUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='totalNumberUpdate']").val($this.parent().prev().prev().prev().prev().prev().text());
    $("input[name='priceUpdate']").val($this.parent().prev().prev().prev().prev().text());
    $("input[name='pressUpdate']").val($this.parent().prev().prev().prev().text());
    $("input[name='publishDateUpdate']").val($this.parent().prev().prev().text());
    $("input[name='versionUpdate']").val($this.parent().prev().text());
});
$(".magazine_button1").click(function(){
    var $this = $(this);
    $(".zj_popWindow1").show();
    $(".zj_shade").show();
});
$(".magazine_button2").click(function(){
    var $this = $(this);
    $(".zj_popWindow2").show();
    $(".zj_shade").show();
    //主要语句
    $("input[name='idUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='nameUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='authorUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().prev().prev().text());
    $("input[name='isbnUpdate']").val($this.parent().prev().prev().prev().prev().prev().prev().text());
    $("input[name='totalNumberUpdate']").val($this.parent().prev().prev().prev().prev().text());
    $("input[name='priceUpdate']").val($this.parent().prev().prev().prev().text());
    $("input[name='pressUpdate']").val($this.parent().prev().prev().text());
    $("input[name='volumeUpdate']").val($this.parent().prev().text());
});
function doHide1(e){
	$(".zj_popWindow1").hide();
	$(".zj_shade").hide();
    $(".input[name='userNameAdd']").val("");
    $(".input[name='cardNoAdd']").val("");
    $(".input[name='passwordAdd']").val("");
    $(".input[name='departmentAdd']").val("");
    $(".input[name='majorAdd']").val("");
    $(".input[name='directorAdd']").val("");
}
function doHide2(e){
    $(".zj_popWindow2").hide();
    $(".zj_shade").hide();
    $("input[name='idUpdate']").val("");
    $(".input[name='userNameUpdate']").val("");
    $(".input[name='cardNoUpdate']").val("");
    $(".input[name='passwordUpdate']").val("");
    $(".input[name='typeUpdate']").val("");
    $(".input[name='departmentUpdate']").val("");
    $("input[name='majorUpdate']").val("");
    $("input[name='directorUpdate']").val("");
}
function doHideBook1(e){
    $(".zj_popWindow1").hide();
    $(".zj_shade").hide();
    $(".input[name='nameAdd']").val("");
    $(".input[name='authorAdd']").val("");
    $(".input[name='isbnAdd']").val("");
    $(".input[name='totalNumberAdd']").val("");
    $(".input[name='priceAdd']").val("");
    $(".input[name='pressAdd']").val("");
    $(".input[name='publishDateAdd']").val("");
    $(".input[name='versionAdd']").val("");

}
function doHideBook2(e){
    $(".zj_popWindow2").hide();
    $(".zj_shade").hide();
    $(".input[name='nameUpdate']").val("");
    $(".input[name='authorUpdate']").val("");
    $(".input[name='isbnUpdate']").val("");
    $(".input[name='priceUpdate']").val("");
    $(".input[name='totalNumberUpdate']").val("");
    $(".input[name='pressUpdate']").val("");
    $(".input[name='publishDateUpdate']").val("");
    $(".input[name='versionUpdate']").val("");
}

function doHideMagazine1(e){
    $(".zj_popWindow1").hide();
    $(".zj_shade").hide();
    $(".input[name='nameAdd']").val("");
    $(".input[name='authorAdd']").val("");
    $(".input[name='isbnAdd']").val("");
    $(".input[name='totalNumberAdd']").val("");
    $(".input[name='priceAdd']").val("");
    $(".input[name='pressAdd']").val("");
    $(".input[name='volumeAdd']").val("");

}
function doHideMagazine2(e){
    $(".zj_popWindow2").hide();
    $(".zj_shade").hide();
    $(".input[name='nameUpdate']").val("");
    $(".input[name='authorUpdate']").val("");
    $(".input[name='isbnUpdate']").val("");
    $(".input[name='priceUpdate']").val("");
    $(".input[name='totalNumberUpdate']").val("");
    $(".input[name='pressUpdate']").val("");
    $(".input[name='volumeUpdate']").val("");
}
$("#zj_check1").click(function () {
    document.getElementById('add').submit();
    $("#zj_check1").attr("disabled", "disabled");
});
$("#zj_check2").click(function () {
    document.getElementById('edit').submit();
    $("#zj_check2").attr("disabled", "disabled");
});

$(document).ready(
	function() {
//         var mx = 0, my = 0; //记录鼠标位置
//         var dx = 0, dy = 0; //记录对话框位置
//         var isDrag = false;
//         $(".zj_popTop").mousedown(function (e) {
//             e = e || window.event;
//             mx = e.pageX;
//             my = e.pageY;
//             dx = $(".zj_popWindow1").offset().left;
//             dy = $(".zj_popWindow1").offset().top;
//             isDrag = true;
//         });
//         $(".zj_popWindow1").mouseup(function () {
//             isDrag = false;
//         });
//         $(document).mousemove(function (e) {
//             var e = e || window.event;
//             var x = e.pageX;      //移动时鼠标X坐标
//             var y = e.pageY;      //移动时鼠标Y坐标
//             if (isDrag) {        //判断对话框能否拖动
//                 var moveX = dx + x - mx;      //移动后对话框新的left值
//                 var moveY = dy + y - my;      //移动后对话框新的top值
//                 //设置拖动范围
//                 var pageW = $(window).width();
//                 var pageH = $(window).height();
//                 var dialogW = $(".zj_popWindow1").width();
//                 var dialogH = $(".zj_popWindow1").height();
//                 var maxX = pageW - dialogW;       //X轴可拖动最大值
//                 var maxY = pageH - dialogH;       //Y轴可拖动最大值
//                 moveX = Math.min(Math.max(0, moveX), maxX);     //X轴可拖动范围
//                 moveY = Math.min(Math.max(0, moveY), maxY);     //Y轴可拖动范围
//                 //重新设置对话框的left、top
//                 $(".zj_popWindow1").css({"left": moveX + 'px', "top": moveY + 'px'});
//             }
//             ;
//         });
//         $dialog = $(".zj_popWindow1");
//
        function autoCenter(el) {
            var bodyW = $(window).width();
            var bodyH = $(window).height();
            var elW = el.width();
            var elH = el.height();
            $(".zj_popWindow1").css({"left": (bodyW - elW) / 2 + 'px', "top": (bodyH - elH) / 2 + 'px'});
            $(".zj_popWindow2").css({"left": (bodyW - elW) / 2 + 'px', "top": (bodyH - elH) / 2 + 'px'});
        };
        window.onresize = function () {
            autoCenter($dialog)
        };
        autoCenter($(".zj_popWindow1"));
        autoCenter($(".zj_popWindow2"));
     }
);



