var LOGIN = (function (window){

	 'use strict';
	 		            
	function init(){
		
		$(".login_btn").on("click", login);
	}
	
	function login(){
		
		var email = $("#email").val();
		var password = $("#password").val();
		
		var data = {};
		data.email = email;
		data.password = password;
		
		$.ajax({
			"url" : "/users/login",
			"type" : "POST",
			"data" : data
		}).done(function(data) {
			console.log("login success")
			console.log(data);
			window.location.href = (baseURL+data);

		}).fail(function(status) {
			console.log("newDeck fail " + status);
		});
	}


	return {
		"init" : init
	}
	
})(window);

$(function(){
	LOGIN.init();
});