function cancelS(ths){
    var $parent=$(ths).parent("li");
    var $value = $parent.attr("id");
    var $shuzi = $value.substring(10);
    $('.seatCharts-row').find('div#'+$shuzi).removeClass('selected').addClass('available');
    $parent.remove();
    $('#total').text($('#total').text()-price);
    $('#counter').text($('#seat-map').find('selected').length-1);
    return 'available';
    recalculateTotal(sc);
}












