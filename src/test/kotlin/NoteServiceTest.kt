import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class NoteServiceTest {

    @Test
    fun add() {
        val noteService = NoteService(noteCommentService = NoteCommentService())

        val result = noteService.add("title", "text", 3, 3, "all",
            "all", 1, LocalDate.now())

        assertEquals(1, result)
    }

    @Test
    fun delete_true() {
        val noteCommentService = NoteCommentService()
        val noteService = NoteService(noteCommentService)
        val ownerId = 1
        val noteId = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, LocalDate.now())

        val result = noteService.delete(noteId)

        assertTrue(result)
    }

    @Test
    fun delete_withComments() {
        val noteCommentService = NoteCommentService()
        val noteService = NoteService(noteCommentService)
        val ownerId = 1
        val noteId = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, LocalDate.now())
        noteCommentService.createComment(noteId, ownerId, 2,"Comment text", "Unique ID")
        noteService.delete(noteId)

        val result = noteCommentService.getComments(noteId, ownerId, null, null)

        assertEquals(emptyList<NoteComment>(), result)
    }

    @Test
    fun delete_false() {
        val noteService = NoteService(noteCommentService = NoteCommentService())

        val result = noteService.delete(0)

        assertFalse(result)
    }

    @Test
    fun edit_true() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val ownerId = 1
        val noteId = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, LocalDate.now())

        val result = noteService.edit(noteId, "title", "text",3,3,"all",
            "all", ownerId)

        assertTrue(result)
    }

    @Test
    fun edit_false() {
        val noteService = NoteService(noteCommentService = NoteCommentService())

        val result = noteService.edit(0, "title", "text",3,3,"all",
            "all", 1)

        assertFalse(result)
    }

    @Test
    fun get_sort0() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val ownerId = 1
        val firstDate = LocalDate.of(2022, 7, 26)
        val secondDate = LocalDate.of(2022, 7, 27)
        val noteId1 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, firstDate)
        val noteId2 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, secondDate)
        val note1 = Note(noteId1, "title", "text", 3, 3, "all",
            "all", ownerId, firstDate)
        val note2 = Note(noteId2, "title", "text", 3, 3, "all",
            "all", ownerId, secondDate)

        val expected = mutableListOf(note2, note1)

        val result = noteService.get(mutableListOf(noteId1, noteId2), ownerId, 2, 0)

        assertEquals(expected, result)
    }

    @Test
    fun get_sort1() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val ownerId = 1
        val firstDate = LocalDate.of(2022, 7, 26)
        val secondDate = LocalDate.of(2022, 7, 27)
        val noteId1 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, firstDate)
        val noteId2 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, secondDate)
        val note1 = Note(noteId1, "title", "text", 3, 3, "all",
            "all", ownerId, firstDate)
        val note2 = Note(noteId2, "title", "text", 3, 3, "all",
            "all", ownerId, secondDate)

        val expected = mutableListOf(note1, note2)

        val result = noteService.get(mutableListOf(noteId1, noteId2), ownerId, 2, 1)

        assertEquals(expected, result)
    }

    @Test
    fun get_count() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val ownerId = 1
        val firstDate = LocalDate.of(2022, 7, 26)
        val secondDate = LocalDate.of(2022, 7, 27)
        val noteId1 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, firstDate)
        val noteId2 = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, secondDate)
        val note1 = Note(noteId1, "title", "text", 3, 3, "all",
            "all", ownerId, firstDate)

        val expected = mutableListOf(note1)

        val result = noteService.get(mutableListOf(noteId1, noteId2), ownerId, 1, 1)

        assertEquals(expected, result)
    }

    @Test
    fun getById_success() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val ownerId = 1
        val date = LocalDate.now()
        val noteId = noteService.add("title", "text", 3, 3, "all",
            "all", ownerId, date)
        val expected = Note(noteId, "title", "text", 3, 3, "all",
            "all", ownerId, date)

        val result = noteService.getById(noteId, ownerId)

        assertEquals(expected, result)
    }

    @Test
    fun getById_null() {
        val noteService = NoteService(noteCommentService = NoteCommentService())
        val date = LocalDate.now()
        val noteId = noteService.add("title", "text", 3, 3, "all",
            "all", 1, date)

        val result = noteService.getById(noteId, 0)

        assertEquals(null, result)
    }
}