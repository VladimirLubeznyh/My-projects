import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

interface Loading {
    suspend fun loading(truck: TypeTruck, isFood: Boolean, scope: CoroutineScope) {
        val filterTruck = filterTruck(truck)
        printStartLoading(isFood, filterTruck)
        while (scope.isActive) {
            val getCargoCatch = runCatching {
                if (!isFood) Storage.getCargo()
                else Storage.getCargoFood()
            }
            if (getCargoCatch.isSuccess) {
                val cargoOrNull = getCargoCatch.getOrNull()
                if (cargoOrNull !== null) {
                    delay(cargoOrNull.loadingTime)
                    filterTruck.loading(cargoOrNull)
                    if (cargoOrNull.weight + filterTruck.getTruckBody().sumOf { it.weight } > filterTruck.maxWeight) {
                        break
                    }
                }
            }
        }
        printEndLoading(isFood, filterTruck)
    }

    fun printStartLoading(isFood: Boolean, truck: TypeTruck) {
        if (isFood) {
            println(
                """
         
            ====> Start ${toString()}:
             Cargo type: Food
             Truck: $truck 
             """.trimIndent()
            )
        } else {
            println(
                """
            
            ====> Start ${toString()}:
             Cargo type: Is not food
             Truck: $truck  
             """.trimIndent()
            )
        }
    }

    fun printEndLoading(isFood: Boolean, truck: TypeTruck) {
        if (isFood) {
            println(
                """
            
            XXXXX End ${toString()}:
             Cargo type: Food
             Truck: $truck  
             Cargo weight: ${truck.getTruckBody().sumOf { it.weight }}
             Cargo amount: ${truck.getTruckBody().size}
             """.trimIndent()
            )
        } else {
            println(
                """
            
            XXXXX End ${toString()}:
             Cargo type: Is not food
             Truck: $truck
             Cargo weight: ${truck.getTruckBody().sumOf { it.weight }}
             Cargo amount: ${truck.getTruckBody().size}
             """.trimIndent()
            )
        }
    }

    fun filterTruck(truck: TypeTruck): TypeTruck {
        return if (truck is TypeTruck.BigTruck) {
            when (Random.nextInt(1, 3)) {
                1 -> TypeTruck.SmallTruck()
                else -> TypeTruck.MediumTruck()
            }
        } else truck
    }
}