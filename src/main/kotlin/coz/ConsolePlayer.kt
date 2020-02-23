package coz

import kotlin.concurrent.thread

class ConsolePlayer(private val field: Field) {
    fun loop() {
        thread {
            while (true) {
                println(field)
                listen()
            }
        }
    }

    private fun listen() {
        while (true) {
            try {
                val line = readLine()!!.split(' ')
                field.put(line[0].toInt(), line[1].toInt(), if (line[2] == "x") Cell.State.CROSS else Cell.State.ZERO)
                return
            } catch (e: Exception) {
                e.printStackTrace()
                println("invalid")
            }
        }
    }
}