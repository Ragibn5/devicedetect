package com.ragibn5.devicedetect.utils

import java.io.File

/**
 * Simple terminal interface.
 */
interface Terminal {
    /**
     * Execute command.
     *
     * @param command The command to run.
     * @param workingDir The current working directory where the command will run.
     * @return The result of the command, or null if the output is blank
     * (empty, or solely consists of whitespace character).
     * */
    fun executeCommand(
        command: String,
        workingDir: File? = null,
        trim: Boolean = true,
        nullOnEmptyOrBlank: Boolean = true,
    ): String?
}

/**
 * Default terminal implementation.
 * */
class DefaultTerminal : Terminal {
    override fun executeCommand(
        command: String,
        workingDir: File?,
        trim: Boolean,
        nullOnEmptyOrBlank: Boolean
    ): String? {
        val fullCommand = (workingDir?.absolutePath?.plus(";") ?: "") + command
        val output = Runtime.getRuntime()
            .exec(fullCommand)
            .inputStream
            .bufferedReader()
            .use { it.readText() }

        val result = if (trim) output.trim() else output
        return if (nullOnEmptyOrBlank) result.ifBlank { null } else result
    }
}