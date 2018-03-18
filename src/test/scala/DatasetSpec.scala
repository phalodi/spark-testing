import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

class DatasetSpec extends FlatSpec with SparkDatasetSpec with Matchers with GivenWhenThen {

  "Empty set" should "be counted" in {
    Given("empty set")

    val _spark = sparkSession
    import _spark.implicits._

    val ds = Seq(Person("xyz", "1"), Person("abc", "32")).toDS()

    When("count words")
    val wordCounts = CountJson.countName("abc1",ds).collect()

    Then("empty count")
    wordCounts shouldBe empty
  }


  "Find person with name abc" should "be counted" in {
    Given("empty set")

    val _spark = sparkSession
    import _spark.implicits._

    val ds = Seq(Person("xyz", "1"), Person("abc", "32")).toDS()

    When("count words")
    val wordCounts = CountJson.countName("abc",ds).collect()

    Then("empty count")
    wordCounts shouldBe Array(Person("abc","32"))
  }
}
