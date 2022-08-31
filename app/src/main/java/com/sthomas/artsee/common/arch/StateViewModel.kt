package com.sthomas.artsee.common.arch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Reduces boiler plate for a view model with a single UI state for a Compose screen.
 * Use [setState] to reduce the UI state.
 */
abstract class StateViewModel<S>(initialState: S) : ViewModel() {
    var state by mutableStateOf(initialState)
        private set

    fun setState(reducer: S.() -> S) {
        state = reducer(state)
    }
}