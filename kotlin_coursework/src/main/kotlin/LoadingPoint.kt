sealed class LoadingPoint : Loading {
    object First : LoadingPoint() {
        override fun toString(): String {
            return "Loading point #1"
        }
    }

    object Second : LoadingPoint() {
        override fun toString(): String {
            return "Loading point #2"
        }
    }
}
