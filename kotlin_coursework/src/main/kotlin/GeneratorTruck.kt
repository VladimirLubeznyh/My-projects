import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlin.random.Random

object GeneratorTruck {
    private val truckQueue = Channel<TypeTruck>()

    fun startGeneratorTruck(scope: CoroutineScope) {
        scope.launch {
            while (true) {
                val truck = addTruckInQueue()
                println("Create Truck #${truck}, Cargo weight: ${truck.getTruckBody().sumOf { it.weight }} ")
                delay(5000)
            }
        }
    }

    fun randomEmptyTruck(): TypeTruck =
        when (Random.nextInt(1, 4)) {
            1 -> TypeTruck.BigTruck()
            2 -> TypeTruck.MediumTruck()
            else -> TypeTruck.SmallTruck()
        }

    private suspend fun loadingInTruck(truck: TypeTruck): TypeTruck {
        val randomCargoNumber = Random.nextInt(0, 1000)
        GeneratorCargo.flowCargo()
            .take(randomCargoNumber)
            .takeWhile {
                truck.truckBody.weightCargo() + it.weight < truck.maxWeight
            }
            .collect {
                truck.loading(it)
            }
        return truck
    }

    private suspend fun addTruckInQueue(): TypeTruck {
        val truck = loadingInTruck(randomEmptyTruck())
        truckQueue.trySend(truck).isSuccess
        return truck
    }

    suspend fun getTruckQueue() = truckQueue.receive()
}