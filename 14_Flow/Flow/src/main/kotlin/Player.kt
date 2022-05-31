class Player {
    private var cardList = mutableListOf<GameCard>()
    suspend fun createCardForPlayer(numberCard: Int) {
        for (i in 0 until numberCard) cardList.add(GameCard())
        cardList.forEach { it.createCard() }
    }

    fun cardList() = cardList

    fun cardIsEmpty(): Boolean {
        val listEmptyCard = mutableListOf<Boolean>()
        cardList.forEach { card ->
            card.takeCard().forEach {
                listEmptyCard.add(it.none { i -> i !== null })
            }
        }
        return listEmptyCard.all { it }
    }
}