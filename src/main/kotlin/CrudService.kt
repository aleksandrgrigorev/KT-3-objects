abstract class CrudService<T : Identifiable> {

    private var elems = mutableListOf<T>()
    private var nextId = 0;

    fun create(elem: T): Int {
        elem.id = ++nextId
        elems += elem
        return elem.id
    }

    fun read(id: Int): T? {
        for (elem in elems) {
            if (id == elem.id) {
                return elem
            }
        }
        return null
    }

    fun read(ids: List<Int>): List<T> {
        val result = mutableListOf<T>()
        for (elem in elems) {
            if (ids.contains(elem.id)) {
                result += elem
            }
        }
        return result
    }

    fun readAll(): List<T> {
        return elems
    }

    fun update(elem: T): Boolean {
        for ((index, entry) in elems.withIndex()) {
            if (entry.id == elem.id) {
                elems[index] = elem
                return true
            }
        }
        return false
    }

    open fun delete(id: Int): Boolean {
        for ((index, elem) in elems.withIndex()) {
            if (id == elem.id) {
                elems.removeAt(index)
                return true
            }
        }
        return false
    }
}