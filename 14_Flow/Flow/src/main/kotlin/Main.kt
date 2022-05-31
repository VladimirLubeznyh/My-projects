import kotlinx.coroutines.*
fun main() {
    val generator = Generator()
    val game = GameParty(3, 2)
    runBlocking {
        game.startGame(generator.flow)
    }
}

