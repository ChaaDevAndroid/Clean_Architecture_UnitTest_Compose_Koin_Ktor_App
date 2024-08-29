package com.chaaba.core

import java.lang.RuntimeException

class FailedToRequestException(message: String? = null) : RuntimeException(message)