package com.hjc.module_home.http.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.util.*

class ConcertBean : BaseObservable() {
    @get:Bindable
    var title: String? = null

    @get:Bindable
    var author: String? = null

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (this.javaClass == obj.javaClass) {
            return true
        }
        val concert = obj as ConcertBean
        return title == concert.title && author == concert.author
    }

    override fun hashCode(): Int {
        return Objects.hash(title, author)
    }
}