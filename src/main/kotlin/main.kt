data class Post (
    var id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Long = System.currentTimeMillis() / 1000L,
    val text: String = "text",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Int = 0,
    val views: Int = 0,
    val original: Post? = null,
    val signerId: Int = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int = 0
)

data class Comments (
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class Likes (
    val count: Int = 0,
    val userLikes: Int = 0,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 0;

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
}