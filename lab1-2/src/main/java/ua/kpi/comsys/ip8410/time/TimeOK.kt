package ua.kpi.comsys.ip8410.time

import java.util.*

class TimeOK(
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0
) {
    val hours = if (hours in 0..23) hours else error("Hours out of bounds: $hours")
    val minutes = if (minutes in 0..59) minutes else error("Minutes out of bounds: $minutes")
    val seconds = if (seconds in 0..59) seconds else error("Seconds out of bounds: $seconds")

    override fun toString() = buildString {
        var hoursFormatted = hours
        var zz = "AM"
        if (hours !in 0..12) {
            zz = "PM"
            hoursFormatted = hours - 12
        }

        append(hoursFormatted)
        append(':')
        append(minutes)
        append(':')
        append(seconds)
        append(' ')
        append(zz)
    }

    operator fun plus(that: TimeOK): TimeOK {
        var hours = 0
        var minutes = 0
        val seconds = (this.seconds + that.seconds).let {
            if (it > 59) {
                minutes++
                it - 60
            } else {
                it
            }
        }

        minutes = (minutes + this.minutes + that.minutes).let {
            if (it > 59) {
                hours++
                it - 60
            } else {
                it
            }
        }

        hours = (hours + this.hours + that.hours).let {
            if (it > 23) it - 24 else it
        }

        return TimeOK(hours, minutes, seconds)
    }

    operator fun minus(that: TimeOK): TimeOK {
        var hours = 0
        var minutes = 0

        val seconds = (this.seconds - that.seconds).let {
            if (it < 0) {
                minutes--
                60 + it
            } else {
                it
            }
        }

        minutes = (minutes + this.minutes - that.minutes).let {
            if (it < 0) {
                hours--
                60 + it
            } else {
                it
            }
        }

        hours = (hours + this.hours - that.hours).let {
            if (it < 0) 24 + it else it
        }

        return TimeOK(hours, minutes, seconds)
    }

    companion object {
        fun from(date: Date): TimeOK = with(GregorianCalendar()) {
            time = date

            val hours = get(Calendar.HOUR_OF_DAY)
            val minutes = get(Calendar.MINUTE)
            val seconds = get(Calendar.SECOND)

            TimeOK(hours, minutes, seconds)
        }
    }
}
