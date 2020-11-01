package com.example.example.dsl

import java.time.LocalDateTime

class Meetup(val name: String, val location: String) {

    private val schedule = mutableListOf<Talk>()

    fun addTalk(t: Talk) {
        schedule.add(t)
    }

    val talks
        get() = schedule.toList()

}

data class Talk (
    val topic: String,
    val speaker: String,
    val time: LocalDateTime
)

fun main() {

    // not DSL
    val chocoMeetup = Meetup("ChocoDevDay", "Zoom")
    val t1 = Talk("Анимации в Android", "Руслан", LocalDateTime.parse("2020-10-28T17:00"))
    val t2 = Talk("Magical Kotlin", "Kudri", LocalDateTime.parse("2020-10-28T18:00"))
    chocoMeetup.addTalk(t1)
    chocoMeetup.addTalk(t2)
    // send it to the server

    // DSL
    meetup {
        name = "ChocoDevDay"
        location = "Zoom"

        talks {
            talk at "2020-10-28T17:00" by "Руслан" titled "Анимации в Android"
            talk at "2020-10-28T18:00" by "Kudri" titled "Magical Kotlin"
        }
    }
    // send it to the server

}

inline fun meetup(config: MeetupDSL.() -> Unit): Meetup {
    val dsl = MeetupDSL().apply(config)
    return Meetup(dsl.name, dsl.location).apply {
        dsl.talkList.forEach(this::addTalk)
    }
}

class MeetupDSL {

    private val _talkList = mutableListOf<Talk>()

    val talkList
        get() = _talkList.toList()

    lateinit var name: String
    lateinit var location: String

    val talks = TalkConfigDSL()

    inner class TalkConfigDSL {

        private val _talkList = this@MeetupDSL._talkList

        operator fun invoke(config: TalkConfigDSL.() -> Unit) {
            this.apply(config)
        }

        val talk
            get() = EmptyTalk()

        inner class EmptyTalk {
            infix fun at(timeString: String) = TimedTalk(LocalDateTime.parse(timeString))
        }

        inner class TimedTalk(val time: LocalDateTime) {
            infix fun by(speaker: String) = TimedAndAuthoredTalk(this, speaker)
        }

        inner class TimedAndAuthoredTalk(private val previous: TimedTalk, private val speaker: String) {
            infix fun titled(topic: String) = _talkList.add(Talk(topic, speaker, previous.time))
        }

    }

}







