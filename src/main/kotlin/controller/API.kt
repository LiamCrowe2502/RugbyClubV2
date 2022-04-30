package controller

import model.Player
import persistence.Serializer

class API(serializerType: Serializer) {

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
        if (players.isEmpty()) "No Players stored"
        else players.joinToString(separator = "\n") { player ->
            players.indexOf(player).toString() + ": " + player.toString()
        }

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
    private fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    /**
     * Checks if index is valid.
     *
     * @param prompt  The information printed to the console for the user to read
     */
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, players)
    }

    /**
     * Updates the details of the player
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The arraylist is return as a list.
     */
    fun updateNote(indexToUpdate: Int, foundPlay: Player?): Boolean {
        // find the note object by the index number
        val foundPlayer = findPlayer(indexToUpdate)

        // if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundPlayer != null) && (foundPlay != null)) {
            foundPlayer.Name = foundPlay.Name
            foundPlayer.Category = foundPlay.Category
            foundPlayer.DOB = foundPlay.DOB
            foundPlayer.ageGroup = foundPlay.ageGroup
            return true
        }
        // if the note was not found, return false, indicating that the update was not successful
        return false
    }

    /**
     * Returns the number of the players in the arraylists
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The the size of the arraylist.
     */
    fun findPlayer(index: Int): Player? {
        return if (isValidListIndex(index, players)) {
            players[index]
        } else null
    }

    /**
     * checks if index is valid to a player and delete it at that index.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return return only if the index is in the arraylist.
     */
    fun deletePlayer(indexToDelete: Int): Player? {
        return if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else null
    }

    /**
     * Returns the number of the players in the arraylists
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The the size of the arraylist.
     */
    private fun formatListString(playersToFormat: List<Player>): String =
        playersToFormat
            .joinToString(separator = "\n") { player ->
                players.indexOf(player).toString() + ": " + player.toString()
            }

    /**
     * Returns a list of the player details, Checks to see is playerID is in arraylist.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The player in the list.
     */
    fun searchByIndex(searchString: Int) =
        formatListString(
            players.filter { Player -> Player.playerID == (searchString) }
        )

    /**
     * Calls the mothods to load the files from the XML and JSON
     *
     * @param prompt  The information is loaded from .XML or .JSON to arraylist
     * @return The the size of the arraylist.
     */
    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }

    /**
     * Saves the details to the .JSON or .XML file.
     *
     * @param prompt  The info is saved to file which can be loaded later.
     * @return Returns a saved file in project.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(players)
    }
}
