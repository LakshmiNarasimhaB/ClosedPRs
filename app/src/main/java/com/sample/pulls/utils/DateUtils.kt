package com.sample.pulls.utils

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT_INFO = "EEE, dd MMM HH:mm a"

/**
 * Gives 22/1/2020 as Wed, 22nd Jan.
 * @param date date
 * @return date in human readable format.
 */
fun getDateWithDay(date: Date?): String? {
    return if (date == null) {
        null
    } else {
        SimpleDateFormat(
            DATE_FORMAT_INFO,
            Locale.getDefault()
        ).format(date)
    }
}
