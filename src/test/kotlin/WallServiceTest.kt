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
        assertEquals(true, result)
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
        assertEquals(false, result)
    }
}