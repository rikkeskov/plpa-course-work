import java.awt.Color

import scala.util.control.Breaks._

class Line(text: String) extends DrawObject[Array[Int]] with Object with Colorable {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val x1: Int = arguments(0) * 10
  private val y1: Int = arguments(1) * 10
  private val x2: Int = arguments(2) * 10
  private val y2: Int = arguments(3) * 10

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    g.setColor(color)

    // Variables / values for the Bresenham's Line Algorithm
    var x = x1
    var y = y1
    val dx = Math.abs(x2 - x1)
    val sx = if (x1 < x2) 1 else -1
    val dy = -Math.abs(y2 -y1)
    val sy = if (y1 < y2) 1 else -1
    var error = dx + dy

    // Bresenham's Line Algorithm
    breakable {
      while (true) {
        g.drawLine(x, y, x, y)
        val error2 = 2 * error
        if (error2 >= dy) {
          if (x == x2) {
            break()
          }
          error = error + dy
          x = x + sx
        }
        if (error2 <= dx ) {
          if (y == y2) {
            break()
          }
          error = error + dx
          y = y + sy
        }
      }
    }
  }
}
