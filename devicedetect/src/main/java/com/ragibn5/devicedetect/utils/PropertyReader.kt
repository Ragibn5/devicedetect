package com.ragibn5.devicedetect.utils

/**
 * Simple terminal interface.
 */
fun interface PropertyReader {
    /**
     * Get a system property.
     *
     * @param propName The name or id of the property.
     * @return The value of the property (as string), or null if
     * */
    fun getProp(propName: String): String?
}


class DefaultPropertyReader(private val terminal: Terminal) : PropertyReader {
    override fun getProp(propName: String): String? {
        return runCatching { terminal.executeCommand("getprop $propName") }.getOrElse {
            it.printStackTrace()
            return null
        }
    }
}
