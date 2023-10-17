package com.route.newsappc38gsat.fragments.news

import com.example.data.api.model.ArticlesItem
import com.example.domain.entity.ArticlesItemDTO

enum class WEEKDAYS(val dayId: Int) {
    SATURDAY(0),
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6)


}

val day1 = WEEKDAYS.SATURDAY

sealed class NewsViewEvents {
    object Idle : NewsViewEvents()
    class EnteredNewsScreen(val category: String) : NewsViewEvents()
    class SelectTabEvent(val sourceId: String) : NewsViewEvents()
    class ClickedOnNewsItem(val articlesItem: ArticlesItemDTO) : NewsViewEvents()

}
