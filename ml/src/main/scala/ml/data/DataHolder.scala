package ml.data

import scala.collection.mutable.ArrayBuffer

object DataHolder {

}

class DataHolder(val trainSet: Array[Data], val testSet: Array[Data], val otherSetMap: Map[String, Array[Data]]) {
  val dataSet: Array[Data] = makeDataSet
  
  def this(trainSet: Array[Data], testSet: Array[Data]){
    this(trainSet, testSet, Map("" -> Array(new Data(null, null))))
  }

  def makeDataSet(): Array[Data] = {
    val buffer = new ArrayBuffer[Data]()
    buffer.appendAll(trainSet)
    buffer.appendAll(testSet)
    otherSetMap.values.foreach(value =>  buffer.appendAll(value))
    buffer.toArray
  }
  
  

}