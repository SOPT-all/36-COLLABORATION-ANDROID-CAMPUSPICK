package org.sopt.collaboration.campuspick.domain.model

enum class DeadLine(val label: String) {
    EMPTY(""),
    ALL("전체"),
    UNDER_WEEK("7일 이하"),
    OVER_WEEK("7일 이상"),
    OVER_TWO_WEEK("14일 이상"),
    OVER_MONTH("30일 이상");

    companion object {
        fun DeadLine.Companion.fromLabel(label: String): DeadLine {
            return entries.find { it.label == label } ?: EMPTY
        }
    }
}

enum class Location(val label: String) {
    EMPTY(""),
    NATIONWIDE("전국"),
    METROPOLITAN("수도권"),
    CHUNGCHEONGDO("충북/충남/대전"),
    JEOLLABUKDO("전북"),
    JEOLLANAMDO("전남/광주"),
    GYEONGSANGBUKDO("경북/대구"),
    GYOUNGSANGNAMDO("경남/부산/울산"),
    GANGWONDO("강원"),
    JEJUDO("제주"),
    ETC("기타");

    companion object {
        fun Location.Companion.fromLabel(label: String): Location {
            return Location.entries.find { it.label == label } ?: EMPTY
        }
    }
}

enum class PreferDay(val label: String) {
    EMPTY(""),
    All("전체"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    WEEKEND("주말");

    companion object {
        fun PreferDay.Companion.fromLabel(label: String): PreferDay {
            return PreferDay.entries.find { it.label == label } ?: EMPTY
        }
    }
}