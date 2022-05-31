import kotlinx.coroutines.flow.take

open class GameCard {
    private val row = 3
    private val column = 9
    private var card = List(row) { MutableList<Int?>(column) { null } }
    private val generatorCard = Generator()

    fun takeCard() = card

    suspend fun createCard(): List<MutableList<Int?>> {
        var rowCounter = 0
        var columnCounter = 0
        generatorCard.flow.take(15).collect { int ->
            card[rowCounter][columnCounter] = int
            columnCounter++
            if (columnCounter == 5) {
                columnCounter = 0
                rowCounter++
            }
        }
        card.forEach {
            it.shuffle()
        }
        return card
    }

    fun printCard(player: Int, int: Int) {
        println("Player-$player, Number - $int ")
        card.forEach {
            println("_________________________________________")
            it.forEach { n ->
                if (n == null) print("|   ")
                else print("| $n ")
            }
            println()
        }
        println("_________________________________________")
        println()
    }
}
