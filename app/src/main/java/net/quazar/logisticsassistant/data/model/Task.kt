package net.quazar.logisticsassistant.data.model

class Task(val id: Long?, val isCurrentTask: Boolean = false,
           val description: TaskDescription) {
}