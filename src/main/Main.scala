package main


import com.westat.sfo.{ReverseCircleKinds, TextAlignments}
import com.westat.{Length, Location}
import com.westat._
import com.westat.gids.GidsFont
/**
  * Created by lee on 6/29/17.
  */


object Main {

  def main(args: Array[String]): Unit = {

//    val props = PropertyFile("Layout.props")
//    println(s"""value of dpi is ${props.value("dpi")}""")
    Length.setDeviceDPI(96) // props.value("dpi").toInt)

    val page = Pages.pageForName("Letter portrait") match {
      case Some(p) => p
      case None => null
    }
 //   Length.test
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
      addItem(GraphicBarcodeWithCaption("2475425675", "bar-code"))

    val font = GidsFont("Arial", "black", "", Length.dimension("10pt"))
    loc = loc.moveDown(Length.dimension("2in")) // now top = Length.dimension("3in")
    page.addGroup(Group("newstuffBand", LayoutOrientations.loHorizontal, loc, Length.dimension("1in"))).
      addItem(GraphicEyeReadableWithCaption("0123456789", "eyeReadableNumber")).
      addItem(GraphicReverseCircleWithCaption(ReverseCircleKinds.rckEcon, "4", "econReverseCrcl")).
      addItem(GraphicReverseCircleWithCaption(ReverseCircleKinds.rckACSColor, "3", "arrowRevCrcl")).
      addItem(GraphicRotatedTextWithCaption("Some rotated text", "45", font, "45RotatedText")).
      addItem(GraphicRotatedTextWithCaption("More rotated text", "90", font, "90RotatedText")).
      addItem(GraphicRotatedTextWithCaption("Rotated text", "135", font, "135RotatedText")).
      addItem(GraphicRotatedTextWithCaption("UpsideDown text", "180", font, "180RotatedText"))


    loc = loc.moveDown(Length.dimension("2in")).shrinkHeight(Length.dimension(".5in"))
    val boldfont = GidsFont("Arial", "black", "700", Length.dimension("10pt"))
    val italicfont = GidsFont("Arial", "black", "", Length.dimension("10pt"), true)
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
      addItem(GraphicTextWithCaption(font, "tan", TextAlignments.taLeft, "U.S. Corporation is one of, if not the very", "leftbold").
        addText("best", boldfont).
        addText("companies ever, you betcha.")
      ).
      addItem(GraphicTextWithCaption(font, "yellow", TextAlignments.taCenter, "U.S. Corporation is one of, if not the very", "centerbold").
      addText("best", boldfont).
      addText("companies ever, you betcha.")
      ).
      addItem(GraphicTextWithCaption(font, "pink", TextAlignments.taRight, "U.S. Corporation is one of, if not the very", "rightitalic").
      addText("best", italicfont).
      addText("companies ever, you betcha.")
      )

    StringUtilities.writeFile("test.svg", page.toSVG)
  }


}
