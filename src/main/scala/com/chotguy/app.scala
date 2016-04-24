package com.chotguy

import com.chotguy.service.{MemoryProductService, TillService}

object app {

  def main(args: Array[String]) {
    if (args.length == 0)
      println("Usage: run <product> <product>")
    else {
      val tillService = new TillService(args) with MemoryProductService
      tillService.checkout()
    }
  }

}
