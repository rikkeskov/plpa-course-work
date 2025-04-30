import java.awt.Color

class Circle(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  override val fill: Color = new Color(0, 0, 0, 0)

  private val x0: Int = arguments(0) * 10
  private val y0: Int = arguments(1) * 10
  private val radius: Int = arguments(2) * 10

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    g.setColor(color)

    // Variables for the Midpoint Circle Algorithm
    var f: Int = 1 - radius
    var ddF_x: Int = 1
    var ddF_y: Int = - 2 * radius
    var x = 0
    var y = radius

    // Set the four starting points
    g.drawLine(x0, y0 + radius, x0, y0 + radius)
    g.drawLine(x0, y0 - radius, x0, y0 - radius)
    g.drawLine(x0 + radius, y0, x0 + radius, y0)
    g.drawLine(x0 - radius, y0, x0 - radius, y0)

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

      g.drawLine(x0 + x, y0 + y, x0 + x, y0 + y)
      g.drawLine(x0 - x, y0 + y, x0 - x, y0 + y)
      g.drawLine(x0 + x, y0 - y, x0 + x, y0 - y)
      g.drawLine(x0 - x, y0 - y, x0 - x, y0 - y)
      g.drawLine(x0 + y, y0 + x, x0 + y, y0 + x)
      g.drawLine(x0 - y, y0 + x, x0 - y, y0 + x)
      g.drawLine(x0 + y, y0 - x, x0 + y, y0 - x)
      g.drawLine(x0 - y, y0 - x, x0 - y, y0 - x)
    }
  }
}
