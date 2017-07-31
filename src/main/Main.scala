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
      addItem(GraphicShapeWithCaption("ellipse", "ellipse", Length.dimension("1in"), Length.dimension("1in")))

    loc = loc.moveDown(Length.dimension("2in"))
    page.addGroup(Group("bracketBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicBracketWithCaption("east", "eastBlackBracket", "black")).
      addItem(GraphicBracketWithCaption("north", "northRedBracket", "red")).
      addItem(GraphicBracketWithCaption("west", "westGreenBracket", "green")).
      addItem(GraphicBracketWithCaption("south", "southOrangeBracket", "orange")).
      addItem(GraphicImageWithCaption("box", "seal image")).
      addItem(GraphicBarcodeWithCaption("2475425675", "barcode"))


    loc = loc.moveDown(Length.dimension("2.5in")).shrinkHeight(Length.dimension(".5in"))
    val font = GidsFont("Arial", "black", "", Length.dimension("10pt"))
    val boldfont = GidsFont("Arial", "black", "700", Length.dimension("10pt"))
    val italicfont = GidsFont("Arial", "black", "500", Length.dimension("10pt"))
    page.addGroup(Group("textAlignBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corporation", "left")).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corporation", "center")).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corporation", "right"))

    loc = loc.moveDown(Length.dimension("1.5in"))
    page.addGroup(Group("textAlignWrapBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corporation is the best company in the world.  We hope.", "right")).
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corporation is the best company in the world.  We hope.", "left")).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corporation is the best company in the world.  We hope.", "center"))

    loc = loc.moveDown(Length.dimension("1.5in"))
    page.addGroup(Group("inlineFontsBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corp is the", "leftbold").
        addText("best", boldfont).
        addText("company.")
      ).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corp is the", "centerbold").
      addText("best", boldfont).
      addText("company.")
      ).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corp is the", "rightitalic").
        addText("best", italicfont).
        addText("company.")
      )

    StringUtilities.writeFile("test.svg", page.toSVG)
  }


}
