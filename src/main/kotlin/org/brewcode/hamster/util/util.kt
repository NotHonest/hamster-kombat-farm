package org.brewcode.hamster.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

fun Int.rnd(left: Number = -20, right: Number = 20) = this + (left.toInt()..right.toInt()).random()
fun Long.rnd(left: Number = -20, right: Number = 20) = this + (left.toInt()..right.toInt()).random()

val mapper = ObjectMapper()
    .registerKotlinModule()
    .apply {
        findAndRegisterModules()
        enable(SerializationFeature.INDENT_OUTPUT)
    }

fun Any?.toJson(): String = mapper.writeValueAsString(this)

inline fun <reified T> String.fromJson(): T = mapper.readValue(this)

fun String.int() = filter(Char::isDigit).toInt()
fun String.double() = filter { it.isDigit() || it == '.' || it == ',' }.replace(",", ".").toDouble()

fun String.money() = when (last().uppercaseChar()) {
    'K' -> double() * 1000
    'M' -> double() * 1000000
    'B' -> double() * 1000000000
    else -> double()
}.toInt()


