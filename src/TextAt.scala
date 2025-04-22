import java.awt.{Color, Shape}
import javax.swing.plaf.basic.BasicTextUI.BasicCaret

class TextAt(args: String) extends DrawObject[Array[Any]] {

  override var arguments: Array[Any] = args match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }
  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0, 0)
  override val shape: Shape = new BasicCaret

  private val Location: (Int, Int) = (arguments(0).toString.toInt, arguments(1).toString.toInt)
  private val Text: String = arguments(2).toString

  override def draw(context: DrawContext): Unit = {
    context.graphics.drawString(Text, Location._1, Location._2)
  }
}
