


function buildRoomDivHtml(room) {
	var roomDivHtml = '<div class="room">' +
							'<h3 class="room-name">' + room.name +'</h3>' +
							'<p class="room-class">' + room.roomClass.name + '</p>';

	roomDivHtml += 			'<div class="room-tags">';
	room.roomTags.forEach((tag) => {
		roomDivHtml += 			'<span>' + tag.name + '</span>';
	});

	roomDivHtml += 			'</div>';

	roomDivHtml += 			'<p class="room-description">' + room.description + '</p>' +
							'<p class="room-price">' + room.price + '$ / per night</p>' +
							'<div class="book-button">Book</div>'
						'</div>'
	return roomDivHtml;
}		

function buildRoomTagCheckbox(tag) {
	return '<label><input type="checkbox" name="tags" value="' + tag.id + '"></input> <span>' + tag.name+ '</span></label>'
}

function buildRoomClassCheckbox(roomClass) {
	return '<label><input type="radio" name="roomClassId" value="' + roomClass.id + '"></input> <span>' + roomClass.name+ '</span></label>'
}


$(document).ready(function(){
	fetch('http://localhost:8082/room')
		.then(response => response.json())
		.then((rooms) => {
			rooms.forEach((room) => {
				$('.booking-page-rooms').append(buildRoomDivHtml(room));
			})
		})
});


$(document).ready(function(){
	fetch('http://localhost:8082/room-tag')
		.then(response => response.json())
		.then((tags) => {
			tags.forEach((tag) => {
				$('.booking-from-tags-wrapper').append(buildRoomTagCheckbox(tag));
			})
		})
});

$(document).ready(function(){
	fetch('http://localhost:8082/room-class')
		.then(response => response.json())
		.then((roomClasses) => {
			roomClasses.forEach((roomClass) => {
				$('.booking-classes-wrapper').append(buildRoomClassCheckbox(roomClass));
			})
		})
});

$(document).ready(function(){
	var form = document.forms.namedItem("booking-form");
	form.addEventListener(
		'submit', 
		function(ev) {
			var fd = new FormData(form);

			var roomSearchRequest = Object.fromEntries(fd);
			roomSearchRequest.tagIds = fd.getAll('tags');
			console.log(roomSearchRequest);


			requestAvailableRooms(roomSearchRequest)
				.then((rooms) => {
					$('.booking-page-rooms').empty()
					rooms.forEach((room) => {
						$('.booking-page-rooms').append(buildRoomDivHtml(room));
					})
				})


			ev.preventDefault();
		}, 
		false
	);
});

function requestAvailableRooms(roomSearchRequest) {
	var uri = 'http://localhost:8084/booking/find-available-rooms?from=' + roomSearchRequest.from + '&to=' + roomSearchRequest.to;
	return fetch(
			uri,
			{
				method: "POST",
				headers: {
			      'Content-Type': 'application/json'
			    },
				body: JSON.stringify(roomSearchRequest)
			}
	).then(resp => resp.json())
	
}