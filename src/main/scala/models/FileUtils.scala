package models

import java.util.GregorianCalendar
import java.text.SimpleDateFormat
import java.util.Properties
import java.io.FileInputStream
import scala.io._

object FileUtils {

  var tmpPath = models.Config.home_path + "/public/temp"

  def getFile(fileName: String) = scala.io.Source.fromFile(fileName).getLines().toSet

  def readPropertiesFile(file: String) = {
    var defaultProps = new Properties()
    var in = new FileInputStream(file)
    defaultProps.load(in)
    in.close()
    defaultProps
  }

  def getNewFilename(prefix: String, suffix: String, path: String) = {
    val calendar = new GregorianCalendar()
    val sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS")
    val datetime = sdf.format(calendar.getTime())
    path + "/" + prefix + "_" + datetime.toString() + suffix
  }

  def getFileToString(filename: String): String = {
    try {

      val lines = scala.io.Source.fromFile(filename).mkString
      lines
    } catch {
      case e: Throwable => {
        println(e)
        ""
      }
    }
  }


}
