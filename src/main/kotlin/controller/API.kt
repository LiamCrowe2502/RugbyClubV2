package controller

import model.Player
import persistence.Serializer

class API(serializerType: Serializer){

    private var serializer: Serializer = serializerType
    private var players = ArrayList<Player>()


    /**
     * Adds a player to the arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The arraylist is return as a list.
     */
    fun add(player: Player): Boolean {
        return players.add(player)
    }

    /**
     * List all the players in the arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The arraylist is return as a list.
     */
    fun listPlayers(): String =
        if  (players.isEmpty()) "No Players stored"
        else players.joinToString (separator = "\n") { player ->
            players.indexOf(player).toString() + ": " + player.toString() }

    /**
     * Returns the number of the players in the arraylists
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The the size of the arraylist.
     */
    fun numberOfPlayers(): Int {
        return players.size
    }

    /**
     * Checks if the index number is the within the arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     */
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    /**
     * Adds a player to the arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The arraylist is return as a list.
     */
    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, players)
    }

    /**
     * Adds a player to the arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The arraylist is return as a list.
     */
    fun updateNote(indexToUpdate: Int, note: Player?): Boolean {
        //find the note object by the index number
        val foundPlayer = findNote(indexToUpdate)

        //if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundPlayer != null) && (note != null)) {
            foundPlayer.Name = note.Name
            foundPlayer.Category = note.Category
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

    /**
     * If the index number is valid than removes the player at that index.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return return only if the index is in the arraylist.
     */
    fun deleteNote(indexToDelete: Int): Player? {
        return if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else null
    }

    fun formatListString(playersToFormat : List<Player>) :String =
        playersToFormat
            .joinToString (separator = "\n") { player ->
                players.indexOf(player).toString() + ": " + player.toString() }

    fun searchByIndex (searchString : Int) =
        formatListString(
            players.filter { Player -> Player.playerID==(searchString) })

    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(players)
    }
}
