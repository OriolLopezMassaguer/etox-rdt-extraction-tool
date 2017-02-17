package models.etox_reports

import slick.driver.PostgresDriver.simple._
import java.sql.ResultSet
import gudusoft.gsqlparser.TGSqlParser
import gudusoft.gsqlparser.pp.para.GFmtOptFactory
import gudusoft.gsqlparser.pp.para.GOutputFmt
import gudusoft.gsqlparser.pp.stmtformatter.FormatterFactory
import gudusoft.gsqlparser.EDbVendor
import java.io.PrintStream
import models.FileUtils
import scala.Range

object Querys_helper {
  var debug = true
  val excluded_fields = Set("img_base64")

  var tmpPathQuerys = FileUtils.tmpPath

  def exportQuery(q: String, filename: String) = {

    val fos = new PrintStream(filename)
    fos.println(Querys_helper.prettyprintSQL(q, true))
    fos.close()
  }
  def exportQuerys(lqs: List[String], prefix: String = ""): Unit = {

    println("Exporting: " + lqs.size)
    println(tmpPathQuerys)
    lqs.zip(Range(0, lqs.size)).map(
      {
        case (q, i) =>
          {

            val filename = FileUtils.getNewFilename(prefix + "Q_seq" + i, ".html", tmpPathQuerys)
            println("---------------------")
            println(filename)
            println("---------------------")
            val fos = new PrintStream(filename)
            fos.println(Querys_helper.prettyprintSQL(q, true))
            fos.close()
          }
      })
  }

  def doQuerySQL2Text[A, B](sqlConnection: java.sql.Connection, filename: String, q: scala.slick.lifted.Query[A, B, Seq]): Unit = {
    val fos = new PrintStream(filename)
    val qs = getSQL(q, false)
    doQuerySQL2Text(sqlConnection, qs, fos)
    fos.close
  }

  def doQuerySQL2Text(sqlConnection: java.sql.Connection, query: String, filename: String): Unit = {
    val fos = new PrintStream(filename)
    doQuerySQL2Text(sqlConnection, query, fos)
    fos.close
  }

  def doQuerySQL2Text(sqlConnection: java.sql.Connection, query: String, out: PrintStream): Unit = {
    var statement = sqlConnection.createStatement()
    statement.execute(query)
    var rsmetadata = statement.getResultSet().getMetaData()
    var rs = statement.getResultSet()
    for (column <- Range(1, rsmetadata.getColumnCount() + 1)) {
      val col = rsmetadata.getColumnLabel(column)
      if (!(excluded_fields contains col)) {
        out.print(rsmetadata.getColumnLabel(column))
        out.print("\t")
      }
    }
    out.println()
    while (rs.next()) {
      for (column <- Range(1, rsmetadata.getColumnCount() + 1)) {
        val col = rsmetadata.getColumnLabel(column)
        if (!(excluded_fields contains col)) {
          out.print(rs.getString(column))
          out.print("\t")
        }
      }
      out.println()
    }
    out.close()

  }

  def prettyprintSQL(q: String, html: Boolean = false): String = {
    println("Pretty print Q: ")
    println("Pretty print html: " + html)
    val sqlparser = new TGSqlParser(EDbVendor.dbvpostgresql)
    //println("Query to parse: "+q)
    sqlparser.sqltext = q
    sqlparser.parse()
    if (sqlparser.checkSyntax() == 0) {
      println("Num statements: " + sqlparser.getSqlstatements().size())
      val sq = sqlparser.getSqlstatements().get(0)
      val option = GFmtOptFactory.newInstance()
      if (html) option.outputFmt = GOutputFmt.ofhtml
      val result = FormatterFactory.pp(sqlparser, option)
      result
    } else {
      println("Num errors parsing SQL: " + sqlparser.getErrorCount())
      println("Error message: " + sqlparser.getErrormessage)
      q
    }

  }
  def prettyprintSQL(lq: List[String]): String = {
    lq.map(this.prettyprintSQL(_, false)).toString
  }
  def prettyprintSQL_Q[A, B](q: scala.slick.lifted.Query[A, B, Seq], html: Boolean = false): String = {
    prettyprintSQL(q.selectStatement, html)
  }

  private def formatField(rs: java.sql.ResultSet, column: Int) = {
    val columntype = rs.getMetaData().getColumnTypeName(column)
    //println(columntype)
    columntype match {
      //case "numeric" => "%.2f" format rs.getFloat(column)
      case "numeric" => "%.2f" formatLocal (java.util.Locale.US, rs.getFloat(column))
      case _         => rs.getString(column)
    }
  }

  def getIteratorFromResultSet(rs: java.sql.ResultSet) = {
    def getFromROW(rs: java.sql.ResultSet) = {
      var rsmetadata = rs.getMetaData()
      val r = for (column <- Range(1, rsmetadata.getColumnCount() + 1))
        yield (rsmetadata.getColumnName(column) -> formatField(rs, column))
      r
    }
    val strea = new Iterator[IndexedSeq[(String, String)]] {
      def hasNext = rs.next()
      def next() = getFromROW(rs)
    }
    strea
  }

  def getSQL[A, B](q: scala.slick.lifted.Query[A, B, Seq], distinct: Boolean = false) = {
    if (distinct)
      q.selectStatement.replace("select", "select distinct")
    else
      q.selectStatement

  }
  def getCountFromResultSet(rs: java.sql.ResultSet) = {
    rs.last()
    val size = rs.getRow()
    rs.beforeFirst()
    size
  }

  def getResultSet[A, B](sqlConnection: java.sql.Connection, q: scala.slick.lifted.Query[A, B, Seq]) = {
    val qstring = getSQL(q, false)
    if (debug) {
      this.exportQuerys(List(qstring))
    }
    var statement = sqlConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
    println("To execute")
    try { statement.execute(qstring) }
    catch {
      case e: Throwable => println(e)
    }
    println("To get resultset")
    statement.getResultSet()
  }
  def executeQuery(sqlConnection: java.sql.Connection, qstring: String) = {
    val stmt = sqlConnection.createStatement()
    //println("\n\ndoQuerySQLInsert: \n\n")
    //println(qstring)
    //if (debug) {
    //      Querys_helper.prettyprintSQL(qstring, false)
    //    this.exportQuerys(List(qstring))
    //println(qstring)
    //}
    stmt.executeUpdate(qstring)
  }

  def getFields()(rs: java.sql.ResultSet) = {
    var rsmetadata = rs.getMetaData()
    for (column <- Range(1, rsmetadata.getColumnCount() + 1))
      yield (rsmetadata.getColumnLabel(column))
  }
}
