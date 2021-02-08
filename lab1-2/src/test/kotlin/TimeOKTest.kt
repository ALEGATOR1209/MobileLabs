import org.junit.Assert
import org.junit.Test
import ua.kpi.comsys.ip8410.time.TimeOK
import java.lang.IllegalStateException
import java.util.*

class TimeOKTest {
    @Test
    fun `test initialization`() {
        val time1 = TimeOK()
        Assert.assertEquals(
            "Default time should be 00:00:00",
            listOf(0, 0, 0),
            listOf(time1.hours, time1.minutes, time1.seconds)
        )

        val time2 = TimeOK(10, 11, 12)
        Assert.assertEquals(
            "Custom time should be 10:11:12",
            listOf(10, 11, 12),
            listOf(time2.hours, time2.minutes, time2.seconds)
        )

        try {
            TimeOK(-20, 20, 10)
            Assert.fail("Should not create time with negative hours")
        } catch (e: IllegalStateException) {}

        try {
            TimeOK(20, -20, 10)
            Assert.fail("Should not create time with negative minutes")
        } catch (e: IllegalStateException) {}

        try {
            TimeOK(20, 20, -10)
            Assert.fail("Should not create time with negative seconds")
        } catch (e: IllegalStateException) {}

        try {
            TimeOK(24, 20, 10)
            Assert.fail("Should not create time with hours > 23")
        } catch (e: IllegalStateException) {}

        try {
            TimeOK(20, 60, 10)
            Assert.fail("Should not create time with minutes > 59")
        } catch (e: IllegalStateException) {}

        try {
            TimeOK(20, 20, 60)
            Assert.fail("Should not create negative time")
        } catch (e: IllegalStateException) {}
    }

    @Test
    fun `test toString`() {
        val time1 = TimeOK(10, 10, 20)
        Assert.assertEquals("10:10:20 AM", time1.toString())

        val time2 = TimeOK(12, 20, 40)
        Assert.assertEquals("12:20:40 AM", time2.toString())

        val time3 = TimeOK(14, 20, 40)
        Assert.assertEquals("2:20:40 PM", time3.toString())

        val time4 = TimeOK()
        Assert.assertEquals("0:0:0 AM", time4.toString())
    }

    @Test
    fun `test from date`() {
        val date = GregorianCalendar(
            2021, // year
            10,   // month
            20,   // day
            12, 45, 56 // hh, mm, ss
        ).time
        val time1 = TimeOK.from(date)

        Assert.assertEquals(
            "Should parse time from date",
            listOf(12, 45, 56),
            listOf(time1.hours, time1.minutes, time1.seconds)
        )
    }

    @Test
    fun `test time plus`() {
        val time1 = TimeOK(10, 10, 10)
        val time2 = TimeOK(5, 20, 30)
        val time3 = time1 + time2

        Assert.assertEquals(
            listOf(15, 30, 40),
            listOf(time3.hours, time3.minutes, time3.seconds)
        )

        val time4 = TimeOK(23, 59, 59)
        val time5 = TimeOK(12, 0, 1)
        val time6 = time4 + time5

        Assert.assertEquals(
            listOf(12, 0, 0),
            listOf(time6.hours, time6.minutes, time6.seconds)
        )
    }

    @Test
    fun `test time minus`() {
        val time1 = TimeOK(10, 10, 10)
        val time2 = TimeOK(5, 20, 30)
        val time3 = time1 - time2

        Assert.assertEquals(
            listOf(4, 49, 40),
            listOf(time3.hours, time3.minutes, time3.seconds)
        )

        val time4 = TimeOK()
        val time5 = TimeOK(seconds = 1)
        val time6 = time4 - time5

        Assert.assertEquals(
            listOf(23, 59, 59),
            listOf(time6.hours, time6.minutes, time6.seconds)
        )
    }
}
