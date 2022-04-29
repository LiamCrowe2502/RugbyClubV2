package controllers

import controller.API
import model.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class APITest {

    private var learnKotlin: Player? = null
    private var summerHoliday: Player? = null
    private var codeApp: Player? = null
    private var testApp: Player? = null
    private var swim:  Player? = null
    private var populatedNotes: API? = API()
    private var emptyNotes: API? = API()

    @BeforeEach
    fun setup(){
        learnKotlin = Player("Learning Kotlin", 5, "College")
        summerHoliday = Player("Summer Holiday to France", 1, "Holiday")
        codeApp =  Player("Code App", 4, "Work")
        testApp =  Player("Test App", 4, "Work")
        swim = Player("Swim - Pool", 3, "Hobby")

        //adding 5 Note to the notes api
        populatedNotes!!.add(learnKotlin!!)
        populatedNotes!!.add(summerHoliday!!)
        populatedNotes!!.add(codeApp!!)
        populatedNotes!!.add(testApp!!)
        populatedNotes!!.add(swim!!)
    }

    @AfterEach
    fun tearDown(){
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populatedNotes = null
        emptyNotes = null
    }

    @Test
    fun `adding a Note to a populated list adds to ArrayList`(){
        val newNote = Player("Study Lambdas", 1, "College")
        assertEquals(5, populatedNotes!!.numberOfPlayers())
        assertTrue(populatedNotes!!.add(newNote))
        assertEquals(6, populatedNotes!!.numberOfPlayers())
        assertEquals(newNote, populatedNotes!!.findNote(populatedNotes!!.numberOfPlayers() - 1))
    }

    @Test
    fun `adding a Note to an empty list adds to ArrayList`(){
        val newNote = Player("Study Lambdas", 1, "College")
        assertEquals(0, emptyNotes!!.numberOfPlayers())
        assertTrue(emptyNotes!!.add(newNote))
        assertEquals(1, emptyNotes!!.numberOfPlayers())
        assertEquals(newNote, emptyNotes!!.findNote(emptyNotes!!.numberOfPlayers() - 1))
    }
}