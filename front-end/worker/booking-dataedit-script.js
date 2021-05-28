function buildPageForBooking() {
	fetchBookings()
		.then((bookings) => {
			$('.data-management-table').empty();
			$('.data-management-table').append(buildBookingsDataTable(bookings));
	})

	$('.entity-editor').empty();
	$('.entity-editor').append(buildBookingFrom())

	var form = document.forms.namedItem("booking-form");
	form.addEventListener(
		'submit', 
		function(ev) {
			
			var booking = Object.fromEntries(new FormData(form));
			if(booking == "") {
				booking.id = null;
			}
			
			console.log(booking);


			saveBooking(booking)
				.then((response) => {
					if (!response.ok) {
					    alert('Error');
					} else {
						alert('Saved');
					}
				})


			ev.preventDefault();
		}, 
		false
	);
}


function fetchBookings(){
	return fetch('http://localhost:8084/booking')
		.then(response => response.json())
}

function fetchBookingById(id){
	return fetch('http://localhost:8084/booking/' + id)
		.then(response => response.json())
}

function saveBooking(booking){
	return fetch(
		'http://localhost:8084/booking',
			{
				method: "POST",
				headers: {
			      'Content-Type': 'application/json'
			    },
				body: JSON.stringify(booking)
			}
		)
}


function buildBookingsDataTable(bookings) {


	var bookingHtmlTable = '<tr class="row title-row">' +
							'<th>ID</th>' +
							'<th>Start Date</th>' +
							'<th>End Date</th>' +
							'<th>Room</th>' +
							'<th>Status</th>' +
							'<th>User</th>' +
							'<th>Price</th>' +
						'</tr>';

	bookings.forEach((booking) => {
		bookingHtmlTable += '<tr class="row" booking_id="'+booking.id+'">' +
							'<td><p>'+booking.id+'</p></td>' +
							'<td><p>'+booking.startDate+'</p></td>' +
							'<td><p>'+booking.endDate+'</p></td>' +
							'<td><p>'+booking.roomId+'</p></td>' +
							'<td><p>'+booking.status+'</p></td>' +
							'<td><p>'+booking.userId+'</p></td>' +
							'<td><p>'+booking.price+'</p></td>' +
						'</tr>';
	})

	return bookingHtmlTable;
}

function buildBookingFrom() {
	return '<form action="multipart/form-data" class="booking-form" name="booking-form">' +
				'<label> <p>ID</p>   		<input type="text" name="id">				</label>' +
				'<label> <p>Start Date</p> 	<input type="date" name="startDate"> 		</label>' +
				'<label> <p>End Date</p> 	<input type="date" name="endDate"> 	 	 	</label>' +
				'<label> <p>Room</p> 		<input type="text" name="roomId"> 			</label>' +
				'<label> <p>Status</p> 		<input type="text" name="status"> 	 	 	</label>' +
				'<label> <p>User</p>    	<input type="text" name="userId"> 			</label>' +
				'<label> <p>Price</p> 		<input type="number" name="price"> 			</label>' +
				'<label class="submit-butoon-label"> <p>Save</p> <input type="submit" value="Save"> </label>' +
			'</form>'
}



