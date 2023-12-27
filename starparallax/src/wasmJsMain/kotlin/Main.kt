package starparallax

import com.jamesward.looperkt.*
import kotlin.random.Random

data class Star(var x: Int, val y: Int, val depth: Int)

typealias Stars = List<Star>
data class Field(val width: Int, val height: Int, val stars: Stars)

fun main() {
    var field: Field? = null

    Looper {
        if ((field == null) || (field?.width != pixels.width) || (field?.height != pixels.height)) {
            // init stars
            val stars = List(10000) {
                val randomX = Random.nextInt(pixels.width)
                val randomY = Random.nextInt(pixels.height)
                val depth = Random.nextInt(5) + 1
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
            field?.stars?.forEach { star ->
                // erase star
                pixels[star.x, star.y] = RGBA(0u, 0u, 0u, 255u)

                // move star
                val newX = star.x + star.depth
                star.x = if (newX >= pixels.width) {
                    star.depth
                } else {
                    newX
                }

                val brightness = (55 + (star.depth * 40)).toUByte()

                //println("x = ${star.x} y = ${star.y} depth = ${star.depth} brightness = $brightness")
                pixels[star.x, star.y] = RGBA(brightness, brightness, brightness, 255u)
            }
        }
    }
}