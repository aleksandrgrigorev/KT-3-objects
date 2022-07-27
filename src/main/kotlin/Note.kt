import java.time.LocalDate

abstract class Identifiable (open var id: Int = 0)

data class Note (
    override var id: Int = 0,
    val title: String = "Note title",
    val text: String = "Note text",
    val privacy: Int = 3,
    val commentPrivacy: Int = 3,
    val privacyView: String = "all",
    val privacyComment: String = "all",
    val ownerId: Int = 1,
    val creationDate: LocalDate
) : Identifiable(id)

data class NoteComment (
    override var id: Int = 0,
    val noteId: Int = 1,
    val ownerId: Int = 1,
    val replyTo: Int = 2,
    val message: String = "Comment text",
    val guid: String = "Unique ID",
    var deleted: Boolean = false,
    val creationDate: LocalDate
) : Identifiable(id)