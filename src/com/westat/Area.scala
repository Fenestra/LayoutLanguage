package com.westat

import com.westat.Length

/**
  * Created by lee on 6/30/17.
  */
case class Area(id : String, left : Length, top : Length, width : Length, height : Length) extends Container

/*
area left,top width, height
  cad add graphic items but need to know orientation of added items
 */