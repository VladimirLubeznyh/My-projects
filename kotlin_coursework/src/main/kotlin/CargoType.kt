sealed class CargoType(val weight: Int, val loadingTime: Long) {
    sealed class Oversize(weight: Int, loadingTime: Long) : CargoType(weight, loadingTime) {
        object Fridge : Oversize(150, 150) {
            override fun toString() = "Fridge"
        }

        object Motorbike : Oversize(250, 250) {
            override fun toString() = "Motorbike"
        }

        object ConstructionMaterials : Oversize(200, 200) {
            override fun toString() = "Construction Materials"
        }
    }

    sealed class MediumSized(weight: Int, loadingTime: Long) : CargoType(weight, loadingTime) {
        object Chairs : MediumSized(50, 50) {
            override fun toString() = "Chairs"
        }

        object Clothing : MediumSized(30, 30) {
            override fun toString() = "Clothing"
        }

        object CarParts : MediumSized(70, 70) {
            override fun toString() = "CarParts"
        }
    }

    sealed class SmallSized(weight: Int, loadingTime: Long) : CargoType(weight, loadingTime) {
        object Shoes : SmallSized(5, 5) {
            override fun toString() = "Shoes"
        }

        object Toys : SmallSized(10, 10) {
            override fun toString() = "Toys"
        }

        object Laptops : SmallSized(3, 3) {
            override fun toString() = "Laptops"
        }
    }

    sealed class Food(weight: Int, loadingTime: Long) : CargoType(weight, loadingTime) {
        object Bread : Food(3, 3) {
            override fun toString() = "Bread"
        }

        object Milk : Food(5, 5) {
            override fun toString() = "Milk"
        }

        object Potato : Food(10, 10) {
            override fun toString() = "Potato"
        }
    }
}

