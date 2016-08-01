var BOARDS = (function (window){
	
	var baseURL = "http://localhost:9090";
//	var baseURL = "http://junniejobs.xyz";

	 'use strict';


	 
	function init(){

		$("#create_board").on("click", create_board);
		$(".add_project_btn").on("click", create_new_project);
		$(".save").on("click", add_project);
		$(".add_project a.cancel").on("click", cancel);
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
				"url" : baseURL + "/api/board/new",
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