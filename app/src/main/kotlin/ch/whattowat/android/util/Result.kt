package ch.whattowat.android.util

class Result<S, E : Exception> {

    val success: S?
    val error: E?

    constructor(success: S?) {
        this.success = success
        error = null
    }

    constructor(error: E) {
        this.error = error
        success = null
    }

    fun isError() = error != null
}