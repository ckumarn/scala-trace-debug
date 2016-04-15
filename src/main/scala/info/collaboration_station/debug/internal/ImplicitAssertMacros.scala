package info.collaboration_station.debug.internal

import info.collaboration_station.debug.Compat
import scala.language.implicitConversions
import scala.language.experimental.macros

/**
  * Created by johnreed on 4/14/16.
  */
object ImplicitAssertMacros {
  def assert[MyType](c: Compat.Context)(assertion: c.Expr[(MyType) => Boolean], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me" // this "me" has to be the same "me" in ImplicitAssert.
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"$assertion($me)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertStdOut[MyType](c: Compat.Context)(assertion: c.Expr[(MyType) => Boolean], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"$assertion($me)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertEquals[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"($me).equals($other)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertEqualsStdOut[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"($me).equals($other)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNotEquals[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"! ($me).equals($other)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNotEqualsStdOut[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"! ($me).equals($other)"
    val isFatal = q"true"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatal[MyType](c: Compat.Context)(assertion: c.Expr[(MyType) => Boolean], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me" // this "me" has to be the same "me" in ImplicitAssert.
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"$assertion($me)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatalStdOut[MyType](c: Compat.Context)(assertion: c.Expr[(MyType) => Boolean], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me" // this "me" has to be the same "me" in ImplicitAssert.
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"$assertion($me)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatalEquals[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"($me).equals($other)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatalEqualsStdOut[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"($me).equals($other)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatalNotEquals[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"false"
    val assertionTrue = q"! ($me).equals($other)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
  def assertNonFatalNotEqualsStdOut[MyType, OtherType](c: Compat.Context)(other: c.Expr[OtherType], message: c.Expr[String]): c.Expr[MyType] = {
    import c.universe._
    val me = q"${c.prefix}.me"
    val numLines = q"Int.MaxValue"
    val useStdOut = q"true"
    val assertionTrue = q"! ($me).equals($other)"
    val isFatal = q"false"
    val toReturn =
      q"""
           _root_.info.collaboration_station.debug.internal.Printer.traceInternalAssert($message, $numLines, $useStdOut, $assertionTrue, $isFatal);
           $me;
          """
    c.Expr[MyType](toReturn)
  }
}
