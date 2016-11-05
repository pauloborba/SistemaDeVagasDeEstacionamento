package sistemadevagasdeestacionamento


class ReservedParkingSpaceController {

    def index() {
        User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)

        def reservedParkingsSpace = ReservedParkingSpace.findAllById(loggedUser.id)
        [reservedParkingsSpace : reservedParkingsSpace]
    }
}
