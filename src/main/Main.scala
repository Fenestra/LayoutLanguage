package main


import com.westat.sfo.TextAlignments
import com.westat.{Length, Location}
import com.westat._
import com.westat.gids.GidsFont
/**
  * Created by lee on 6/29/17.
  */


object Main {

  def main(args: Array[String]): Unit = {
    val page = Pages.pageForName("Letter portrait") match {
      case Some(p) => p
      case None => null
    }
    var loc = Location.create(".5in", "1in", "7.5in", "1.5in")
    page.addGroup(Group("shapeBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicShapeWithCaption("triangle-east", "eastTriangle")).
      addItem(GraphicShapeWithCaption("triangle-north", "smallNorthTriangle", Length.dimension(".5in"), Length.dimension(".75in"))).
      addItem(GraphicShapeWithCaption("triangle-west", "westTriangle")).
      addItem(GraphicShapeWithCaption("triangle-south", "southTriangle")).
      addItem(GraphicShapeWithCaption("box", "smallNormalBox", Length.dimension(".5in"), Length.dimension(".5in"))).
      addItem(GraphicShapeWithCaption("diamond", "diamond")).
      addItem(GraphicShapeWithCaption("ellipse", "ellipse", Length.dimension(".75in"), Length.dimension("1in")))

    loc = loc.moveDown(Length.dimension("2in"))
    //  loc.top = Length.dimension("3in")
    //  loc.bottom = loc.top + loc.height
    page.addGroup(Group("bracketBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicBracketWithCaption("east", "eastBlackBracket", "black")).
      addItem(GraphicBracketWithCaption("north", "northRedBracket", "red")).
      addItem(GraphicBracketWithCaption("west", "westGreenBracket", "green")).
      addItem(GraphicBracketWithCaption("south", "southOrangeBracket", "orange")).
      addItem(GraphicImageWithCaption("box", "seal image"))

    loc = loc.moveDown(Length.dimension("2.5in")).shrinkHeight(Length.dimension(".5in"))
//    loc.top = Length.dimension("5.5in")
//    loc.height = Length.dimension("1in")
//    loc.bottom = loc.top + loc.height
    val font = GidsFont("Arial", "black", "", Length.dimension("10pt"))
    page.addGroup(Group("textAlignBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corporation", "left")).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corporation", "center")).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corporation", "right"))

    loc = loc.moveDown(Length.dimension("1.5in"))
//    loc.top = Length.dimension("7in")
//    loc.height = Length.dimension("1in")
//    loc.bottom = loc.top + loc.height
    page.addGroup(Group("textAlignBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corporation is the best company in the world.  We hope.", "right")).
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corporation is the best company in the world.  We hope.", "left")).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corporation is the best company in the world.  We hope.", "center"))
      StringUtilities.writeFile("test.svg", page.toSVG)
  }


}