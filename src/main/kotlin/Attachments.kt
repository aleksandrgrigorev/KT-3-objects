abstract class Attachment {
    abstract val type: String
}

class Photo (
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text: String = "text",
    val date: Int = 0,
    val sizes: Array<Sizes> = emptyArray(),
    val width: Int = 0,
    val height: Int = 0
)

class Sizes (
    val type: String = "text",
    val url: String = "https://netology.ru/",
    val width: Int = 800,
    val height: Int = 600
)

class PhotoAttachment (
    val photo: Photo = Photo()
) : Attachment() {
    override val type = "photo"
}

class Video (
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "video",
    val description: String = "descriptionText",
    val duration: Int = 1,
    val image: Array<ImageVideo> = emptyArray(),
    val firstFrame: Array<FirstFrame> = emptyArray(),
    val date: Int = 1,
    val addingDate: Int = 1,
    val views: Int = 0,
    val localViews: Int = 0,
    val comments: Int = 0,
    val player: String = "VideoPlayer",
    val platform: String = "YouTube",
    val canAdd: Boolean = true,
    val isPrivate: Boolean = false,
    val processing: Int = 0,
    val isFavorite: Boolean = true,
    val canComment: Boolean = true,
    val canEdit: Boolean = true,
    val canLike: Boolean = true,
    val canRepost: Boolean = true,
    val canSubscribe: Boolean = true,
    val canAddToFaves: Boolean = true,
    val canAttachLink: Boolean = true,
    val width: Int = 800,
    val height: Int = 600,
    val userId: Int = 0,
    val converting: Boolean = false,
    val added: Boolean = false,
    val isSubscribed: Boolean = false,
    val repeat: Boolean = false,
    val videoType: String = "video",
    val live: Boolean = false,
    val likes: Int = 0
)

class ImageVideo (
    val height: Int = 600,
    val url: String = "https://youtube.com",
    val width: Int = 800,
    val withPadding: Int = 0
)

class FirstFrame (
    val height: Int = 600,
    val url: String = "https://img.com/img.jpg",
    val width: Int = 800
)

class VideoAttachment (
    val video: Video = Video(),
) : Attachment() {
    override val type = "video"
}

class Document (
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "document",
    val size: Int = 1,
    val ext: String = ".txt",
    val url: String = "https://vk.com/asaefaf.txt",
    val date: Int = 1,
    val docType: Int = 1
)

class DocumentAttachment (
    val document: Document = Document(),
) : Attachment() {
    override val type = "document"
}

class Link (
    val url: String = "URL ссылки",
    val title: String = "Заголовок",
    val caption: String = "Подпись ссылки (если имеется)",
    val description: String = "Описание ссылки",
    val photo: Photo,
    val productPrice: Int,
    val button: Button = Button(),
    val previewPage: String = "owner_id_page_id",
    val previewUrl: String = "https://google.com/"
)

data class Button (
    val title: String = "название кнопки",
    val url: String = "URL, на который ведет кнопка",
)

class LinkAttachment (
    val link: Link,
) : Attachment() {
    override val type = "link"
}

class Album (
    val id: Int = 0,
    val thumb: Photo,
    val ownerId: Int = 0,
    val title: String = "Название альбома",
    val description: String = "Описание альбома",
    val created: Int = 1,
    val updated: Int = 1,
    val size: Int = 0
)

class AlbumAttachment (
    val album: Album,
) : Attachment() {
    override val type = "album"
}