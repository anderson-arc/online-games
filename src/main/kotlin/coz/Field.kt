package coz

import filledWith

class Field(private val winCount: Int = 5) {
    private val state = ArrayList<ArrayList<Cell>>(initWidth)
    private var width = initWidth
    private var height = initHeight

    init {
        repeat(initWidth) {
            state.add(ArrayList<Cell>(initHeight).filledWith(initHeight) { Cell() })
        }
    }

    fun put(x: Int, y: Int, newState: Cell.State): Boolean {
        if (x < 0 || x >= width ||
            y < 0 || y >= height ||
            state[x][y].state != Cell.State.EMPTY ||
            newState == Cell.State.EMPTY) {
            return false
        }
        state[x][y].state = newState
        expand(x, y)
        println(checkWin())
        return true
    }

    private fun checkWin(): Cell.State {
        for ((x, vertical) in state.withIndex()) {
            for ((y, cell) in vertical.withIndex()) {
                if (cell.state == Cell.State.EMPTY) {
                    continue
                }

                var count = 0
                if (x + winCount < width) {
                    repeat(winCount) {
                        if (state[x + it][y].state == cell.state) {
                            count++
                        }
                    }
                }
                if (count == winCount) {
                    return cell.state
                }

                count = 0
                if (y + winCount < height) {
                    repeat(winCount) {
                        if (state[x][y + it].state == cell.state) {
                            count++
                        }
                    }
                }
                if (count == winCount) {
                    return cell.state
                }

                count = 0
                if (y + winCount < height && x + winCount < width) {
                    repeat(winCount) {
                        if (state[x + it][y + it].state == cell.state) {
                            count++
                        }
                    }
                }
                if (count == winCount) {
                    return cell.state
                }
            }
        }
        return Cell.State.EMPTY
    }

    private fun expand(x: Int, y: Int) {
        when (y) {
            0 -> {
                when (x) {
                    0 -> {
                        state.add(0, ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                        height++
                        state.forEach { it.add(0, Cell()) }
                    }
                    in 1 until (width - 1) -> {
                        height++
                        state.forEach { it.add(0, Cell()) }
                    }
                    (width - 1) -> {
                        state.add(ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                        height++
                        state.forEach { it.add(0, Cell()) }
                    }
                }
            }
            in 1 until (height - 1) -> {
                when (x) {
                    0 -> {
                        state.add(0, ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                    }
                    (width - 1) -> {
                        state.add(ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                    }
                }
            }
            (height - 1) -> {
                when (x) {
                    0 -> {
                        state.add(0, ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                        height++
                        state.forEach { it.add(Cell()) }
                    }
                    in 1 until (width - 1) -> {
                        height++
                        state.forEach { it.add(Cell()) }
                    }
                    (width - 1) -> {
                        state.add(ArrayList<Cell>(height).filledWith(height) { Cell() })
                        width++
                        height++
                        state.forEach { it.add(Cell()) }
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return buildString {
            repeat(height) { y ->
                repeat(width) { x ->
                    append("${state[x][y].state.char} ")
                }
                append('\n')
            }
        }
    }

    companion object {
        const val initWidth = 3
        const val initHeight = 3
    }
}