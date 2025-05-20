package org.sopt.collaboration.campuspick.domain.model

enum class Category(val label: String) {
    ALL("전체"),
    CULTURE("문화/예술/공연"),
    VOLUNTEER("봉사/사회활동"),
    ACADEMIC("학술/교양"),
    JOB("취업/창업"),
    LANGUAGE("어학"),
    SPORTS("체육"),
    SOCIAL("친목"),
    ETC("기타");

    companion object {
        fun indexFromName(name: String?): Int {
            if (name == null) return 0
            val index = Category.entries.toTypedArray().indexOfFirst { it.name == name }
            return if (index != -1) index else 0
        }
    }
}