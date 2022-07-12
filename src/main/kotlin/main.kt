import java.util.*

data class Post (
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Objects,
    val copyright: Objects,
    val likes: Objects,
    val reposts: Objects,
    val views: Objects,
    val postType: String,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Objects,
    val postponedId: Int
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 0;

    fun add(post: Post): Post {
        post.id = nextId++
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