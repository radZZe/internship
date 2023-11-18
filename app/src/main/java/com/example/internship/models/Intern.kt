package com.example.internship.models

import kotlinx.serialization.Serializable

@Serializable
data class Intern(
    val baseInfo: BaseInfo,
    val sex: String,
    val age: Int,
    val speciality: String,
    val preferences: MutableList<String> = mutableListOf(),
    val position: String,
    val skills: MutableList<String> = mutableListOf(),
    val activeInternship: MutableList<String> = mutableListOf(),
    val archiveInternship: MutableList<String> = mutableListOf(),
)

enum class InternPosition(type: String){ // при получении данных стажера обращаться по индексу 0
    INTERN("Стажёр"),
    INTERN_SUPPORT("intern_support"),
    MANAGER("manager"),
    ADMINISTRATOR("administrator"),
    TEAM_MEMBER("team_member")
}

enum class WorkFormat(type: String) { // при получении данных стажера обращаться по индексу 1
    ONLINE("Онлайн"),
    OFFLINE("Оффлайн")
}
enum class Remuneration(type: String) { // при получении данных стажера обращаться по индексу 2
    PAID("Оплачеваемая"),
    UNPAID("Неоплачеваемая")
}
enum class WorkCity(type: String) { // при получении данных стажера обращаться по индексу 3
    INCITY("В городе пребывания"),
    OTHER("В другом")
}
enum class Duration(type: String) { // при получении данных стажера обращаться по индексу 4
    LONG("Долгосрочная"),
    SHORT("Краткосрочная")
}
enum class FollowingWork(type: String) { // при получении данных стажера обращаться по индексу 5
    WITH("С последующим трудоустройством"),
    WITHOUT("Без")
}