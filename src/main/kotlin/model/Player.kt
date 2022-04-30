package model

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

// create an extension function on a date class which returns a string
private fun Date.dateToString(format: String): String {
    // simple date formatter
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

    // return the formatted date string
    return dateFormatter.format(this)
}

var timestamp: Date = Date()

class Player(
    var Name: String = "",
    var Category: String = "",
    var ageGroup: Int = 18,
    // var DOB: String = ""
    var DOB: String = timestamp.dateToString("YYYY-MM-DD")
    // var DOB: LocalDate? = LocalDate.parse("yyyy-MM-dd")
) {

    companion object {
        private val count: AtomicInteger = AtomicInteger(100)
    }

    val playerID = count.incrementAndGet()
    override fun toString(): String {
        return "Player(id=$playerID, name = '$Name', category = '$Category', age = '$ageGroup' dob = '$DOB')"
    }
}
