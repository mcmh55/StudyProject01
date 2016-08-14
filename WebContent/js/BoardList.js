$(function(){
	
	var searchType = $("#search_type");
	
	$("#search_type_list").change(function(){
		searchType.attr("value", $('#search_type_list option:selected').val());
	});
});

function checkEmpty() {
	
	var searchType = $("#search_type");
	var searchText = $("#search_text");
	var inputText = $("#input_search_text").val();
	
	if ( searchText == "" ) {
		
		alert("검색어를 입력해주세요.");
		
		return false;
	} else {
		
		searchType.attr("value", "1");
		searchText.attr("value", inputText);
		
		return true;
	}
}