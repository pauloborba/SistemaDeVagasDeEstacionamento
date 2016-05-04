import static cucumber.api.groovy.EN.*

// Cenario 1	
Given(~'user "([^"]*)" parked on spot "([^"]*)" using the system$'){ String user, int numSpot ->
	UserTestAndDataOperators.createUser(user)
	VagaTestAndDataOperators.reserveSpot(user, numSpot)
	assert VagaTestAndDataOperators.spotOfUser(user) == numSpot
}

When(~'user "([^"]*)" asks a reminder where he parked his car'){ String user ->
	vaga = VagaTestAndDataOperators.spotOfUser(user)
}

Then(~'Then the system informs that he parked on spot "([^"]*)"'){ String numSpot ->
	assert numSpot == vaga
}

// Cenario 2
Given(~'the system doesnt have the information where user "([^"]*)" parked$'){ String user ->
	vaga = UserTestAndDataOperators.spotOfUser(user)
	assert vaga == null
}

When(~'user "([^"]*)" asks a reminder where he parked his car'){ String user ->
	page.askForReminder(user)
	vaga = VagaTestAndDataOperators.spotOfUser()
}

Then(~'there is no changes in the reservations of the parking spot'){ ->
	assert VagaTestAndDataOperators.historyOfReservations == 0
}