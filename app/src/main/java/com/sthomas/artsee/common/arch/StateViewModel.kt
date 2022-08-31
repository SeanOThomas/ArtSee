package com.sthomas.artsee.common.arch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class StateViewModel<S>(initialState: S) : ViewModel() {
    var state by mutableStateOf(initialState)
        private set

    fun setState(reducer: S.() -> S) {
        state = reducer(state)
    }
}