import controller.API
import model.Player
import mu.KotlinLogging
import utils.Scanner.Scanner.readNextInt
import utils.Scanner.Scanner.readNextLine
import java.time.LocalDate
import java.util.*

private val logger = KotlinLogging.logger {}
private val api = API()
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

fun addPlayer() {
    //logger.info { "addNote() function invoked" }
    //val playerID = readNextInt("Enter a ID number: ")
    //println(Validations.validator)
    //val DOB = getDateInput("Enter DOB")
    //val DOB = readNextLine("Enter DOB: ")
    var Name = readNextLine("Enter full name for player: ")
    var DOB = readNextLine("Enter DOB for player: ")
    var ageGroup = readNextInt("Enter age for player: ")

    val isAdded = api.add(Player(Name = Name, DOB = DOB, ageGroup = ageGroup))

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
    logger.info { "searchByID() function invoked" }
}

fun save() {
    logger.info { "save() function invoked" }
}

fun load() {
    logger.info { "load() function invoked" }
}

fun updatePlayer() {
    //logger.info { "updateNotes() function invoked" }
    listPlayers()
    if (api.numberOfPlayers() > 0) {
        //only ask the user to choose the note if notes exist
        val indexToUpdate = readNextInt("Enter a Index number:")
        if (api.isValidIndex(indexToUpdate)) {
            val Name = readNextLine("Enter full name for player: ")
            val ageGroup = readNextInt("Enter age: ")
            val DOB = readNextLine("Enter DOB: ")

            //pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (api.updateNote(indexToUpdate, Player(Name, DOB, ageGroup))){
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
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the note to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val noteToDelete = api.deleteNote(indexToDelete)
        if (noteToDelete != null) {
            println("Delete Successful! Deleted note: ${noteToDelete.playerID}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun exit() {
    logger.info { "exit function invoked" }
    System.exit(0)
}
