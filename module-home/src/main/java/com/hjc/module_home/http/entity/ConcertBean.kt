package com.hjc.module_home.http.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.util.*

class ConcertBean : BaseObservable() {
    @get:Bindable
    var title: String? = null

    @get:Bindable
    var author: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null) {
            return false
        }
        if (this.javaClass == other.javaClass) {
            return true
        }
        val concert = other as ConcertBean
        return title == concert.title && author == concert.author
    }

    override fun hashCode(): Int {
        return Objects.hash(title, author)
    }
}