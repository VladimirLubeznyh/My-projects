class TruckBody {
    private val listCargo = mutableListOf<CargoType>()

    fun pushCargo(cargo: CargoType) {
        listCargo.add(cargo)
    }

    fun popCargo():CargoType {
        return  listCargo.removeLast()
    }

    fun isEmpty() = listCargo.isEmpty()

    fun getBody() = listCargo

    fun weightCargo() = listCargo.sumOf { it.weight }
}