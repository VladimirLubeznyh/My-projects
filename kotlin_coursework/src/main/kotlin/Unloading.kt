import kotlinx.coroutines.delay

interface Unloading {
    suspend fun unloading(truck: TypeTruck) {
        println(
            """
                
                ===> Start ${toString()}:
                 Truck: $truck 
                 Cargo weight: ${truck.getTruckBody().sumOf { it.weight }}
                 Time unloading: ${truck.getTruckBody().sumOf { it.loadingTime }}
                """.trimIndent()
        )
        for (i in 1..truck.getTruckBody().size) {
            val item = truck.truckBody.popCargo()
            Storage.addCargo(item)
            delay(item.loadingTime)
        }
        println(
            """
            
            XXXX End ${toString()}:
             Truck: $truck 
            """.trimIndent()
        )
    }
}