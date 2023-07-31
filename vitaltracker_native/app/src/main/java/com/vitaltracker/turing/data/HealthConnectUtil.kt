package com.vitaltracker.turing.data

import androidx.health.connect.client.records.SleepStageRecord
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.random.Random

/**
 * Creates a [ZonedDateTime] either using the offset stored in Health Connect, or falling back on
 * the zone offset for the device, where Health Connect contains no zone offset data. This fallback
 * may be correct in a number of circumstances, but may also not apply in others, so is used here
 * just as an example.
 */
fun dateTimeWithOffsetOrDefault(time: Instant, offset: ZoneOffset?): ZonedDateTime =
    if (offset != null) {
        ZonedDateTime.ofInstant(time, offset)
    } else {
        ZonedDateTime.ofInstant(time, ZoneId.systemDefault())
    }

fun Duration.formatTime() = String.format(
    "%02d:%02d:%02d",
    this.toHours() % 24,
    this.toMinutes() % 60,
    this.seconds % 60
)

fun Duration.formatHoursMinutes() = String.format(
    "%01dh%02dm",
    this.toHours() % 24,
    this.toMinutes() % 60
)

/**
 * Generates a random sleep stage for the purpose of populating data. Excludes UNKNOWN sleep stage.
 */
fun randomSleepStage() = listOf(
    SleepStageRecord.STAGE_TYPE_AWAKE,
    SleepStageRecord.STAGE_TYPE_DEEP,
    SleepStageRecord.STAGE_TYPE_LIGHT,
    SleepStageRecord.STAGE_TYPE_OUT_OF_BED,
    SleepStageRecord.STAGE_TYPE_REM,
    SleepStageRecord.STAGE_TYPE_SLEEPING
).let { stages ->
    stages[Random.nextInt(stages.size)]
}
