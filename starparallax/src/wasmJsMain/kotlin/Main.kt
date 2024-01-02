package starparallax

import com.jamesward.looperkt.*
import kotlin.random.Random

data class Star(var x: Int, val y: Int, val depth: Int)

typealias Stars = List<Star>
data class Field(val width: Int, val height: Int, val stars: Stars)

fun main() {
    val depths = 5
    val depthFloor = 55
    val depthInterval = 200 / depths
    var field: Field? = null

    Looper {
        if ((field == null) || (field?.width != pixels.width) || (field?.height != pixels.height)) {
            // init stars
            val numStars = pixels.width * pixels.height / 100
            val stars = List(numStars) {
                val randomX = Random.nextInt(pixels.width)
                val randomY = Random.nextInt(pixels.height)
                val depth = Random.nextInt(depths) + 1
                Star(randomX, randomY, depth)
            }
            field = Field(pixels.width, pixels.height, stars)

            // start dark
            for (x in 0..<pixels.width) {
                for (y in 0..<pixels.height) {
                    pixels[x, y] = RGBA(0u, 0u, 0u, 255u)
                }
            }
        } else {
            if (!pointer.down) {
                field?.stars?.forEach { star ->
                    // erase star
                    pixels[star.x, star.y] = RGBA(0u, 0u, 0u, 255u)

                    // move star
                    val newX = star.x + star.depth
                    star.x = if (newX >= pixels.width) {
                        newX - pixels.width
                    } else {
                        newX
                    }

                    val brightness = (depthFloor + (star.depth * depthInterval)).toUByte()
                    pixels[star.x, star.y] = RGBA(brightness, brightness, brightness, 255u)
                }
            }
        }
    }
}