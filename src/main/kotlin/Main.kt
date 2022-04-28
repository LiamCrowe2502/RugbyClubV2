import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}
val scanner = Scanner(System.`in`)

fun main(args: Array<String>) {
    runMenu()
}

fun mainMenu() : Int {
    print(""" 
         > ----------------------------------
         > |        Rugby Club APP         |
         > ----------------------------------
         > |   1) Add a player              |
         > |   2) List all players          |
         > |   3) List number of players    |
         > |   4) Search player ID          |
         > |   5) Save player details       |
         > |   6) Load database             |
         > |   7) Update player details     |
         > |   8) Delete a player           |
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">"))
    return scanner.nextInt()
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addPlayer()
            2  -> listPlayers()
            3  -> numOfPlayers()
            4  -> searchByID()
            5  -> save()
            6  -> load()
            7  -> updatePlayer()
            8  -> deletePlayer()
            0  -> exit()
            else -> System.out.println("Invalid option entered: ${option}")
        }
    } while (true)
}

fun addPlayer(){
    logger.info { "addPlayer() function invoked" }
}

fun listPlayers() {
    logger.info { "listPlayers() function invoked" }
}

fun numOfPlayers() {
    logger.info { "numberOfPlayers() function invoked" }
}

fun searchByID() {
    logger.info { "searchByID() function invoked" }
}

fun save() {
    logger.info { "save() function invoked" }
}

fun load() {
    logger.info { "load() function invoked" }
}

fun updatePlayer() {
    logger.info { "updatePlayers() function invoked" }
}

fun deletePlayer() {
    logger.info { "deletePlayer() function invoked" }
}

fun exit() {
    logger.info { "exit function invoked" }
    System.exit(0)
}
