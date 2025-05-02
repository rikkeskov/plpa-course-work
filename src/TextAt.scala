import java.awt.{Color, Shape, Font}
import javax.swing.plaf.basic.BasicTextUI.BasicCaret

class TextAt(args: String) extends DrawObject[Array[Any]] with Colorable {

  override var arguments: Array[Any] = args match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val x0: Int = arguments(0).asInstanceOf[Int] * 10
  private val y0: Int = arguments(1).asInstanceOf[Int] * 10
  private val text: String = arguments(2).asInstanceOf[String]

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics

    g.setColor(color)
    g.setFont(new Font("TimesRoman", Font.BOLD, 12))
    g.drawString(text, x0, y0)
  }
}
