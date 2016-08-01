var TODO = (function (window){
	
	var baseURL = "http://localhost:9090";
//	var baseURL = "https://junniejobs.xyz";
	 'use strict';

	 var board_btn = " <li class='board waves-effect waves-light btn'>" +		
	 						"<a href='index.html' th:href='@{/boards/board/{boardId}(boardId=${board.boardId})}'>" +
	 						"{{input-value}}</a>" +
	 					"</li>";
	 						
	function init(){

//		$("#boards_list").on("click", ".board", page_nav);
		moveToBoards();
		$("#create_board").on("click", create_board);
		$(".add_project_btn").on("click", create_new_project);
		$(".save").on("click", add_project);
		$(".add_project a.cancel").on("click", cancel);
	}
	
	function moveToBoards(){
		
		$.ajax({
			"url" : baseURL+"/user",
			"type" : "GET"
		}).then(function(data) {
			console.log(data.userAuthentication.details.name);
			var facebookId = data.userAuthentication.details.id;	
			var user_profile_imgUrl = "http://graph.facebook.com/" + facebookId + "/picture?type=square";
			var facebookUser = {
					 fbId : data.userAuthentication.details.id,
					 username : data.userAuthentication.details.name
			}
			return $.ajax({
				"url" : baseURL+"/fbUserLogin",
				"type" : "POST",
				"data" : facebookUser					
			});	
		}).then(function(data){
			console.log("facebook Login succeed");
			window.location.href = ("/boards/"+data);
		})
	}

	function cancel(){

		$(".btn-floating").css('display','block');
		$(".add_project_form").css('display','none');

	}

	function add_project(){

		var project_name = $("#add_project").val();
		var str = board_btn.replace(/\{\{input-value\}\}/gi,project_name);
		console.log(str);
		$("#boards_list").prepend(str);
		console.log(str);
		$("#add_project").val("");
		$(".add_project_form").css('display','none');
		$(".btn-floating").css('display','block');
		
		var data = {};
		data.boardName = project_name;

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
			}).fail(function(status) {
				console.log("newBoard fail");
			});

		}
	}

	function create_new_project(){

		$(".add_project_btn").css('display','none');
		$(".add_project_form").css('display','block');
	}

//	function page_nav(){
//		window.location.href = ("board");
//	}

	function create_board(){
		$("#boards_list").prepend(board_btn);
	}
	

	return {
		"init" : init
	}
})(window);

$(function(){
    TODO.init();
});