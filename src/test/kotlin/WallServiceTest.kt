import org.junit.Test

import org.junit.Assert.*
import java.util.stream.IntStream.range

class WallServiceTest {

    @Test
    fun add() {
        //arrange
        val post = Post(text = "TEST_MSG")

        //act
        val result = WallService.add(post)

        //assert
        assertNotEquals(0, result.id)
    }

    @Test
    fun update_true() {
        //arrange
        for (i in range(0, 5)) {
            WallService.add(Post(text = "TEST_MSG"))
        }
        val post = Post(id = 1, text = "TEST_MSG")

        //act
        val result = WallService.update(post)

        //assert
        assertTrue(result)
    }

    @Test
    fun update_false() {
        //arrange
        for (i in range(0, 5)) {
            WallService.add(Post(text = "TEST_MSG"))
        }
        val post = Post(text = "TEST_MSG")

        //act
        val result = WallService.update(post)

        //assert
        assertFalse(result)
    }

    @Test
    fun createComment_shouldNotThrow() {
        //arrange
        val postId = 1
        val comment = Comment()
        for (i in range(0, 5)) {
            WallService.add(Post(text = "TEST_MSG"))
        }

        //act
        val result = WallService.createComment(
            postId = postId,
            comment = comment
        )

        //assert
        assertEquals(comment, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_shouldThrow() {
        //arrange
        val postId = 0
        val comment = Comment()
        for (i in range(0, 5)) {
            WallService.add(Post(text = "TEST_MSG"))
        }

        //act
        WallService.createComment(
            postId = postId,
            comment = comment
        )
    }
}