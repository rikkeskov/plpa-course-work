import java.awt.{Color, Graphics2D, Shape}

class DrawContext(val graphics: Graphics2D) {
  var objects: Array[DrawObject[_]] = Array()
  var latestBoundingBox: Option[Shape] = None
  private var errors: Array[(Int, String)] = Array()

  def addError(error: String, lineNumber: Int, obj: DrawObject[_]): Unit = {
    errors = errors :+ (lineNumber, error)
  }

  def formatErrors(): String = {
    errors.foldLeft("")((acc, err) => acc ++ s"${err._1}: ${err._2}\n")
  }

  def resetContext(): Unit = {
    errors = Array()
    objects = Array()
    latestBoundingBox = None
  }

  def resetGraphics(): Unit = {
    graphics.setColor(Color.WHITE)
    graphics.setBackground(Color.WHITE)
    graphics.fillRect(-1000, -1000, 2000, 2000)
    graphics.setColor(new Color(0, 0, 0))
    graphics.setBackground(new Color(0, 0, 0, 0))
  }
}
