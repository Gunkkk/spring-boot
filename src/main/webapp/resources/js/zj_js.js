$(".zj_position1").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("初升高学科选择(6选3)");
	$(".zj_position1").stop().animate({top: '-30px'});
});
$(".zj_position1").mouseleave(function(){
	$(".zj_position1").stop().animate({top: '0px'});
});
$(".zj_position2").mouseleave(function(){
	$(".zj_position2").stop().animate({top: '0px'});
});
$(".zj_position3").mouseleave(function(){
	$(".zj_position3").stop().animate({top: '0px'});
});
$(".zj_position4").mouseleave(function(){
	$(".zj_position4").stop().animate({top: '0px'});
});
$(".zj_position5").mouseleave(function(){
	$(".zj_position5").stop().animate({top: '0px'});
});
$(".zj_position6").mouseleave(function(){
	$(".zj_position6").stop().animate({top: '0px'});
});
$(".zj_position7").mouseleave(function(){
	$(".zj_position7").stop().animate({top: '0px'});
});
$(".zj_position8").mouseleave(function(){
	$(".zj_position8").stop().animate({top: '0px'});
});
$(".zj_position2").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("高考志愿规划");
	$(".zj_position2").stop().animate({top: '-30px'});
});
$(".zj_position3").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("高中生出国留学规划");
	$(".zj_position3").stop().animate({top: '-30px'});
});
$(".zj_position4").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("考研咨询");
	$(".zj_position4").stop().animate({top: '-30px'});
});
$(".zj_position5").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("研究生出国专业院校规划");
	$(".zj_position5").stop().animate({top: '-30px'});
});
$(".zj_position6").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("就业指导");
	$(".zj_position6").stop().animate({top: '-30px'});
});
$(".zj_position7").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").text("创业辅导");
	$(".zj_position7").stop().animate({top: '-30px'});
});
$(".zj_position8").mouseover(function(){
	// $(".zj_midtext").css("background-color","#E9E9E4");
	$("#zj_title").text("");
	$("#zj_content").html("教育从业者/师资培训<br/>生涯规划及心理健康教育课程");
	$(".zj_position8").stop().animate({top: '-30px'});
});
$(".zj_plate1").mouseover(function(){
	$(".zj_induction1").fadeTo("slow",0.7);
	$(".zj_plate1").css("filter","grayscale(80%)");
});
$(".zj_plate1").mouseleave(function(){
	$(".zj_induction1").hide();
	$(".zj_plate1").css("filter","grayscale(0%)");
});
$(".zj_plate2").mouseover(function(){
	$(".zj_induction2").fadeTo("slow",0.7);
	$(".zj_plate2").css("filter","grayscale(80%)");
});
$(".zj_plate2").mouseleave(function(){
	$(".zj_induction2").hide();
	$(".zj_plate2").css("filter","grayscale(0%)");
});
$(".zj_plate3").mouseover(function(){
	$(".zj_induction3").fadeTo("slow",0.7);
	$(".zj_plate3").css("filter","grayscale(80%)");
});
$(".zj_plate3").mouseleave(function(){
	$(".zj_induction3").hide();
	$(".zj_plate3").css("filter","grayscale(0%)");
});
$(".zj_plate4").mouseover(function(){
	$(".zj_induction4").fadeTo("slow",0.7);
	$(".zj_plate4").css("filter","grayscale(80%)");
});
$(".zj_plate4").mouseleave(function(){
	$(".zj_induction4").hide();
	$(".zj_plate4").css("filter","grayscale(0%)");
});
$("li.dropdown").hover(function(e) {
    
    $(this).children(".dropdown-menu").delay(100).show(200);
  },

  function(e){
    $(this).children(".dropdown-menu").delay(100).hide(200);
  }
  );
  $(function() {
        var bannerSlider = new Slider($('#banner_tabs'), {
            time: 5000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('#bannerCtrl'),
            activeControllerCls: 'active'
        });
        $('#banner_tabs .flex-prev').click(function() {
            bannerSlider.prev()
        });
        $('#banner_tabs .flex-next').click(function() {
            bannerSlider.next()
        });
    })

$(function (){
        setInterval(function () {
            $('#comment ul li:last').hide().insertBefore($("#comment ul li:first")).slideDown(1000);
          }, 5000);
    });