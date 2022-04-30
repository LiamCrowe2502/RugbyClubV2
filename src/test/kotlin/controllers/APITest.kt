package controllers

import controller.API
import model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import persistence.XMLSerializer
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
    private var populatedNotes: API? = API()
    private var emptyNotes: API? = API()

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
    fun `adding a Note to a populated list adds to ArrayList`() {
        val newNote = Player("Study Lambdas", "Forwrds", 20, "2002-01-25")
        assertEquals(5, populatedNotes!!.numberOfPlayers())
        assertTrue(populatedNotes!!.add(newNote))
        assertEquals(6, populatedNotes!!.numberOfPlayers())
        assertEquals(newNote, populatedNotes!!.findPlayer(populatedNotes!!.numberOfPlayers() - 1))
    }

    @Test
    fun `adding a Note to an empty list adds to ArrayList`() {
        val newNote = Player("Study Lambdas", "Forwrds", 20, "2002-01-25")
        assertEquals(0, emptyNotes!!.numberOfPlayers())
        assertTrue(emptyNotes!!.add(newNote))
        assertEquals(1, emptyNotes!!.numberOfPlayers())
        assertEquals(newNote, emptyNotes!!.findPlayer(emptyNotes!!.numberOfPlayers() - 1))
    }

    @Test
    fun `listAllNotes returns No Notes Stored message when ArrayList is empty`() {
        assertEquals(0, emptyNotes!!.numberOfPlayers())
        assertTrue(emptyNotes!!.listPlayers().lowercase().contains("no players"))
    }

    @Test
    fun `listAllNotes returns Notes when ArrayList has notes stored`() {
        assertEquals(5, populatedNotes!!.numberOfPlayers())
        val notesString = populatedNotes!!.listPlayers().lowercase()
        assertTrue(notesString.contains("learning kotlin"))
        assertTrue(notesString.contains("code app"))
        assertTrue(notesString.contains("test app"))
        assertTrue(notesString.contains("swim"))
        assertTrue(notesString.contains("summer holiday"))
    }
}
