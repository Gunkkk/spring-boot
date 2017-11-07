$("#btn1").mouseover(function() {
	$("#btn1").attr("src","../../../resources/images/btn1_1.png");
});
$("#btn1").mouseout(function() {
	$("#btn1").attr("src","../../../resources/images/btn1.png");
});
$("#btn5").mouseover(function() {
	$("#btn5").attr("src","../../../resources/images/btn5_1.png");
});
$("#btn5").mouseout(function() {
	$("#btn5").attr("src","../../../resources/images/btn5.png");
});
$("li.dropdown").hover(function(e) {
    $(this).children(".dropdown-menu").delay(100).show(200);
  	},
  	function(e){
    $(this).children(".dropdown-menu").delay(100).hide(200);
  	}
  );