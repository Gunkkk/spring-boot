$("#btn1").mouseover(function() {
	$("#btn1").attr("src","resources/images/btn1_1.png");
});
$("#btn1").mouseout(function() {
	$("#btn1").attr("src","resources/images/btn1.png");
});
$("#btn3").mouseover(function() {
	$("#btn3").attr("src","/resources/images/btn3_2.png");
});
$("#btn3").mouseout(function() {
	$("#btn3").attr("src","/resources/images/btn3_1.png");
});
$("#btn5").mouseover(function() {
	$("#btn5").attr("src","/resources/images/btn5_1.png");
});
$("#btn5").mouseout(function() {
	$("#btn5").attr("src","/resources/images/btn5.png");
});
var fm = document.getElementById("right_bar");
$(document).ready(function() {  
    $(window).scroll(function(){  
        $("#right_bar").stop();  
        $("#right_bar").animate({top:(document.documentElement.scrollTop || document.body.scrollTop)+500},100);  
    });  
}); 