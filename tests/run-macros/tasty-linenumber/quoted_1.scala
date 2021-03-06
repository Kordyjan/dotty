import scala.quoted._

class LineNumber(val value: Int) {
  override def toString: String = value.toString
}

object LineNumber {

  implicit inline def line[T >: Unit <: Unit]: LineNumber =
    ${lineImpl('[T])}

  def lineImpl(x: Type[Unit])(using QuoteContext) : Expr[LineNumber] = {
    import qctx.reflect._
    '{new LineNumber(${Expr(rootPosition.startLine)})}
  }

}
