import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive

class Generator {
    private val list = (1..90).toList().shuffled()
    val flow = flow {
        while (currentCoroutineContext().isActive) {
            list.forEach {
                emit(it)
            }
        }
    }.take(90)
}


