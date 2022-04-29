package controller

import model.Player

class API {
    private var players = ArrayList<Player>()

    fun add(player: Player): Boolean {
        return players.add(player)
    }

    fun listPlayers(): String =
        if  (players.isEmpty()) "No Players stored"
        else players.joinToString (separator = "\n") { player ->
            players.indexOf(player).toString() + ": " + player.toString() }

    fun numberOfPlayers(): Int {
        return players.size
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, players)
    }

    fun updateNote(indexToUpdate: Int, note: Player?): Boolean {
        //find the note object by the index number
        val foundPlayer = findNote(indexToUpdate)

        //if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundPlayer != null) && (note != null)) {
            foundPlayer.Name = note.Name
            foundPlayer.DOB = note.DOB
            foundPlayer.ageGroup = note.ageGroup
            return true
        }
        //if the note was not found, return false, indicating that the update was not successful
        return false
    }

    fun findNote(index: Int): Player? {
        return if (isValidListIndex(index, players)) {
            players[index]
        } else null
    }

    fun deleteNote(indexToDelete: Int): Player? {
        return if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else null
    }
}