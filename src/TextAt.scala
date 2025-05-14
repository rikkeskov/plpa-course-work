import java.awt.geom.AffineTransform
import java.awt.Font

class TextAt(args: String) extends DrawObject[Array[Any]] with Object with Colorable {

  override var arguments: Array[Any] = args match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  var fontType: Int = Font.BOLD

  private val x0: Int = arguments(0).asInstanceOf[Int] * 10
  private val y0: Int = arguments(1).asInstanceOf[Int] * 10
  private val text: String = arguments(2).asInstanceOf[String]

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics

    val originalTransform: AffineTransform = g.getTransform
    g.setColor(color)
    g.setFont(new Font("TimesRoman", fontType, 12))

    g.translate(x0, y0)
    g.scale(-1, 1)
    val fm = g.getFontMetrics
    val textWidth = fm.stringWidth(text)
    val textHeight = fm.getHeight
    g.rotate(Math.PI)
    g.drawString(text, -(textWidth/2), -(textHeight/2))
    g.setTransform(originalTransform)

  }
}
