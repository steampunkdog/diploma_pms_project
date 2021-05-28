//$('input[name="name_of_your_radiobutton"]:checked').val();

var dataTypes = [
		{ name: "Room", funcName: "buildPageForRoom" },
		{ name: "RoomClass", funcName: "buildPageForRoomClass" },
		{ name: "RoomTag", funcName: "buildPageForRoomTag" },
		{ name: "User", funcName: "buildPageForUser" },
		{ name: "Booking", funcName: "buildPageForBooking" },
		{ name: "Task", funcName: "buildPageForTask" }
];

$(document).ready(function(){
	dataTypes.forEach((dataType) => $(".data-types-list").append('<div class="data-type" onclick="' + dataType.funcName + '()"><p>' + dataType.name + '</p></div>'));
	
});