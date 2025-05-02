import java.awt.Color
import java.awt.geom.{AffineTransform, Path2D, PathIterator, Rectangle2D}

class Circle(text: String) extends DrawObject[Array[Int]] with Colorable with Fillable {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val x0: Int = arguments(0) * 10
  private val y0: Int = arguments(1) * 10
  private val radius: Int = arguments(2) * 10

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics

    val path = new Path2D.Float()
    // Variables for the Midpoint Circle Algorithm
    var f: Int = 1 - radius
    var ddF_x: Int = 1
    var ddF_y: Int = - 2 * radius
    var x = 0
    var y = radius

    var octants = Array.ofDim[(Int, Int)](8, 0)
    // Set the starting point
    path.moveTo(x0.toFloat, (y0 - radius).toFloat)

    // Midpoint Circle Algorithm
    while (x < y) {
      if (f >= 0) {
        y -= 1
        ddF_y += 2
        f += ddF_y
      }
      x += 1
      ddF_x += 2
      f += ddF_x

      octants.update(0, octants(0) :+ (x0 + x, y0 - y)) // octant 1
      octants.update(1, octants(1) :+ (x0 + y, y0 - x)) // octant 2
      octants.update(2, octants(2) :+ (x0 + y, y0 + x)) // octant 3
      octants.update(3, octants(3) :+ (x0 + x, y0 + y)) // octant 4
      octants.update(4, octants(4) :+ (x0 - x, y0 + y)) // octant 5
      octants.update(5, octants(5) :+ (x0 - y, y0 + x)) // octant 6
      octants.update(6, octants(6) :+ (x0 - y, y0 - x)) // octant 7
      octants.update(7, octants(7) :+ (x0 - x, y0 - y)) // octant 8
    }
    octants = octants.zipWithIndex.map(oct => if (oct._2 % 2 == 1) oct._1.reverse else oct._1)
    octants.flatten.foreach(point => path.lineTo(point._1.toFloat, point._2.toFloat))

    path.closePath()
    g.setColor(fill)
    g.fill(path)

    g.setColor(color)
    g.draw(path)
  }
}
