import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream

case class WordCount(word: String, count: Int)

object WordCount {

  def count(sc: SparkContext, lines: RDD[String]): RDD[WordCount] = {

    val words = lines.flatMap(_.split(" ")).filter(_.nonEmpty)

    val wordCounts = words.map(word => (word, 1)).reduceByKey(_ + _).map {
      case (word: String, count: Int) => WordCount(word, count)
    }

    wordCounts
  }

  type WordHandler = (RDD[WordCount]) => Unit


  def count(scc: StreamingContext, lines: DStream[String])(wordHandler: WordHandler) = {

    val words = lines.transform(x => x)

    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _).map {
      case (word: String, count: Int) => WordCount(word, count)
    }

    wordCounts.foreachRDD((rdd: RDD[WordCount]) => {
      wordHandler(rdd.sortBy(_.word))
    })
  }
}