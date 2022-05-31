import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

val parentJob = Job()
val scope = CoroutineScope(parentJob + Dispatchers.Default)

class GameParty(private val numberOfPlayer: Int, private val numberOfCard: Int) {

    private val playerList = List(numberOfPlayer) { Player() }

    suspend fun startGame(flow: Flow<Int>) {
        playerList.forEach { it.createCardForPlayer(numberOfCard) }
        scope.launch {
            flow.collect { int ->
                playerList.forEachIndexed { index, player ->
                    scope.launch {
                        player.cardList().forEach { card ->
                            checkNumberInCard(int, card.takeCard(), player, index + 1)
                        }
                    }
                }
                printCardPlayer(int)
                delay(100)
            }
        }
        parentJob.complete()
        parentJob.join()
    }

    private suspend fun checkNumberInCard(
        int: Int,
        matrix: List<MutableList<Int?>>,
        player: Player,
        playerNumber: Int
    ) =
        matrix.forEach { row ->
            for (i in 0 until 9) {
                if (row[i] == int) row[i] = null
                cancelFlow(player, playerNumber)
            }

        }

    private suspend fun cancelFlow(player: Player, playerNumber: Int) {
        if (player.cardIsEmpty()) {
            println("Whine $playerNumber")
            parentJob.cancel()
            parentJob.join()
        }
    }

    private fun printCardPlayer(number: Int) {
        playerList.forEachIndexed() { index, player ->
            player.cardList().forEach {
                it.printCard(index + 1, number)
            }
        }
    }
}

