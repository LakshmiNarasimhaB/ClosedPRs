package com.sample.pulls.utils

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateUtilsKtTest {

    /**
     * date set to run tests on.
     */
    val dateSet: Map<String, Date> = mapOf(
        Pair("Tue, 20 Jan 06:15 AM", Date(1644342092)),
        Pair("Tue, 20 Jan 04:03 AM", Date(1636393292)),
        Pair("Tue, 20 Jan 08:13 AM", Date(1651426892)),
        Pair("Tue, 20 Jan 08:23 AM", Date(1651988492)),
    )

    @Test
    fun getDateWithDay() {
        for ((key, value) in dateSet.entries) {
            val resolvedDate = getDateWithDay(value)
            assertEquals(key, resolvedDate)
        }
    }
}
