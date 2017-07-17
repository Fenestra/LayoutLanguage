package com.westat

import com.westat.Length
import scala.collection.mutable.ListBuffer

/**
  * Created by lee on 6/29/17.
  */


case class Page(name : String, width : Length, height : Length, color : String) {
  private val list = new ListBuffer[Group]
  def addGroup(value : Group) : Group = {
    list += value
    value
  }
  def displayChildren = {
    println(this)
    list.foreach(g => g.displayChildren)
  }
  def toSVG : String = {
    val sb = new StringBuilder(
      s"""<svg id="$name" width="${width.asInchesString}" height="${height.asInchesString}"
      zoomAndPan="magnify" preserveAspectRatio="xMidYMid meet"
      viewbox="0 0 ${width.asInchesString} ${height.asInchesString}"
      xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">\n""")
    sb.append(s"""<rect x="0" y="0" width="${width.asInchesString}" height="${height.asInchesString}" fill="$color"/>\n """)
    list.foreach(g => sb.append(g.toSVG))
    sb.append("</svg>")
    sb.toString()
  }
}
