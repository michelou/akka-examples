package zzz.akka.investigation

import org.junit.Test
import org.junit.Assert.assertTrue

class MainTest extends MainTestJava {
  import MainTestJava._

  @Test
  def test1(): Unit = {
    actor ! "Hello"
    actor ! 42

    Thread.sleep(2000)
    assertTrue("Done", true)
  }

  @Test
  def test2(): Unit = {
    println("test2")
  }

}
