package coz

class Cell {
    var state = State.EMPTY

    enum class State(val char: Char) {
        EMPTY('_'), CROSS('X'), ZERO('O')
    }
}