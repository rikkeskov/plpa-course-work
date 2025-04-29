import java.awt.{Graphics2D, Shape}

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
    errors = Array();
    objects = Array();
    latestBoundingBox = None
  }
}
