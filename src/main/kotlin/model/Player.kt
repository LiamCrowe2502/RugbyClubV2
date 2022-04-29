package model

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

//create an extension function on a date class which returns a string
private fun Date.dateToString(format: String): String {
    //simple date formatter
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

    //return the formatted date string
    return dateFormatter.format(this)
}

class Player(
    var Name:String = "",
    var ageGroup:Int = 18,
    //var DOB: String = ""
    var timestamp: Date = Date(),
    var DOB: String = timestamp.dateToString("YYYY-MM-DD")
    //var DOB: LocalDate? = LocalDate.parse("yyyy-MM-dd")
) {

    companion object {
        private val count: AtomicInteger = AtomicInteger(100)
    }

    val playerID = count.incrementAndGet()
    override fun toString(): String {
        return "Player(id=$playerID, name = '$Name', age = '$ageGroup' dob = '$DOB')"
    }

}