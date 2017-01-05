import scala.collection.mutable
import scala.io.Source

/**
  * Created by Montana Ruth on 1/5/2017.
  */

object AddingWords extends App {
  val in = Source.stdin.getLines().takeWhile(_ != "").toList
  val r = """ ([-+]) ([A-Za-z]+)""".r
  val wordToNum = mutable.HashMap[String,Int]()
  val numToWord = mutable.HashMap[Int,String]()

  abstract class Tree
  case class Sum(l: Tree, r: Tree) extends Tree
  case class Difference(l: Tree, r: Tree) extends Tree
  case class Const(v: Int) extends Tree
  case class Var(v: String) extends Tree

  def eval(t: Tree, env: mutable.HashMap[String,Int]): Option[Int] = t match {
    case Sum(l,r) =>
      for {
        x <- eval(l, env)
        y <- eval(r, env)
      } yield (x + y)
    case Difference(l,r) => for {
      x <- eval(l, env)
      y <- eval(r, env)
    } yield (x - y)
    case Const(v) => Some(v)
    case Var(v: String) => env.get(v)
  }

  def getCalcOp(opStr: String): Tree = {
    val (start,rest) = opStr.span(_ != ' ')
    r.findAllMatchIn(rest).foldLeft(Var(start): Tree)((c,next) =>
      next.group(1) match {
        case "-" => Difference(c,Var(next.group(2)))
        case "+" => Sum(c,Var(next.group(2)))
      }
    )
  }

  def evalOp(s: String) = s.split(" ") match {
    case Array("def",v,x) =>
      wordToNum += v -> x.toInt
      numToWord += x.toInt -> v
    case Array("clear",_*) =>
      wordToNum.clear()
      numToWord.clear()
    case Array("calc",calcOp@_*) =>
      print(s"${calcOp.mkString(" ")} ${
        (for {
          n <- eval(getCalcOp(calcOp.init.mkString(" ")), wordToNum)
          w <- numToWord.get(n)
        } yield (w)) match {
          case Some(w) => w
          case None => "unknown"
        }
      }\n")
  }

  //in.foreach(s => evalOp(s))

  wordToNum ++= List("Testing" -> 5, "is" -> 2, "horrible" -> 3)
  numToWord ++= List(5 -> "Testing", 2 -> "is", 3 -> "horrible")
  evalOp("calc Testing - is =")
}