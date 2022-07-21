data class Post (
    var id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Long = System.currentTimeMillis() / 1000L,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Int = 0,
    val views: Int = 0,
    val postType: String = "post",
    val signerId: Int = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int = 0,
    val attachments: Array<Attachment> = emptyArray()
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