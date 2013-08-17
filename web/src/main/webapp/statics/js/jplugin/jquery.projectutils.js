	(function($) {

		$.suggest = function(input, options) {
			
		}
		
		/**
		 * 单项修改操作
		 * 通过链接的方式提交数据并修改成功，返回referer到修改页面
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 * 3、options：参数（生成修改项）
		 */
		$.fn.modifyAjax = function(title, option, callbackOption) {
			$(this).each(function() {
				
				$(this).click(function(){
					var thisObj = $(this);
					var id = new Date().getTime();
					showDialog($(this), id);
					$("#" + id + " .projectutils_modify_dialog_cancel").click(function() {
						$("#"+id).remove();
						return false;
					});
					$("#" + id + " .projectutils_modify_dialog_submit").click(function() {
						$.ajax({
							   type: "post",
							   url: $("#form" + id).attr("action"),
							   data:$("#form" + id).serialize(),
							   dataType: "json",
							   success: function(data){
								   //alert(data.resultCode);
								   callbackOption.callbackFun.apply(thisObj, [{"ajaxData":data, "obj":callbackOption.obj}]);
							   }
							 });
						$("#"+id).remove();
						return false;
					});
					return false;
				});
			});
			
			
			//加入body并定位显示
			function showDialog(aObj, id) {

				var html = "";
				html += "<div id='" + id + "' class='projectutils_modify_dialog'>";
				html += "<div>" + title + "</div>";
				html += "<form id='form" + id + "' method='post' action='" + aObj.attr("href") + "'>";
				for (var i = 0; i < option.length; i++) {
					var obj = option[i];
					
					html += "	 <label for='" + obj.name + "'>" + obj.label + "</label>";
					
					var initValue = '';
					if (obj.value != undefined) {
						initValue = obj.value;
					}
					
					if (obj.type == 'text') {
						html += "    <input name='" + obj.name + "' type='text' value='" + initValue + "'></input>";
					} else if (obj.type == 'textarea') {
						html += "    <textarea id='" + obj.name + "' name='" + obj.name + "'>" + initValue + "</textarea>";
					}
				}
				html += "	 <button onclick='return false;' class='projectutils_modify_dialog_submit' type='submit'>确定</button>";
				html += "	 <button class='projectutils_modify_dialog_cancel'>取消</button>";
				html += "</form>";
				html += "</div>";
				
				$('body').append(html);
				var position = aObj.offset();
				if ($(window).width()-$("#" + id).width()-10 < position.left) {
					if ($(window).height() - $("#" + id).height()-10 + $(document).scrollTop() - 50 < position.top) {
						position_move_rbTolt($("#" + id), aObj);
					} else {
						position_move_rtTolb($("#" + id), aObj);
					}
				} else if($(window).height() - $("#" + id).height()-10 < position.top) {
					position_move_lbTolt($("#" + id), aObj);
				}
				else {
					position_move_ltTolb($("#" + id), aObj);
				}

				$("#" + id).show();
			}
		}
		
		/**
		 * 单项修改操作
		 * 通过链接的方式提交数据并修改成功，返回referer到修改页面
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 * 3、options：参数（生成修改项）
		 */
		$.fn.modifyM = function(title, option) {
			
			$(this).each(function() {
				
				$(this).click(function(){
					var id = new Date().getTime();
					showDialog($(this), id);
					$("#" + id + " .projectutils_modify_dialog_cancel").click(function() {
						$("#"+id).remove();
						return false;
					});
					return false;
				});
			});
			
			//构造html
			
			//加入body并定位显示
			function showDialog(aObj, id) {

				var html = "";
				html += "<div id='" + id + "' class='projectutils_modify_dialog'>";
				html += "<div>" + title + "</div>";
				html += "<form method='post' action='" + aObj.attr("href") + "'>";
				for (var i = 0; i < option.length; i++) {
					var obj = option[i];
					
					html += "	 <label for='" + obj.name + "'>" + obj.label + "</label>";
					
					var initValue = '';
					if (obj.value != undefined) {
						initValue = obj.value;
					}
					
					if (obj.type == 'text') {
						html += "    <input name='" + obj.name + "' type='text' value='" + initValue + "'></input>";
					} else if (obj.type == 'textarea') {
						html += "    <textarea id='" + obj.name + "' name='" + obj.name + "'>" + initValue + "</textarea>";
					}
				}
				html += "	 <button class='projectutils_modify_dialog_submit' type='submit'>确定</button>";
				html += "	 <button class='projectutils_modify_dialog_cancel'>取消</button>";
				html += "</form>";
				html += "</div>";
				
				$('body').append(html);
				var position = aObj.offset();
				if ($(window).width()-$("#" + id).width()-10 < position.left) {
					if ($(window).height() - $("#" + id).height()-10 + $(document).scrollTop() - 50 < position.top) {
						position_move_rbTolt($("#" + id), aObj);
					} else {
						position_move_rtTolb($("#" + id), aObj);
					}
				} else if($(window).height() - $("#" + id).height()-10 < position.top) {
					position_move_lbTolt($("#" + id), aObj);
				}
				else {
					position_move_ltTolb($("#" + id), aObj);
				}

				$("#" + id).show();
			}
		}
		
		/**
		 * 单项修改操作
		 * 通过链接的方式提交数据并修改成功，返回referer到修改页面
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 * 3、options：参数（生成修改项）
		 */
		$.fn.modify = function(title, option) {
			
			$(this).each(function() {
				
				$(this).click(function(){
					var id = new Date().getTime();
					showDialog($(this), id);
					$("#" + id + " .projectutils_modify_dialog_cancel").click(function() {
						$("#"+id).remove();
						return false;
					});
					return false;
				});
			});
			
			//构造html
			
			//加入body并定位显示
			function showDialog(aObj, id) {

				var html = "";
				html += "<div id='" + id + "' class='projectutils_modify_dialog'>";
				html += "<form method='post' action='" + aObj.attr("href") + "'>";
				html += "	 <label for='addSize'>" + title + "</label>";
				
				html += "    <input name='" + option.name + "' type='text'></input>";
				
				html += "	 <button class='projectutils_modify_dialog_submit' type='submit'>确定</button>";
				html += "	 <button class='projectutils_modify_dialog_cancel'>取消</button>";
				html += "</form>";
				html += "</div>";
				
				$('body').append(html);
				position_move_ltTolb($("#" + id), aObj);
				$("#" + id).show();
			}
		}
		
		/**
		 * 单项修改操作
		 * 通过链接的方式提交数据并修改成功，返回referer到修改页面
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 * 3、options：参数（生成修改项）
		 */
		$.fn.modifyBySearch = function(title, option) {
			
		}
		
		/**
		 * 文本初始提示
		 * 给text(input=text)初始化上一段提示,点击后消失，如果已经存在其他值，也不显示
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 */
		$.fn.textInitTip_guoqi = function(tip) {
			
			var srcText = $(this).val();
			if (srcText == '') {
				$(this).addClass('colorGray');
				$(this).val(tip);
			}
			
			$(this).focus(function(){
				if ($(this).val() == tip) {
					$(this).removeClass('colorGray');
					$(this).val('');
				}

			});
			
			$(this).blur(function(){
				if ($(this).val() == '') {
					$(this).addClass('colorGray');
					$(this).val(tip);
				}

			});

		};
		
		/**
		 * 文本初始提示改进版，浮动显示，这样就不会出现无法提示的情况
		 * 给text(input=text)初始化上一段提示,点击后消失，如果已经存在其他值，也不显示
		 * 
		 * 1、href：要提交修改的链接地址
		 * 2、标题
		 */
		$.fn.textInitTip = function(tip) {
			var textVar = $(this);
			var id = new Date().getTime();
			var textP = "<div class='textInit colorGray' style='position:absolute;display:none;z-index:10;' id='" + id + "'>" + tip + "</div>";
			$("body").append(textP);
			position_move_lmTolm($("#"+id),$(this));
			
			if ($(this).val() == '') {
				$("#"+id).css("display", "block");
			}
			
			//文本框获得焦点
			$(this).focus(function(){
				$("#"+id).css("display", "none");
			});
			
			//提示文本点击
			$("#"+id).click(function(){
				//$("#detailaddress").focus();
				textVar.focus();
			});
			
			//文本框失去焦点
			$(this).blur(function(){
				if ($(this).val() == '') {
					$("#"+id).css("display", "block");
				}
			});

		};
		
		/**
		  * 淡出提示
		  *	
		 */
		$.fn.centerTip = function(tip, fontSize) {
			var textVar = $(this);
			var id = new Date().getTime();
			var textP = "<div style='position:absolute;z-index:100;display:none;font-weight:bolder;color:green;font-size:" + fontSize + "px;' id='" + id + "'>" + tip + "</div>";
			$("body").append(textP);
			
			centerBrowser($("#"+id));
			$("#"+id).css("display", "block");
			$("#"+id).fadeOut(3000, function(){$("#"+id).remove()});
		};
		
	})(jQuery);
