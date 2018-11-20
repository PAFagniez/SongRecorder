package paf.songrecorder.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {
        fun getCurrentDateAndTimeAsString(): String {

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            return sdf.format(Date())
        }
    }
}