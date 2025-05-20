package org.sopt.collaboration.campuspick.domain.model

enum class DeadLine(val label: String) {
    EMPTY(""),
    ALL("전체"),
    UNDER7("7일 이하"),
    OVER7("7일 이상"),
    OVER14("14일 이상"),
    OVER30("30일 이상");

    val replaceDeadLine: String?
        get() = if (this == ALL) null else name

    companion object {
        fun DeadLine.Companion.fromLabel(label: String): DeadLine {
            return entries.find { it.label == label } ?: EMPTY
        }
    }
}

enum class Region(val label: String) {
    EMPTY(""),
    NATIONWIDE("전국"),
    CAPITAL_REGION("수도권"),
    CHUNGCHEONG_DAEJEON("충북/충남/대전"),
    JEONBUK("전북"),
    JEONNAM_GWANGJU("전남/광주"),
    GYEONGBUK_DAEGU("경북/대구"),
    GYEONGNAM_BUSAN_ULSAN("경남/부산/울산"),
    GANGWON("강원"),
    JEJU("제주"),
    ETC("기타");

    val replaceRegion: String?
        get() = if (this == NATIONWIDE) null else name

    companion object {
        fun Region.Companion.fromLabel(label: String): Region {
            return Region.entries.find { it.label == label } ?: EMPTY
        }
    }
}

enum class PreferDay(val label: String) {
    EMPTY(""),
    ALL("전체"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    WEEKEND("주말");

    val replaceDay: String?
        get() = if (this == ALL) null else name

    companion object {
        fun PreferDay.Companion.fromLabel(label: String): PreferDay {
            return PreferDay.entries.find { it.label == label } ?: EMPTY
        }
    }
}