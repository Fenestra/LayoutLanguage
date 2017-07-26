package com.westat

import com.westat.gids.GidsFont
import com.westat.sfo._
import com.westat.Length
import scala.collection.mutable.StringBuilder


/**
  * Created by lee on 6/30/17.
  */
trait GraphicItem {
  protected val defaultWidth = Length.dimension("1in")
  protected val defaultHeight = Length.dimension("1in")
  protected val defaultSpace = Length.dimension("0fu")
  def toSvg(loc : Location) : String = toString+"\n"
}

case class GraphicImage(data : String) extends GraphicItem

case class GraphicShape(graphicType : String) extends GraphicItem {
  override def toSvg(loc : Location) : String = {
    val sb = new StringBuilder(s"   <text>image:$graphicType</text>\n")
    sb.toString()
  }
}

case class GraphicText(font : GidsFont, color : String, textAlign : TextAlignments.Value) extends GraphicItem {
  private val blocktext = BlockText(font, textAlign)
  override def toSvg(loc : Location) : String = {
    blocktext.toSVG(loc, false)
  }

  def addText(text : String) : GraphicText = {
    blocktext.addText(InlineText(text, font))
    this
  }
}

trait GraphicWithCaption extends GraphicItem {
  def graphicSVG(loc : Location) : String
  def captionText : String
  override def toSvg(loc : Location) : String = {
    val sb = new StringBuilder(graphicSVG(loc))
// println(sb.toString())
    val cent = loc.left + (defaultWidth / 2)
    sb.append(s"""   <text x="${cent.asInchesString}" y="${(loc.top + defaultHeight + Length.dimension("9pt")).asInchesString}" text-anchor="middle" >$captionText</text>\n""")
    sb.toString()
  }
}

case class GraphicImageWithCaption(data : String, text : String) extends GraphicWithCaption {
  private val image = BlockGraphic.createGraphic("png", Length.dimension(".5in"), Length.dimension(".5in"),
    Length.dimension("0fu"), Length.dimension("0fu"), ImageData.SEAL)

  override def graphicSVG(loc : Location) : String = {
    image.toSVG(loc, false)
  }

  override def captionText : String = text
}

case class GraphicBarcodeWithCaption(data : String, text : String) extends GraphicWithCaption {
  private val image = BlockGraphic.createGraphic("bar-code", Length.dimension("1in"), Length.dimension(".5in"),
    Length.dimension("0fu"), Length.dimension("0fu"), data)

  override def graphicSVG(loc : Location) : String = {
    image.toSVG(loc, false)
  }

  override def captionText : String = text
}

case class GraphicBracketWithCaption(direction : String, text : String, color : String) extends GraphicWithCaption {
  private val bracket = BlockBracket(direction, Length.dimension("0fu"), color, null)

  override def graphicSVG(loc : Location) : String = {
    bracket.toSVG(loc, false)
  }

  override def captionText : String = text
}

case class GraphicShapeWithCaption(graphicType : String, text : String, width : Length = Length.dimension("0fu"), height : Length = Length.dimension("0fu")) extends GraphicWithCaption {
  private def graphicWidth : Length = if (width.equals("0fu"))
    defaultWidth
  else
    width
  private def graphicHeight : Length = if (height.equals("0fu"))
    defaultHeight
  else
    height
  private val graphicShape = BlockBox(BoxStyles.valueForStyleString(graphicType), graphicWidth, graphicHeight,
      defaultSpace, defaultSpace, Length.dimension(".01in"), "blue", "blue")

  override def graphicSVG(loc : Location) : String = {
    graphicShape.toSVG(loc, false)
  }

  override def captionText : String = text
}

case class GraphicTextWithCaption(font : GidsFont, color : String, textAlign : TextAlignments.Value, text : String, caption : String) extends GraphicWithCaption {
  private val blocktext = BlockText(font, textAlign)
  blocktext.addText(InlineText(text, font))

  override def graphicSVG(loc : Location) : String = {
    s"""<rect x="${loc.left.asInchesString}" y="${loc.top.asInchesString}" width="${(loc.right - loc.left).asInchesString}" height="${(loc.bottom - loc.top).asInchesString}" fill="$color"/>\n """ +
    blocktext.toSVG(loc, false)
  }

  override def captionText : String = caption
}

