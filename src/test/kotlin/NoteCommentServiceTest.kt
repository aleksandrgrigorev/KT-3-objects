import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class NoteCommentServiceTest {

    @Test
    fun createComment() {
        val noteCommentService = NoteCommentService()
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "test msg"
        val guid = "guid"
        val date = LocalDate.now()

        val result = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, date)

        assertEquals(1, result)
    }

    @Test
    fun editComment_true() {
        val noteCommentService = NoteCommentService()
        val commentId = 1
        val ownerId = 1
        val date = LocalDate.now()
        val oldNoteComment = noteCommentService.createComment(1, ownerId,0,"msg", "guid", date)
        val message = "edit msg"

        val result = noteCommentService.editComment(commentId, ownerId, message, date)

        assertTrue(result)
    }

    @Test
    fun editComment_nothingToEdit() {
        val noteCommentService = NoteCommentService()
        val commentId = 1
        val ownerId = 1
        val message = "edit msg"
        val date = LocalDate.now()

        val result = noteCommentService.editComment(commentId, ownerId, message, date)

        assertFalse(result)
    }

    @Test
    fun getComments_sort0_dropLast() {
        val noteCommentService = NoteCommentService()
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "msg"
        val count = 2
        val sort = 0
        val guid = "guid"
        val firstDate = LocalDate.of(2022, 7, 25)
        val secondDate = LocalDate.of(2022, 7, 26)
        val thirdDate = LocalDate.of(2022, 7, 27)
        val noteCommentId1 = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, firstDate)
        val noteCommentId2 = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, secondDate)
        val noteCommentId3 = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, thirdDate)
        val noteComment1 = NoteComment(noteCommentId1, noteId, ownerId, replyTo, message, guid, false, firstDate)
        val noteComment2 = NoteComment(noteCommentId2, noteId, ownerId, replyTo, message, guid, false, secondDate)
        val noteComment3 = NoteComment(noteCommentId3, noteId, ownerId, replyTo, message, guid, false, thirdDate)
        val expected = mutableListOf(noteComment1, noteComment2)

        val result = noteCommentService.getComments(noteId, ownerId, count, sort)

        assertEquals(expected, result)
    }

    @Test
    fun getComments_sort1_noteCommentsFiltered() {
        val noteCommentService = NoteCommentService()
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "msg"
        val count = 3
        val sort = 1
        val guid = "guid"
        val firstDate = LocalDate.of(2022, 7, 25)
        val secondDate = LocalDate.of(2022, 7, 26)
        val thirdDate = LocalDate.of(2022, 7, 27)
        val noteCommentId1 = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, firstDate)
        val noteCommentId2 = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, secondDate)
        val noteCommentId3 = noteCommentService.createComment(2, 2, replyTo, message, guid, thirdDate)
        val noteComment1 = NoteComment(noteCommentId1, noteId, ownerId, replyTo, message, guid, false, firstDate)
        val noteComment2 = NoteComment(noteCommentId2, noteId, ownerId, replyTo, message, guid, false, secondDate)
        val noteComment3 = NoteComment(noteCommentId3, noteId, ownerId, replyTo, message, guid, false, thirdDate)
        val expected = mutableListOf(noteComment2, noteComment1)

        val result = noteCommentService.getComments(noteId, ownerId, count, sort)

        assertEquals(expected, result)
    }

    @Test
    fun deleteComment_true() {
        val noteCommentService = NoteCommentService()
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "msg"
        val guid = "guid"
        val date = LocalDate.of(2022, 7, 25)
        val commentId = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, date)

        val result = noteCommentService.deleteComment(commentId, ownerId)

        assertTrue(result)
    }

    @Test
    fun deleteComment_alreadyDeletedComment() {
        val noteCommentService = NoteCommentService()
        val commentId = 1
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "msg"
        val guid = "guid"
        val date = LocalDate.of(2022, 7, 28)
        val noteComment = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, date)
        noteCommentService.deleteComment(commentId, ownerId)

        val result = noteCommentService.deleteComment(commentId, ownerId)

        assertFalse(result)
    }

    @Test
    fun restoreComment_true() {
        val noteCommentService = NoteCommentService()
        val commentId = 1
        val noteId = 1
        val ownerId = 1
        val replyTo = 1
        val message = "msg"
        val guid = "guid"
        val date = LocalDate.of(2022, 7, 28)
        val noteComment = noteCommentService.createComment(noteId, ownerId, replyTo, message, guid, date)
        noteCommentService.deleteComment(commentId, ownerId)

        val result = noteCommentService.restoreComment(commentId, ownerId)

        assertTrue(result)
    }

    @Test
    fun restoreComment_false() {
        val noteCommentService = NoteCommentService()
        val commentId = 1
        val ownerId = 1
        val noteComment = null

        val result = noteCommentService.restoreComment(commentId, ownerId)

        assertFalse(result)
    }
}