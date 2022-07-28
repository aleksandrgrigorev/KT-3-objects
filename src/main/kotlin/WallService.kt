class PostNotFoundException(message: String) : RuntimeException(message)

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