package model

import java.time.LocalDate
import java.util.concurrent.atomic.AtomicInteger

class Player(
    var Name:String = "",
    var ageGroup:Int = 18,
    var DOB: String = ""
    //var DOB: LocalDate? = LocalDate.parse("yyyy-MM-dd")
) {

    companion object {
        private val count: AtomicInteger = AtomicInteger(100)
    }

    val playerID = count.incrementAndGet()
    override fun toString(): String {
        return "Player(id=$playerID, name='$Name', age='$ageGroup' dob='$DOB')"
    }

}