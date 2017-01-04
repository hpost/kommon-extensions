package cc.femto.kommon.extensions

/**
 * Group a list into equal chunks of size [groupSize].
 * The last group will contain {@code size % groupSize} elements.
 *
 * @param groupSize Size of each group.
 * @return A list containing the source split up into groups of size [groupSize].
 */
fun <T> List<T>.grouped(groupSize: Int): List<List<T>> {
    var remaining = this
    val batches = mutableListOf<List<T>>()
    while (remaining.isNotEmpty()) {
        val n = Math.min(groupSize, remaining.size)
        batches += remaining.take(n)
        remaining = remaining.drop(n)
    }
    return batches
}
