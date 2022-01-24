fun Collection<Long>.multiply():Long {
    return this.fold(1L) { acc, l -> acc * l}
}