import java.awt.Color

class Rectangle(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0, 0)

  private val x1: Int = arguments(0)
  private val y1: Int = arguments(1)
  private val x2: Int = arguments(2)
  private val y2: Int = arguments(3)

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    g.setColor(color)

    val topLine = new Line(s"($x1 $y1) ($x2 $y1)")
    topLine.draw(context)
    val rightLine = new Line(s"($x2 $y1) ($x2 $y2)")
    rightLine.draw(context)
    val bottomLine = new Line(s"($x2 $y2) ($x1 $y2)")
    bottomLine.draw(context)
    val leftLine = new Line(s"($x1 $y2) ($x1 $y1)")
    leftLine.draw(context)
  }
}