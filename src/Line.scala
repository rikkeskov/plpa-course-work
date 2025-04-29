import java.awt.Color

import scala.util.control.Breaks._

class Line(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0, 0)

  private val x1: Int = arguments(0) * 10
  private val y1: Int = arguments(1) * 10
  private val x2: Int = arguments(0) * 10
  private val y2: Int = arguments(1) * 10

  override def draw(context: DrawContext): Unit = {
    // draw line from x1, y1 to x2, y2
    val g = context.graphics
    g.setColor(color)

    var x = x1
    var y = -y1
    val xEnd = x2
    val yEnd = -y2

    val dx = Math.abs(x2 - x1)
    val sx = if (x1 < x2) 1 else -1

    val dy = Math.abs(y2 -y1)
    val sy = if (y1 < y2) -1 else 1
    var error = (if (dx > dy) dx else -dy) / 2

    breakable {
      while (true) {
        g.drawLine(x, y, x, y)
        System.out.println("loop")
        if (y == yEnd && x == xEnd ) {
          break()
        }
        if (error > -dx) {
          error = error - dy
          x = x + sx
        }
        if (error < dy ) {
          error = error + dx
          y = y + sy
        }
      }
    }

    // this fails the program if drawn before bounding box, which is currently the only way to view the circle
    //context.latestBoundingBox match {
    //  case Some(bb) => context.graphics.setClip(bb)
    //  case None =>  throw DrawException("Missing Bounding-box", this)
    //}
  }
}
