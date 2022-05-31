import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object Storage {
    var storageCargo = mutableListOf<CargoType>()
    val storageCargoFood = mutableListOf<CargoType.Food>()
    private val mutex = Mutex()
    fun addCargo(cargo: CargoType) {
        if (cargo is CargoType.Food) {
            storageCargoFood.add(cargo)
        } else {
            storageCargo.add(cargo)
        }
    }

    suspend fun getCargo(): CargoType {
        mutex.withLock {
            return storageCargo.removeFirst()
        }
    }

    suspend fun getCargoFood(): CargoType {
        mutex.withLock {
            return storageCargoFood.removeFirst()
        }
    }
}

