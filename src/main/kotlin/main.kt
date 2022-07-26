class PostNotFoundException(message: String) : RuntimeException(message)

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

object WallService {
    private var nextId = 0;
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        post.id = ++nextId
        posts += post
        return post
    }

    fun update(post: Post): Boolean {
        for ((index, p) in posts.withIndex()) {
            if (p.id == post.id) {
                posts[index] = post.copy(ownerId = p.ownerId, date = p.date)
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index, p) in posts.withIndex()) {
            if (p.id == postId) {
                comments += comment.copy(text = "New comment $index")
                return comment
            }
        }
        throw PostNotFoundException("No post with $postId")
    }
}