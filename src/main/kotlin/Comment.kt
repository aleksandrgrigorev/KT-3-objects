data class Comment (
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 1,
    val text: String = "Comment text",
    val replyToUser: Int = 1,
    val replyToComment: Int = 1,
    val attachments: Attachment = PhotoAttachment(),
    val thread: CommentThread = CommentThread()
)

data class CommentThread (
    val count: Int = 0,
    val canPost: Boolean = true,
    val showReplyButton : Boolean = true,
    val groupsCanPost: Boolean = true
)