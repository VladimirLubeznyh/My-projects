sealed class TypeTruck : AbstractTruck() {
    class BigTruck : TypeTruck() {
        override val maxWeight: Int = 20000
        override fun toString(): String {
            return "Big truck"
        }
    }

    class MediumTruck : TypeTruck() {
        override val maxWeight: Int = 10000
        override fun toString(): String {
            return "Medium truck"
        }
    }

    class SmallTruck : TypeTruck() {
        override val maxWeight: Int = 5000
        override fun toString(): String {
            return "Small truck"
        }
    }
}
