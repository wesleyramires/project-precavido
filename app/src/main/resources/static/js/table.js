function deleta(id) {
	$('#modalDelete').modal('show');
	document.querySelector('#btn-delete-title').addEventListener('click',
			function() {
				$('#modalDelete').modal('hide');

				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 204) {
						window.location.reload();
					}
				};

				xhttp.open("DELETE", `/dashboard/payments/${id}`, true);
				xhttp.send();
			});
}
