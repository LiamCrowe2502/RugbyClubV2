package controllers

import controller.API
import model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistance.XMLSerializer
import persistence.JSONSerializer
import java.io.File
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNull

class APITest {

    private var learnKotlin: Player? = null
    private var summerHoliday: Player? = null
    private var codeApp: Player? = null
    private var testApp: Player? = null
    private var swim: Player? = null
    private var populatedNotes: API? = API(JSONSerializer(File("players.json")))
    private var emptyNotes: API? = API(XMLSerializer(File("players.xml")))

    @BeforeEach
    fun setup() {
        learnKotlin = Player("Learning Kotlin", "Forwrds", 20, "2018-12-12")
        summerHoliday = Player("Summer Holiday to France", "Forwrds", 20, "2018-12-12")
        codeApp = Player("Code App", "Forwrds", 20, "2002-01-25")
        testApp = Player("Test App", "Forwrds", 20, "2002-01-25")
        swim = Player("Swim - Pool", "Forwrds", 20, "2002-01-25")

        // adding 5 Note to the notes api
        populatedNotes!!.add(learnKotlin!!)
        populatedNotes!!.add(summerHoliday!!)
        populatedNotes!!.add(codeApp!!)
        populatedNotes!!.add(testApp!!)
        populatedNotes!!.add(swim!!)
    }

    @AfterEach
    fun tearDown() {
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populatedNotes = null
        emptyNotes = null
    }

    @Test
    fun `adding a Player to a populated list adds to ArrayList`() {
        val newNote = Player("Study Lambdas", "Forwrds", 20, "2002-01-25")
        assertEquals(5, populatedNotes!!.numberOfPlayers())
        assertTrue(populatedNotes!!.add(newNote))
        assertEquals(6, populatedNotes!!.numberOfPlayers())
        assertEquals(newNote, populatedNotes!!.findPlayer(populatedNotes!!.numberOfPlayers() - 1))
    }

    @Test
    fun `adding a Player to an empty list adds to ArrayList`() {
        val newNote = Player("Study Lambdas", "Forwrds", 20, "2002-01-25")
        assertEquals(0, emptyNotes!!.numberOfPlayers())
        assertTrue(emptyNotes!!.add(newNote))
        assertEquals(1, emptyNotes!!.numberOfPlayers())
        assertEquals(newNote, emptyNotes!!.findPlayer(emptyNotes!!.numberOfPlayers() - 1))
    }

    @Test
    fun `listAllPlayers returns No Notes Stored message when ArrayList is empty`() {
        assertEquals(0, emptyNotes!!.numberOfPlayers())
        assertTrue(emptyNotes!!.listPlayers().lowercase().contains("no players"))
    }

    @Test
    fun `listAllPlayers returns Notes when ArrayList has notes stored`() {
        assertEquals(5, populatedNotes!!.numberOfPlayers())
        val notesString = populatedNotes!!.listPlayers().lowercase()
        assertTrue(notesString.contains("learning kotlin"))
        assertTrue(notesString.contains("code app"))
        assertTrue(notesString.contains("test app"))
        assertTrue(notesString.contains("swim"))
        assertTrue(notesString.contains("summer holiday"))
    }

    @Nested
    inner class UpdateNotes {
        @Test
        fun `updating a note that does not exist returns false`() {
            assertFalse(populatedNotes!!.updateNote(6, Player("Updating", "Backs", 13, "0000-00-00")))
            assertFalse(populatedNotes!!.updateNote(-1, Player("Updating", "Backs", 12, "2002-12-12")))
            assertFalse(emptyNotes!!.updateNote(0, Player("Updating", "Forwards", 12, "2002-12-12")))
        }
    }

    @Nested
    inner class DeleteNotes {

        @Test
        fun `deleting a Note that does not exist, returns null`() {
            assertNull(emptyNotes!!.deletePlayer(0))
            assertNull(populatedNotes!!.deletePlayer(-1))
            assertNull(populatedNotes!!.deletePlayer(5))
        }

        @Test
        fun `deleting a note that exists delete and returns deleted object`() {
            assertEquals(5, populatedNotes!!.numberOfPlayers())
            assertEquals(swim, populatedNotes!!.deletePlayer(4))
            assertEquals(4, populatedNotes!!.numberOfPlayers())
            assertEquals(learnKotlin, populatedNotes!!.deletePlayer(0))
            assertEquals(3, populatedNotes!!.numberOfPlayers())
        }
    }

    @Test
    fun `saving and loading an empty collection in JSON doesn't crash app`() {
        // Saving an empty notes.json file.
        val storingNotes = API(JSONSerializer(File("players.json")))
        storingNotes.store()

        // Loading the empty notes.json file into a new object
        val loadedNotes = API(JSONSerializer(File("players.json")))
        loadedNotes.load()

        // Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
        assertEquals(0, storingNotes.numberOfPlayers())
        assertEquals(0, loadedNotes.numberOfPlayers())
        assertEquals(storingNotes.numberOfPlayers(), loadedNotes.numberOfPlayers())
    }

    @Test
    fun `saving and loading an loaded collection in JSON doesn't loose data`() {
        // Storing 3 notes to the notes.json file.
        val storingNotes = API(JSONSerializer(File("players.json")))
        storingNotes.add(testApp!!)
        storingNotes.add(swim!!)
        storingNotes.add(summerHoliday!!)
        storingNotes.store()

        // Loading notes.json into a different collection
        val loadedNotes = API(JSONSerializer(File("players.json")))
        loadedNotes.load()

        // Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
        assertEquals(3, storingNotes.numberOfPlayers())
        assertEquals(3, loadedNotes.numberOfPlayers())
        assertEquals(storingNotes.numberOfPlayers(), loadedNotes.numberOfPlayers())
        assertEquals(storingNotes.findPlayer(0), loadedNotes.findPlayer(0))
        assertEquals(storingNotes.findPlayer(1), loadedNotes.findPlayer(1))
        assertEquals(storingNotes.findPlayer(2), loadedNotes.findPlayer(2))
    }
}

