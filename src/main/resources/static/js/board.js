var TODO = (function (window){

	 'use strict';

	 	var baseURL = "http://localhost:9090";
//	 	var baseURL = "http://localhost:8989";
//		var baseURL = "http://junniejobs.xyz";


    var comment_html =   "<div class='comment' data-id='{{dataId}}'>" +
			                    "<div class='commenter'>{{writer_name}}</div>" +
			                    "<div class='comment_contents z-depth-1'>{{comment_contents}}</div>" +
			                    "<div class='comment_date'>{{current_time}} - </div>" +
			                    "<div class='comment_reply'> Reply</div>" +
              			  "</div>";

    var comment_template = Handlebars.compile(comment_html);

		var card_html =
					"<div class='list_card' data-id='{{data}}'>" +
						 "<div class='list_card_detail'>" +
									"<a class='list_card_title modal-trigger modalLink' dir='auto' href='#modalLayer' >{{value}}</a>" +
						 "</div>" +
					"</div>";

		var deck_html = "<div class='list_wrapper' data-id='{{data}}'>" +
					"<div class='list_content z-depth-1'>" +
							"<div class='list_header'>"+
							 "<textarea class='list_header_name'>{{value}}</textarea>"+
						 "</div>" +
							"<div class='list_cards'></div>" +
			"<div class='card_composer'>" +
									"<div class='show_add_card_form_form'>" +
										"<textarea class='list_card_composer_textarea'></textarea>" +
										 "<a class='waves-effect  waves-light btn card_save blue-grey lighten-5'>save</a>" +
										 "<a class='waves-effect waves-light btn card_cancel blue-grey lighten-5'>cancel</a>" +
									"</div>" +
									"<a class='show_add_card_form' href='#''>Add a Card...</a>" +
							"</div>" +
					"</div>" +
				"</div>";

	function init(){



  	$("#board_canvas").on("click", ".modalLink", show_modal);
		$(".btn-floating").on("click", show_create_deck_form);
		$(".save").on("click", add_deck);
		$("#board_canvas").on("click",".show_add_card_form", show_add_card_form);
		$("#board_canvas").on("click",".card_save", card_save);
		$("#board_canvas").on("click",".card_cancel", card_cancel);
   	$( "#sortable" ).disableSelection();
		$(".add_deck a.cancel").on("click", cancel);
		$(".add_deck").removeClass("ui-sortable-handle");
 		$(".attach_from_computer").on("click", file_upload);
 		$(".comment_send").on("click", add_comment);
 		$( "#sortable" ).sortable({
  		  placeholder: "ui-state-highlight",
  		  cancel: ".add_deck"
 		});
 		$( "#board_canvas" ).sortable();
		$( "#board_canvas" ).disableSelection();
 		$(".members_btn").on("click", search_member);
 		$(".due_date_btn").on("click", setting_date);
 		$(".file_attachment").on("click", setting_attachment);
    $(".datepicker").pickadate({
		    selectMonths: true,
		    selectYears: 15
  	});
  	$(".close_button").on("click", close_modal);
  	$(".shadow_body").on("click", close_modal);
  	$('.modal-trigger').leanModal();
	}

	function close_modal(){

		$("#modalLayer").fadeOut("slow");
		$(".shadow_body").fadeOut("slow");
	}

	function setting_attachment(){

		if($(".modal_for_attachment").hasClass("clicked")){
			$(".modal_for_attachment").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_attachment").addClass("clicked").slideDown();
	}

	function setting_date(){

		if($(".modal_for_due_date").hasClass("clicked")){
			$(".modal_for_due_date").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_due_date").addClass("clicked").slideDown();

	}

	function search_member(){

		console.log("asd");
		if($(".modal_for_members").hasClass("clicked")){
			$(".modal_for_members").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_members").addClass("clicked").slideDown();
	}

	function add_comment(e){

		var cardId = $(e.target).closest(".list_card").data("id");
		console.log(cardId);

		var comment_contents = $(".comment_contents").val();
		var writer_name = $(".username").val();
		var now = new Date();
		var currentTime = now.getDate() + " " +
					  month_written_english(now.getMonth()+1) + " " +
					  now.getFullYear() + " at " +
					  now.getHours() + ":" +
					  now.getMinutes();

		var cardId = $(".hiddenCardId").val();

		var data = {};
		data.cardId = cardId;
		data.username = writer_name;
		data.contents = comment_contents;
		data.timeStamp = currentTime;
		console.log(data);

		if (comment_contents === "") {
			alert("내용을 입력해주세요");
			return;
		}

		if (comment_contents !== null) {

			$.ajax({
				"url" : baseURL + "/api/comment/new",
				"type" : "POST",
				"data" : data
			}).done(function(data) {
				console.log("newComment success")
				console.log(data);
				$(comment_template({"dataId":data.commentId,"comment_contents":comment_contents, "current_time":currentTime, "writer_name":writer_name})).appendTo(".comments");
				$(".comment_contents").val("");
			}).fail(function(status) {
				console.log("newComment fail " + status);
			});
		}





	}

	function month_written_english(month){

		if(month === 1){
			return "Jan";
		}else if(month === 2){
			return "Feb";
		}else if(month === 3){
			return "Mar";
		}else if(month === 4){
			return "Apr";
		}else if(month === 5){
			return "May";
		}else if(month === 6){
			return "Jun";
		}else if(month === 7){
			return "July";
		}else if(month === 8){
			return "Aug";
		}else if(month === 9){
			return "Sep";
		}else if(month === 10){
			return "Oct";
		}else if(month === 11){
			return "Nov";
		}else if(month === 12){
			return "Dec";
		}
	}

	function file_upload(){
		$("#fileUpload").trigger("click");
	}

	function show_modal(e){
		$(".shadow_body").fadeIn("slow");
		$("#modalLayer").fadeIn("slow");
		var title = $(e.target).text();
		$(".card_title_in_modal").text(title);
		var list_name = $(e.target).closest(".list_content").find(".list_header_name").val();
		$(".list_name").text(list_name);
		$(".hiddenCardId").val($(e.target).closest(".list_card").data("id"));

	}

	function card_cancel(e){

		$(e.target).closest(".card_composer .show_add_card_form_form").css('display', 'none');
		$(e.target).closest(".card_composer").find("a.show_add_card_form").css('display', 'block');
	}

	function cancel(){

		$(".btn-floating").css('display','block');
		$(".add_deck_form").css('display','none');
	}

	function modal(){
		$('.modal-trigger').leanModal();
	}

	function show_create_deck_form(){

		$(".btn-floating").css('display','none');
		$(".add_deck_form").css('display','block');
	}

	function show_add_card_form(e){
		// $(this).closest(".card_composer").find()
		$(e.target).parent().find(".show_add_card_form_form").css('display', 'block');
		$(e.target).parent().find("a.show_add_card_form").css('display', 'none');
	}

	function card_save(e){

		$(".show_add_card_form_form").css('display', 'none');
		var card_Name = $(e.target).parent(".show_add_card_form_form").find(".list_card_composer_textarea").val();
		var $list_wrapper = $(e.target).closest(".list_wrapper");
		console.log("리스트 래퍼를 찾겠어 ");
		console.log($list_wrapper);


		var boardId = $(".boardId").val();
		var deckId = $(e.target).closest(".list_wrapper").data("id");
		console.log(deckId);

		var data = {};
		data.cardName = card_Name;
		data.deckId = deckId;

		if (card_Name === "") {
			alert("제목을 입력해주세요");
			return;
		}

		if (card_Name !== null) {

			$.ajax({
				"url" : baseURL + "/api/card/new",
				"type" : "POST",
				"data" : data
			}).done(function(data) {
				console.log("newCard success")
				console.log(data);

				var card_template = Handlebars.compile(card_html);
				var str = card_template({"value":card_Name, "data":data.cardId});

				$list_wrapper.find(".list_cards").last().append(str);
				$(e.target).parent(".show_add_card_form_form").find(".list_card_composer_textarea").val("");
				$(e.target).parents(".card_composer").find("a.show_add_card_form").css('display', 'block');

			}).fail(function(status) {
				console.log("newDeck fail " + status);

			});
		}
	}

	function add_deck(){

		var deck_name = $("#add_deck").val();
		var boardId = $(".boardId").val();
		var data = {};
		data.deckName = deck_name;
		data.boardId = boardId;

		if (deck_name === "") {
			alert("제목을 입력해주세요");
			return;
		}

		if (deck_name !== null) {

			$.ajax({
				"url" : baseURL + "/api/deck/new",
				"type" : "POST",
				"data" : data
			}).done(function(data) {
				console.log("newDeck success")
				console.log(data);

				var deck_template = Handlebars.compile(deck_html);
				var str = deck_template({"value":deck_name, "data":data.deckId});

				$(".add_deck").before(str);
				$("#add_deck").val("");
				$(".add_deck_form").css('display','none');
				$(".btn-floating").css('display','block');

			}).fail(function(status) {
				console.log("newDeck fail " + status);
				console.log(status);
			});
		}


	}

	return {
		"init" : init
	}

})(window);

$(function(){
    TODO.init();
});
