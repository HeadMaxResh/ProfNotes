package com.example.profnotes.presentation.viewmodel

import android.widget.ImageView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.R
import com.example.profnotes.data.database.Database
import com.example.profnotes.data.database.model.LocalNoteEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.squareup.picasso.Picasso

class LocalNoteViewModel(state: SavedStateHandle) : ViewModel() {

    private val _noteState: MutableStateFlow<LocalNoteEntity> =
        MutableStateFlow(
            LocalNoteEntity(
                id = -1,
                title = "",
                content = emptyArray(),
                date = 0,
                comments = null,
            )
        )

    val noteState: StateFlow<LocalNoteEntity> = _noteState.asStateFlow()

    private val _isBookmarked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBookmarked = _isBookmarked.asStateFlow()

    private val dao = Database.bookmarkDao

    init {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<LocalNoteEntity> = moshi.adapter(LocalNoteEntity::class.java)

        val json: String = state["content"]!!

        viewModelScope.launch {
            _noteState.emit(adapter.fromJson(json)!!)

            dao.isLocalNoteBookmarked(_noteState.value.id).collect {
                _isBookmarked.emit( it > 0)
            }
        }
    }

    fun loadImage(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(view)
    }

    fun bookmark() =
        if (_isBookmarked.value) removeFromBookmarks()
        else addToBookmarks()

    private fun addToBookmarks() {
        _noteState.value.isBookmarked = true
        viewModelScope.launch {
            dao.updateBookmarkLocalNote(_noteState.value)
        }
    }

    private fun removeFromBookmarks() {
        _noteState.value.isBookmarked = false
        viewModelScope.launch {
            dao.updateBookmarkLocalNote(_noteState.value)
        }
    }
}