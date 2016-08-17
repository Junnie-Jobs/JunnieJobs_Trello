var TODO = (function (window){
	
	 'use strict';

	 var board_btn = " <li class='board waves-effect waves-light btn'>" +		
	 						"<a href='index.html' th:href='@{/boards/board/{boardId}(boardId=${board.boardId})}'>" +
	 						"{{input-value}}</a>" +
	 					"</li>";
	 						
	function init(){
		moveToBoards();
	}
	
	function moveToBoards(){
		
		console.log("move!");
		
		$.ajax({
			"url" : "/user",
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
				"url" : "/fbUserLogin",
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

	return {
		"init" : init
	}
})(window);

$(function(){
    TODO.init();
});