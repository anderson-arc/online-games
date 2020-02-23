inline fun <T> ArrayList<T>.filledWith(element: () -> T): ArrayList<T> {
    for (i in this.indices) {
        this[i] = element()
    }
    return this
}

inline fun <T> ArrayList<T>.filledWith(count: Int, element: () -> T): ArrayList<T> {
    repeat (count) {
        this.add(element())
    }
    return this
}