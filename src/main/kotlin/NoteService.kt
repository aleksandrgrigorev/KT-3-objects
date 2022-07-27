import java.time.LocalDate

class NoteService(
    private val noteCommentService: NoteCommentService
): CrudService<Note>() {

    fun add(
        title: String, text: String, privacy: Int, commentPrivacy: Int,
        privacyView: String, privacyComment: String, ownerId: Int, now: LocalDate
    ): Int {
        val note = Note(0, title, text, privacy, commentPrivacy,
            privacyView, privacyComment, ownerId, now)
        return create(note)
    }

    override fun delete(noteId: Int): Boolean {
        val note = read(noteId) ?: return false
        val ownerId = note.ownerId
        if (super.delete(noteId)) {
            val noteComments = noteCommentService.getComments(noteId, ownerId, count = null, sort = null)
            for (noteComment in noteComments) {
                noteComment.deleted = true
            }
            return true
        }
        return false
    }

    fun edit(noteId: Int, title: String, text: String, privacy: Int, commentPrivacy: Int,
             privacyView: String, privacyComment: String, ownerId: Int): Boolean {
        val note = Note(noteId, title, text, privacy, commentPrivacy, privacyView, privacyComment, ownerId, LocalDate.now())
        return update(note)
    }

    fun get(ids: MutableList<Int>, userId: Int, count: Int, sort: Int): List<Note> {
        var notes = read(ids)
        notes = notes.filter { n -> n.ownerId == userId }
        if (sort == 1) {
            notes = notes.sortedBy { n -> n.creationDate }
        } else if (sort == 0) {
            notes = notes.sortedByDescending { n -> n.creationDate }
        }

        return if (notes.size <= count) {
            notes
        } else {
            notes.dropLast(notes.size - count)
        }
    }

    fun getById(noteId: Int, ownerId: Int): Note? {
        val note = read(noteId)
        if (note?.ownerId == ownerId) {
            return note
        }
        return null
    }
}