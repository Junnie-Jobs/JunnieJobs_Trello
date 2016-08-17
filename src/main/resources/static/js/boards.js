var BOARDS = (function (window){
	
	 'use strict';

	function init(){
		
		$("#create_board").on("click", create_board);
		$(".add_project_btn").on("click", create_new_project);
		$(".save").on("click", add_project);
		$(".add_project a.cancel").on("click", cancel);
		$(".setting_btn").on("click", update);
		$("#boards_list").on("click", ".update_btn", updateBoard);
		$("#boards_list").on("click", ".delete_btn", deleteBoard);
	}
	
	function updateBoard(e){
		
		var board = $(e.target).closest(".board_ul").find(".board");
		var boardId = $(e.target).closest(".board_ul").find(".board").data().boardid;
		var boardName = $(".modify_board_name").val();
		
		if($(board).hasClass("modify")){
			
			var data = {};
			data.boardId = $(e.target).closest(".board_ul").find(".board").data().boardid;
			data.boardName = $(".modify_board_name").val();
			console.log(data);
			
			$.ajax({
				"url" :  "/api/board/update",
				"type" : "POST",
				"data" : data
			}).done(function(){
				$(".modify_board_name").remove();
				$(board).find("a").removeClass("none").text(boardName);				
			}).fail(function(){
				console.log("modfiy event fail");
			})
			
			$(board).removeClass("modify");
			return;
			
		}
		
		var val = $(board).find("a").text();
		console.log(val);
		var modify = "<input class='modify_board_name' type='text' placeholder="+val+
			"></input>";
		
		$(board).find("a").addClass("none");
		$(board).append(modify).addClass("modify");
//		
	}
	
	function deleteBoard(e){
		
		var boardId = $(e.target).closest(".board_ul").find(".board").data();
		var board = $(e.target).closest(".board_ul").find(".board");
		console.log(boardId);
		var data = {};
		data.id = boardId.boardid;
		console.log(data);
		
		$.ajax({
			"url" :  "/api/board/delete",
			"type" : "POST",
			"data" : data
		}).done(function(){
			console.log("board delete succeess");
			$(board).remove();
			$(e.target).closest(".update_box").remove();
			
		}).fail(function(){
			console.log("deletion event fail");
		})
	}
	
	function update(){
		
		$("#my_board").toggleClass("update_view");
		if($(".update_text").hasClass("none")){
			$(".update_text").css('display','block').removeClass("none");
			
		var li = "<div class='update_box z-depth-1'>" +
						"<div class='update_btn'>수정</div>" +
						"<div class='delete_btn'>삭제</div>" +
					"</div>"
		$(".board_ul").append(li);
		$(".board").css('width', '245px');
		$(".add_project_btn").addClass("none");	
		   return;
		   
		}else{
			$(".update_text").css('display','none').addClass("none");
			$(".update_box").addClass("none");
			$(".add_project_btn").removeClass("none");	
			return;
		}
		
		
			
		
	}

	function cancel(){

		$(".btn-floating").css('display','block');
		$(".add_project_form").css('display','none');

	}

	function add_project(){

		var project_name = $("#add_project").val();
		var userId = $(".userId").val();
		var data = {};
		data.boardName = project_name;
		data.userId = userId;

		if (project_name === "") {
			alert("제목을 입력해주세요");
			return;
		}

		if (project_name !== null) {

			$.ajax({
				"url" : "/api/board/new",
				"type" : "POST",
				"data" : data
			}).done(function(data) {
				console.log("newBoard success")
				
				var board_btn = 
					
					"<li class='board board_btn waves-effect waves-light btn'>" +
				"<a href='/boards/board/"+data.boardId+"/"+userId+"'"+">" +
	 						"{{input-value}}</a>" +
	 					"</li>";
				
				var str = board_btn.replace(/\{\{input-value\}\}/gi,project_name);
				$("#boards_list").prepend(str);
				console.log(str);
				$("#add_project").val("");
				$(".add_project_form").css('display','none');
				$(".btn-floating").css('display','block');

				
			}).fail(function(status) {
				console.log("newBoard fail");
			});

		}
	}

	function create_new_project(){

		$(".add_project_btn").css('display','none');
		$(".add_project_form").css('display','block');
	}

	function create_board(){
		$("#boards_list").prepend(board_btn);
	}
	

	return {
		"init" : init
	}
})(window);

$(function(){
	BOARDS.init();
});