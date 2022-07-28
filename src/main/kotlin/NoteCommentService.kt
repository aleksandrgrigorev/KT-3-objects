import java.time.LocalDate

class NoteCommentService: CrudService<NoteComment>() {

    fun createComment(noteId: Int, ownerId: Int, replyTo: Int, message: String, guid: String, now: LocalDate): Int {
        val noteComment = NoteComment(0, noteId, ownerId, replyTo, message, guid, creationDate = now)
        return create(noteComment)
    }

    fun editComment(commentId: Int, ownerId: Int, message: String, now: LocalDate): Boolean {
        val noteComment = NoteComment(commentId, ownerId = ownerId, message = message, creationDate = now)
        val oldNoteComment = getById(commentId, ownerId) ?: return false
        return update(noteComment)
    }

    fun getComments(noteId: Int, ownerId: Int, count: Int?, sort: Int?): List<NoteComment> {
        var noteComments = readAll()
        noteComments = noteComments.filter { comment -> comment.noteId == noteId && comment.ownerId == ownerId &&
                !comment.deleted}
        if (sort == 1) {
            noteComments = noteComments.sortedByDescending { nc -> nc.creationDate }
        } else if (sort == 0) {
            noteComments = noteComments.sortedBy { nc -> nc.creationDate }
        }

        return if (count == null || noteComments.size <= count) {
            noteComments
        } else {
            noteComments.dropLast(noteComments.size - count)
        }
    }

    fun deleteComment(commentId: Int, ownerId: Int): Boolean {
        val noteComment = getById(commentId, ownerId) ?: return false
        noteComment.deleted = true
        return true
    }

    fun restoreComment(commentId: Int, ownerId: Int): Boolean {
        val noteComment = getByIdWithDeleted(commentId, ownerId) ?: return false
        if (!noteComment.deleted) return false
        noteComment.deleted = false
        return true
    }

    private fun getById(commentId: Int, ownerId: Int): NoteComment? {
        val noteComment = read(commentId)
        if (noteComment != null && !noteComment.deleted && noteComment.ownerId == ownerId) {
            return noteComment
        }
        return null
    }

    private fun getByIdWithDeleted(commentId: Int, ownerId: Int): NoteComment? {
        val noteComment = read(commentId)
        if (noteComment != null && noteComment.ownerId == ownerId) {
            return noteComment
        }
        return null
    }
}