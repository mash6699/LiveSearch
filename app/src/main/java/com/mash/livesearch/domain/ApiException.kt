package com.mash.livesearch.domain

import java.io.IOException

class ApiException(val code: Int, message: String): IOException(message)