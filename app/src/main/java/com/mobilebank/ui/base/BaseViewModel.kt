package com.onsual.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.mobilebank.ui.base.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base class for [ViewModel] instances
 */
abstract class BaseViewModel<State : UiState> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

//    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
//    val event = _event.asSharedFlow()
//
//    private val _effect : Channel<Effect> = Channel()
//    val effect = _effect.receiveAsFlow()

    val event: SingleLiveEvent<Event> =
        SingleLiveEvent()

    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
//        viewModelScope.launch {
//            event.collect {
//                handleEvent(it)
//            }
//        }
    }

    /**
     * Handle each event
     */
//    abstract fun handleEvent(event : Event)


    /**
     * Set new Event
     */
//    fun setEvent(event : Event) {
//        val newEvent = event
//        viewModelScope.launch { _event.emit(newEvent) }
//    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

//    protected fun proceedFail(fail: Fail?) {
//        event.postValue(
//            Event.Alert(
//                fail?.message ?: "",
//                fail?.detail ?: "",
//            )
//        )
//    }

}

sealed class Event {

    class Alert(
        val title: String,
        val message: String,
        @StringRes val titleRes: Int? = null,
        @StringRes val messageRes: Int? = null
    ) : Event()

    class SnackBar(val title: String) : Event()
    class Toast(val title: String) : Event()
    class Busy(val show: Boolean) : Event()

}