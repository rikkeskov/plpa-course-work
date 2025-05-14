//> using leftAlignRectangleCoordinates
import java.awt.Shape

class Rectangle(text: String) extends DrawObject[Array[Int]] with Object with Colorable with Fillable {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private def leftAlignRectangleCoordinates(x1: Int, y1: Int, x2: Int, y2: Int): Array[Int] = {
    var lowX: Int = x1
    var lowY: Int = y1
    var highX: Int = x2
    var highY: Int = y2
    if(lowX > highX){
      lowX = lowX + highX
      highX = lowX - highX
      lowX = lowX - highX
    }
    if(lowY > highY){
      lowY = lowY + highY
      highY = lowY - highY
      lowY = lowY - highY
    }
    Array[Int](lowX, lowY, highX, highY)
  }

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    val coordinates = leftAlignRectangleCoordinates(arguments(0), arguments(1), arguments(2), arguments(3))
    val shape: Shape = new java.awt.Rectangle(coordinates(0) * 10, coordinates(1) * 10, (coordinates(2) - coordinates(0)) * 10, (coordinates(3) - coordinates(1)) * 10)

    g.setColor(fill)
    g.fill(shape)
    g.setColor(color)
    g.draw(shape)
  }
}