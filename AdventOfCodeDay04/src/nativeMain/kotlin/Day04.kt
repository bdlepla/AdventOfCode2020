class Day04(lines:List<String>) {
    private val documents = createDocuments(lines)
    init{
        println("${documents.count()} documents")
    }

    fun solvePart1() = documents.count{it.isSimplyValid()}
    fun solvePart2() = documents.count{it.isValid()}

    private fun createDocuments(lines:List<String>):List<Document> {
        val currentLines = mutableListOf<String>()
        val documents = mutableListOf<Document>()
        lines.forEach{
            if (it.isBlank()) {
                if (currentLines.any()) {
                    val oneLine = currentLines.joinToString(" ")
                    documents.add(Document(oneLine))
                    currentLines.clear()
                }
            }
            else {
                currentLines.add(it)
            }
        }
        if (currentLines.any()){
            val oneLine = currentLines.joinToString(" ")
            documents.add(Document(oneLine))
        }
        return documents
    }

}