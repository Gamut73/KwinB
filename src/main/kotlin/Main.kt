package org.artificery

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import org.artificery.commands.Create
import org.artificery.commands.KwinB

fun main(args: Array<String>) = KwinB()
    .subcommands(
        Create()
    )
    .main(args)