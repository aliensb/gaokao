$(function() {
	tm_centerdialog();
	$(window).resize(function() {
		tm_centerdialog();
	});
});
function tm_centerdialog() {
	var width = $("#dialog").width();
	var height = $("#dialog").height();
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var left = windowWidth / 2 - width / 2;
	var top = windowHeight / 2 - height / 2;
	$("#dialog").css({
		left : left,
		top : top
	});
}
