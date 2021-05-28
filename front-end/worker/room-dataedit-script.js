function buildPageForRoom() {
	fetchRooms()
		.then((rooms) => {
			$('.data-management-table').empty();
			$('.data-management-table').append(buildRoomsDataTable(rooms));
	})

	$('.entity-editor').empty();
	$('.entity-editor').append(buildRoomFrom())
}


function fetchRooms(){
	return fetch('http://localhost:8082/room')
		.then(response => response.json())
}

function fetchRoomById(id){
	return fetch('http://localhost:8082/room/' + id)
		.then(response => response.json())
}



function buildRoomsDataTable(rooms) {


	var roomHtmlTable = '<tr>' +
							'<th><p>ID</p></th>' +
							'<th><p>Name</p></th>' +
							'<th><p>Description</p></th>' +
							'<th><p>Class</p></th>' +
							'<th><p>Sleeping Area Number</p></th>' +
							'<th><p>Tags</p></th>' +
						'</tr>';

	rooms.forEach((room) => {
		roomHtmlTable += '<tr room_id="'+room.id+'">' +
							'<td><p>'+room.id+'</p></td>' +
							'<td><p>'+room.name+'</p></td>' +
							'<td><p>'+room.description+'</p></td>' +
							'<td><p>ID:'+room.roomClass.id+'<br>Name:' +room.roomClass.name+ '</p></td>' +
							'<td><p>'+room.sleepingAreaNumber+'</p></td>' +
							'<td>';

		if (room.roomTags != null && room.roomTags != undefined) {
			room.roomTags.forEach((tag) => {
				roomHtmlTable += '<p>ID:'+tag.id+'<br>Name:' +tag.name+ '</p>';
			})	
		}
							
		roomHtmlTable += '</td></tr>';
	})

	return roomHtmlTable;
}

function buildRoomFrom() {
	return '<form>' +
				'<label> <p>ID</p>   					<input type="text" name="id">					</label>' +
				'<label> <p>Name</p> 					<input type="text" name="name"> 				</label>' +
				'<label> <p>Description</p> 			<textarea name="description"></textarea> 		</label>' +
				'<label> <p>Class</p> 					<input type="text" name="class"> 				</label>' +
				'<label> <p>Sleeping Area Number</p>    <input type="number" name="sleepingAreaNumber"> </label>' +
				'<label> <p>Tags</p> 					<input type="text" name="roomTags">				</label>' +
				'<label class="submit-butoon-label"> <p>Save</p> <input type="submit" value="Save"> 	</label>' +
			'</form>'
}

