//左上 对 左下
function position_ltTolb(src, target) {
	var srcPosition = src.offset();
	var targetPosition = target.offset();
	
	targetTop = targetPosition.top;
	targetBottom = targetTop + target.height();
	targetLeft = targetPosition.left;
	targetRight = targetPosition.left + target.width();
	
	srcLeft = targetLeft;
	srcTop = targetBottom;
	
	src.css('left', srcLeft).css('top', srcTop).css('display', "block");
}

//浏览器中间, 往上70px，符合浏览习惯，跟alert保持一致
function centerBrowser(target) {
	var itop=($(window).height()-target.height())/2  + $(document).scrollTop() - 50;
    var ileft=($(window).width()-target.width())/2  + $(document).scrollLeft() - 20;
    
    target.css("top", itop).css("left",ileft).css('display', "block");
}

function position_rtTort(src, target) {
	
	margin_Left = target.width() - src.width();
	margin_top = 0;
	
	var srcClone = src.clone();
	
	srcClone.css('margin-left', margin_Left);
	srcClone.css('margin-top', margin_top);
	srcClone.css('display', "block");

	
	target.prepend(srcClone);
}

function position_rtTort(src, target) {
	
	margin_Left = target.width() - src.width();
	margin_top = 0;
	
	var srcClone = src.clone();
	
	srcClone.css('margin-left', margin_Left);
	srcClone.css('margin-top', margin_top);
	srcClone.css('display', "block");

	
	target.prepend(srcClone);
}

/*function position_ltTolb(src, target) {
	
	margin_Left = target.width() - src.width();
	margin_top = 0;
	
	var srcClone = src.clone();
	var position = target.offset();
	srcClone.css('left', position.left);
	srcClone.css('top', position.top + target.height();
	srcClone.css('display', "block");
	
	target.prepend(srcClone);
}*/

function position_move_rtTort(src, target) {
	
/*	margin_Left = target.width() - src.width();
	margin_top = 0;
	
	src.css('margin-left', margin_Left);
	src.css('margin-top', margin_top);
	src.css('display', "block");

	
	target.prepend(src);*/
	
	var position = target.offset();
	src.css('left', position.left);
	src.css('top', position.top);
	src.css('display', "block");

	$("body").prepend(src);
}

function position_move_ltTolb(src, target) {
	
	/*	margin_Left = target.width() - src.width();
		margin_top = 0;
		
		src.css('margin-left', margin_Left);
		src.css('margin-top', margin_top);
		src.css('display', "block");

		
		target.prepend(src);*/
		
		var position = target.offset();
		src.css('left', position.left);
		src.css('top', position.top + target.height() + 2);
	}

function position_move_rtTolb(src, target) {
		
		var position = target.offset();
		src.css('left', position.left - src.width() - 2);
		src.css('top', position.top + target.height() + 2);
	}

function position_move_rbTolt(src, target) {
	
	var position = target.offset();
	src.css('left', position.left - src.width() - 2);
	src.css('top', position.top - src.height() - 2);
}

function position_move_lbTolt(src, target) {
	
	var position = target.offset();
	src.css('left', position.left);
	src.css('top', position.top - target.height() - 2);
}

function position_move_lmTolm(src, target) {
	
	/*	margin_Left = target.width() - src.width();
		margin_top = 0;
		
		src.css('margin-left', margin_Left);
		src.css('margin-top', margin_top);
		src.css('display', "block");

		
		target.prepend(src);*/
		
		var position = target.offset();
		src.css('left', position.left);
		var pri = target.height() - src.height();
		src.css('top', position.top + pri/2);
	}

function getObjectFromArray(arrays, name, value) {
	var obj = null;
	for (var i = 0; i <arrays.length; i++) {
		var object = arrays[i];
		if (object[name] == value) {
			obj = object;
			break;
		}
	}
	
	return obj;
}