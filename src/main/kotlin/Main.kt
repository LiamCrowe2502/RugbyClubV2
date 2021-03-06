import controller.API
import model.Player
import mu.KotlinLogging
import persistence.JSONSerializer
import utils.CategoryUtility
import utils.CategoryUtility.categories
import utils.Scanner.getDateInput
import utils.Scanner.readNextInt
import utils.Scanner.readNextLine
import java.io.File
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess

// private val api = API(XMLSerializer(File("players.xml")))
private val api = API(JSONSerializer(File("players.json")))
private val logger = KotlinLogging.logger {}
val scanner = Scanner(System.`in`)

fun main() {
    runMenu()
}

fun mainMenu(): Int {
    print(
        """ 
         > ----------------------------------
         > |        Rugby Club APP          |
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
         > ==>> """.trimMargin(">")
    )
    return scanner.nextInt()
}

fun runMenu() {
    do {
        when (val option: Int = mainMenu()) {
            1 -> addPlayer()
            2 -> listPlayers()
            3 -> numOfPlayers()
            4 -> searchByID()
            5 -> save()
            6 -> load()
            7 -> updatePlayer()
            8 -> deletePlayer()
            0 -> exit()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}

fun addPlayer() {
    // logger.info { "addNote() function invoked" }
    // val playerID = readNextInt("Enter a ID number: ")
    // println(Validations.validator)
    // val DOB = getDateInput("Enter DOB")
    // val DOB = readNextLine("Enter DOB: ")
    val name = readNextLine("Enter full name for player: ")
    var category: String
    do {
        category = readNextLine("Enter category name for player ($categories): ")
    } while (!CategoryUtility.isValidCategory(category))
    val dob = getDateInput("Enter DOB: ")
    var dateAsString = dob.toString()
    if (dob == LocalDate.now()) run {
        dateAsString = "0000-00-00"
        println("The date was invalid.")
    }
    var ageGroup: Int
    do {
        ageGroup = readNextInt("Enter age group: ")
    } while (ageGroup !in 6..39)
    val isAdded = api.add(Player(Name = name, Category = category, DOB = dateAsString, ageGroup = ageGroup))

    if (isAdded) {
        println("Added Successfully")
        api.listPlayers()
    } else {
        println("Add Failed")
    }
}

fun listPlayers() {
    println(api.listPlayers())
}

fun numOfPlayers() {
    println(api.numberOfPlayers())
}

fun searchByID() {
    val searchID = readNextInt("Enter the playerID: ")
    val searchResult = api.searchByIndex(searchID)
    if (searchResult.isEmpty()) {
        println("No players found")
    } else {
        println(searchResult)
    }
}

fun save() {
    try {
        api.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        api.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun updatePlayer() {
    // logger.info { "updateNotes() function invoked" }
    listPlayers()
    if (api.numberOfPlayers() > 0) {
        // only ask the user to choose the note if notes exist
        val indexToUpdate = readNextInt("Enter a Index number:")
        if (api.isValidIndex(indexToUpdate)) {
            val name = readNextLine("Enter full name for player: ")
            var category: String
            do {
                category = readNextLine("Enter category name for player ($categories): ")
            } while (!CategoryUtility.isValidCategory(category))
            val dob = getDateInput("Enter DOB: ")
            var dateAsString = dob.toString()
            if (dob == LocalDate.now()) run {
                dateAsString = "0000-00-00"
                println("The date was invalid.")
            }
            var ageGroup: Int
            do {
                ageGroup = readNextInt("Enter age group: ")
            } while (ageGroup !in 6..39)
            if (api.updateNote(indexToUpdate, Player(Name = name, Category = category, DOB = dateAsString, ageGroup = ageGroup))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no players for this index number")
        }
    }
}

fun deletePlayer() {
    listPlayers()
    if (api.numberOfPlayers() > 0) {
        // only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the note to delete: ")
        // pass the index of the note to NoteAPI for deleting and check for success.
        val noteToDelete = api.deletePlayer(indexToDelete)
        if (noteToDelete != null) {
            println("Delete Successful! Deleted note: ${noteToDelete.playerID}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun exit() {
    logger.info { "exit function invoked" }
    exitProcess(0)
}
