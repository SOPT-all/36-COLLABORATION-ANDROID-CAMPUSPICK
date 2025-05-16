package org.sopt.collaboration.campuspick.domain.model

enum class Category(val label: String) {
    ALL("전체"),
    CULTURE_ART_PERFORMANCE("문화/예술/공연"),
    SERVICE_SOCIAL_ACTIVITY("봉사/사회활동"),
    ACADEMIC_LIBERAL_ART("학술/교양"),
    LANGUAGE("어학"),
    PHYSICAL("체육"),
    FRIENDSHIP("친목"),
    ETC("기타")
}