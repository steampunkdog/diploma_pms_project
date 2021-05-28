function buildPageForUser() {
	fetchUsers()
		.then((users) => {
			$('.data-management-table').empty();
			$('.data-management-table').append(buildUsersDataTable(users));
	})

	$('.entity-editor').empty();
	$('.entity-editor').append(buildUserFrom())

	var form = document.forms.namedItem("user-form");
	form.addEventListener(
		'submit', 
		function(ev) {

			var user = Object.fromEntries(new FormData(form));
			if(user.id == "") {
				user.id = null;
			}
			
			console.log(user);


			saveUser(user)
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


function fetchUsers(){
	return fetch('http://localhost:8083/user')
		.then(response => response.json())
}

function fetchUserById(id){
	return fetch('http://localhost:8083/user/' + id)
		.then(response => response.json())
}

function saveUser(user){
	return fetch(
		'http://localhost:8083/user/employee',
			{
				method: "POST",
				headers: {
			      'Content-Type': 'application/json'
			    },
				body: JSON.stringify(user)
			}
		)
}


function buildUsersDataTable(users) {


	var userHtmlTable = '<tr>' +
							'<th>ID</th>' +
							'<th>Name</th>' +
							'<th>Lastname</th>' +
							'<th>E-mail</th>' +
							'<th>Role</th>' +
							'<th>Phone Number</th>' +
							'<th>Date Of Birth</th>' +
							'<th>Gender</th>' +
							'<th>Home Country</th>' +
						'</tr>';

	users.forEach((user) => {
		userHtmlTable += '<tr user_id="'+user.id+'">' +
							'<td><p>' + user.id + '</p></td>' +
							'<td><p>' + user.name + '</p></td>' +
							'<td><p>' + user.lastName + '</p></td>' +
							'<td><p>' + user.email + '</p></td>' +
							'<td><p>' + user.role + '</p></td>' +
							'<td><p>' + user.phoneNumber + '</p></td>' +
							'<td><p>' + user.dateOfBirth + '</p></td>' +
							'<td><p>' + user.gender + '</p></td>' +
							'<td><p>' + user.homeCountry + '</p></td>' +
						'</tr>';
	})

	return userHtmlTable;
}

function buildUserFrom() {
	return '<form action="multipart/form-data" class="user-form" name="user-form">' +
				'<label> <p>ID</p>   				<input type="text" name="id">			</label>' +
				'<label> <p>Name</p> 				<input type="text" name="name"> 		</label>' +
				'<label> <p>Lastname</p> 			<input type="text" name="lastName"> 	</label>' +
				'<label> <p>E-mail</p> 				<input type="email" name="email"> 		</label>' +
				'<label> <p>Password</p> 			<input type="password" name="password">	</label>' +
				'<label> <p>Role</p> 				<input type="text" name="role"> 	 	</label>' +
				'<label> <p>Phone Number</p>    	<input type="text" name="phoneNumber"> 	</label>' +
				'<label> <p>Date Of Birth</p> 		<input type="date" name="dateOfBirth"> 	</label>' +
				'<label> <p>Gender</p> 				<input type="text" name="gender"> 		</label>' +
				'<label> <p>Home Country</p> 		<input type="text" name="homeCountry"> 	</label>' +
				'<label class="submit-butoon-label"> <p>Save</p> <input type="submit" value="Save"> </label>' +
			'</form>'
}



