import java.awt.geom.AffineTransform
import java.awt.{Color, Font, Shape}

class TextAt(args: String) extends DrawObject[Array[Any]] with Object with Colorable {

  override var arguments: Array[Any] = args match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val x0: Int = arguments(0).asInstanceOf[Int] * 10
  private val y0: Int = arguments(1).asInstanceOf[Int] * 10
  private val text: String = arguments(2).asInstanceOf[String]

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics

    val originalTransform: AffineTransform = g.getTransform

    // Revert the transformation
    //g.setTransform(new AffineTransform(1, 0, 0, 1, 0, 0))

    // Translate to the correct position
    //g.translate(x0, context.height - y0)
    g.setColor(color)
    g.setFont(new Font("TimesRoman", Font.BOLD, 12))

    // Translate to where you want the text anchor point to be
    g.translate(x0, y0)

    // Step 2: Flip horizontally using scale(-1, 1)// Step 2: Flip horizontally using scale(-1, 1)
    g.scale(-1, 1)

    // Step 3: Draw string at (0, 0) — but because of the horizontal flip,
    // we need to shift it back by its width to align correctly.
    val fm = g.getFontMetrics
    //val text = "Horizontally Flipped"
    val textWidth = fm.stringWidth(text)

    // Rotate 180 degrees (PI radians) around that point to flip the text
    g.rotate(Math.PI)
    print(textWidth.toString + " - ")
    // Draw the text With its correct anchor point
    g.drawString(text, -(textWidth/2), 0)

    // Restore original transform
    g.setTransform(originalTransform)

  }
}
