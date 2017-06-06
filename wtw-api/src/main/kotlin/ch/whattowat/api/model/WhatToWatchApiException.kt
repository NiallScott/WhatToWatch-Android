package ch.whattowat.api.model

class WhatToWatchApiException : Exception {

    constructor() : super("An exception occured while loading data from the What To Watch API.")

    constructor(message: String) : super(message)

    constructor(cause: Throwable) : super(cause)

    constructor(message: String, cause: Throwable) : super(message, cause)
}