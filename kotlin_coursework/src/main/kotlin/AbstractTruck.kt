abstract class AbstractTruck {
    abstract val maxWeight: Int
    val truckBody = TruckBody()

    fun loading(cargo: CargoType) {
        if (cargo.weight + truckBody.weightCargo() < maxWeight) truckBody.pushCargo(cargo)
    }

    fun unloading() {
        if (!truckBody.isEmpty()) truckBody.popCargo()
    }

    fun getTruckBody() = truckBody.getBody()
}