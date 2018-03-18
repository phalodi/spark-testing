import org.apache.spark.rdd.RDD
import org.apache.spark.streaming._
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Span}
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class SparkStreamingExampleSpec extends FlatSpec
  with SparkStreamingSpec with GivenWhenThen with Matchers with Eventually {

  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(5000, Millis)))

  "Sample set" should "be counted" in {
    Given("streaming context is initialized")
    val lines = mutable.Queue[RDD[String]]()

    var results = ListBuffer.empty[Array[WordCount]]

    WordCount.count(ssc,
      ssc.queueStream(lines)) { (wordsCount: RDD[WordCount]) =>
      results += wordsCount.collect()
    }

    ssc.start()

    When("first set of words queued")
    lines += sc.makeRDD(Seq("a", "b"))

    Then("words counted after first slide")
    ClockWrapper.advance(ssc, Seconds(5000))
    eventually {
      results.last should equal(Array(
        WordCount("a", 1),
        WordCount("b", 1)))
    }


  }
}

