package paf.songrecorder.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {
        fun getCurrentDateAndTimeForNameAsString(): String {

            val sdf = SimpleDateFormat("dd_M_yyyy hh_mm_ss")
            return sdf.format(Date())
        }

        fun getCurrentDateAsString(): String {

            val sdf = SimpleDateFormat("dd/M/yyyy")
            return sdf.format(Date())
        }

        fun getCurrentDateAndTimeAsString(): String {

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            return sdf.format(Date())
        }
    }
}