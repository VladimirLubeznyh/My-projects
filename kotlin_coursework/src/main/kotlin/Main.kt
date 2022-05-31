import kotlinx.coroutines.*
//Программа имитирует работу логистического терминала
fun main() {
    println("How many minutes should the terminal work?")
    var n = readlnOrNull()?.toLongOrNull() ?: return
    while (n <= 0){
        println("Enter the correct value")
        n = readlnOrNull()?.toLongOrNull() ?: return
    }

    runBlocking {
        Terminal.startWorking(n)
        Terminal.parentJob.complete()
        Terminal.parentJob.join()
    }
}



