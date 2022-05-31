import kotlinx.coroutines.*


object Terminal {
    val parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Default)
    suspend fun startWorking(timeWorkingInMinutes: Long) {
        GeneratorTruck.startGeneratorTruck(scope)
        unloading()
        loading()
        printStorage()
        scope.launch {
            delay(timeWorkingInMinutes * 60000)
            scope.cancel()
        }
    }

    private suspend fun unloading() {
        scope.launch {
            while (isActive) {
                UnloadingPoint.First.unloading(GeneratorTruck.getTruckQueue())
            }

        }
        scope.launch {
            while (isActive) {
                UnloadingPoint.Second.unloading(GeneratorTruck.getTruckQueue())
            }
        }
        scope.launch {
            while (isActive) {
                UnloadingPoint.Third.unloading(GeneratorTruck.getTruckQueue())
            }
        }
        scope.launch {
            while (isActive) {
                UnloadingPoint.Fourth.unloading(GeneratorTruck.getTruckQueue())
            }
        }
        scope.launch {
            while (isActive) {
                UnloadingPoint.Fifth.unloading(GeneratorTruck.getTruckQueue())
            }
        }

    }

    private suspend fun loading() {
        scope.launch {
            while (isActive) {
                val truck = GeneratorTruck.randomEmptyTruck()
                if (Storage.storageCargo.isNotEmpty()) LoadingPoint.First.loading(truck, false, scope)
                if (Storage.storageCargoFood.isNotEmpty()) LoadingPoint.First.loading(truck, true, scope)
            }
        }
        scope.launch {
            while (isActive) {
                val truck = GeneratorTruck.randomEmptyTruck()
                if (Storage.storageCargo.isNotEmpty()) LoadingPoint.Second.loading(truck, false, scope)
                if (Storage.storageCargoFood.isNotEmpty()) LoadingPoint.Second.loading(truck, true, scope)
            }
        }
    }

    private suspend fun printStorage() {
        scope.launch {
            while (isActive) {
                delay(5000)
                println(
                    """
                    ___________________________
                    Storage:
                    Cargo amount(is not food) - ${Storage.storageCargo.size}
                    Cargo amount (food) - ${Storage.storageCargoFood.size}
                    ____________________________
                    
                """.trimIndent()
                )
            }
        }
    }
}