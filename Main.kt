import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.channels.Channel
import sun.rmi.server.Dispatcher
import java.io.File
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis




enum class Choice{ SEARCH ,EDIT , DELETE , EXIT }

@OptIn(DelicateCoroutinesApi::class, ExperimentalStdlibApi::class)

suspend fun main(arg: Array<String>) = coroutineScope{
    lateinit var job :Job
    while (true) {
            println(
                """
            -> press 1 to search
            -> press 2 to edit
            -> press 3 to delete
            -> press 4 to exit
        """.trimIndent() )

            val choice = readLine()!!.toInt()

            when (Choice.values()[choice - 1]) {
                Choice.SEARCH  -> {
                                    job = launch(Dispatchers.IO){ search() }
                                    println("press -1 to Exit")
                                    println("Loading......")
                                    if (readLine()!!.toInt() == -1) println(1)
                }
                Choice.EDIT    -> "edit"
                Choice.DELETE  -> "delete"
                Choice.EXIT    -> println("Thank You !").also {
                    if(!job.isCompleted) {
                        println("job is cancelled")
                        job.cancel()
                    }
                    return@coroutineScope }
            }
    }
}

@OptIn(DelicateCoroutinesApi::class)
suspend fun search(){
    println("entered")
    println(Thread.currentThread().name)
    delay(10000)
    println("result came")
}




