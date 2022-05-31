sealed class UnloadingPoint : Unloading {
    object First : UnloadingPoint() {
        override fun toString(): String {
            return "Unloading point #1"
        }
    }

    object Second : UnloadingPoint() {
        override fun toString(): String {
            return "Unloading point #2"
        }
    }

    object Third : UnloadingPoint() {
        override fun toString(): String {
            return "Unloading point #3"
        }
    }

    object Fourth : UnloadingPoint() {
        override fun toString(): String {
            return "Unloading point #4"
        }
    }

    object Fifth : UnloadingPoint() {
        override fun toString(): String {
            return "Unloading point #5"
        }
    }
}