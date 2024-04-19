function submitReservation() {
    // Obtenez les données du formulaire
    const formData = {
        nom: document.getElementById('name').value,
        email: document.getElementById('email').value,
        date: document.getElementById('reservationDate').value,
        time: document.getElementById('reservationTime').value,
        nombre_person: document.getElementById('reservationPersons').value,
    };

    // Effectuez une requête POST vers le backend avec les données du formulaire
    axios.post('http://localhost:8080/api/reservations', formData)
        .then(response => {
            console.log('Réservation réussie:', response.data);
            window.alert('Votre réservation a été enregistrée avec succès. Merci!');

            // Ajoutez ici tout code que vous souhaitez exécuter après une réservation réussie
        })
        .catch(error => {
            console.error('Erreur lors de la réservation:', error);
            window.alert('Erreur lors de la réservation . Merci!');

        });
}