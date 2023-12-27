import kotlin.random.Random
import kotlin.random.nextUInt
import com.jamesward.looperkt.*

fun main() {
    Looper {
        repeat(50) {
            val randomX = Random.nextInt(pixels.width)
            val randomY = Random.nextInt(pixels.height)
            val thePixel: RGBA? = pixels[randomX, randomY]

            if (thePixel.isVisible) {
                // to make it fun, if this pixel is visible already, hide it
                pixels[randomX, randomY] = thePixel.hide()
            }
            else {
                val randomRGBA = RGBA(Random.nextUInt() or 0xFFu) // full alpha
                pixels[randomX, randomY] = randomRGBA
            }

        }

        pixels[pointer.x, pointer.y] = if (pointer.down) RGBA(255u, 0u, 0u, 255u) else RGBA(0u, 0u, 255u, 255u)
    }
}
