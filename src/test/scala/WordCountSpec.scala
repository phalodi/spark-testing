import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

class WordCountSpec extends FlatSpec with SparkSpec with Matchers with GivenWhenThen {

  "Empty set" should "be counted" in {
    Given("empty set")
    val lines = Array("")

    When("count words")
    val wordCounts = WordCount.count(sc, sc.parallelize(lines)).collect()

    Then("empty count")
    wordCounts shouldBe empty
  }

//  "Shakespeare most famous quote" should "be counted" in {
//    Given("quote")
//    val lines = Array("To be or not to be.", "That is the question.")
//
//    When("count words")
//    val wordCounts = WordCount.count(sc, sc.parallelize(lines)).collect()
//
//    Then("words counted")
//    wordCounts should equal(Array(
//      WordCount("question.",1),
//      WordCount("the",1),
//      WordCount("is",1),
//      WordCount("not",1),
//      WordCount("or",1),
//      WordCount("be",1),
//      WordCount("to",1),
//      WordCount("To",1),
//      WordCount("be.",1),
//      WordCount("That",1)))
//  }


}
