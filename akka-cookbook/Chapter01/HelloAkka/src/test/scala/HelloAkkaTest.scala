package com.packt.chapter01

import org.junit.Test
import org.junit.Assert.assertEquals

class HelloAkkaTest extends HelloAkkaTestJava {
  import HelloAkkaTestJava._

  @Test
  def test1(): Unit = {
    assertEquals("Actor system", actorSystem.name, "[]HelloAkka")
    assertEquals("Actor system", actorSystem.toString, "akka://HelloAkka")
  }
/*
  @Test
  def test2(): Unit = {
    println("test2")
  }
*/
}
