import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlin.random.Random

object GeneratorCargo {
    fun flowCargo(): Flow<CargoType> {
        val randomTypeCargo: Int = Random.nextInt(1, 3)
        return flow {
            when (randomTypeCargo) {
                1 -> {
                    while (true) {
                        emit(randomCargo())
                    }
                }
                else -> while (true) {
                    emit(randomCargoFood())
                }
            }
        }
    }

    private fun randomCargoFood(): CargoType.Food = when (Random.nextInt(1, 4)) {
        1 -> CargoType.Food.Bread
        2 -> CargoType.Food.Milk
        else -> CargoType.Food.Potato
    }

    private fun randomCargo(): CargoType {
        val randomToCargoType = Random.nextInt(1, 4)
        val randomToCargo = Random.nextInt(1, 4)
        return when (randomToCargoType) {
            1 -> when (randomToCargo) {
                1 -> CargoType.Oversize.Fridge
                2 -> CargoType.Oversize.ConstructionMaterials
                else -> CargoType.Oversize.Motorbike
            }
            2 -> when (randomToCargo) {
                1 -> CargoType.MediumSized.CarParts
                2 -> CargoType.MediumSized.Chairs
                else -> CargoType.MediumSized.Clothing
            }
            else -> when (randomToCargo) {
                1 -> CargoType.SmallSized.Laptops
                2 -> CargoType.SmallSized.Shoes
                else -> CargoType.SmallSized.Toys
            }
        }
    }
}