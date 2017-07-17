package com.westat

import com.westat.Length
import scala.collection.mutable.ListBuffer

/**
  * Created by lee on 6/30/17.
  */
object Pages {
  private val list = new ListBuffer[Page]
  list += Page("Letter portrait", Length.dimension("8.5in"), Length.dimension("11in"), "lightgreen")
  list += Page("Letter landscape", Length.dimension("11in"), Length.dimension("8.5in"), "orange")

  def pageForName(aName : String) : Option[Page] = {
    list.find(p => p.name == aName)
  }
}
