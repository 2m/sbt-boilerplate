package spray.boilerplate

import org.specs2.mutable.Specification

class TemplateParserSpecs extends Specification {
  import TemplateParser.parse

  "TemplateParser.parse" should {
    "without expansion" in {
      parse("abc") === FixedString("abc")
    }
    "just expansion" in {
      parse("[# def #]") === Expand(LiteralString(" def "), ", ")
    }
    "multiple expansions" in {
      parse("[#a#]abc[#b#]") ===
        Sequence(List(Expand(LiteralString("a"), ", "), FixedString("abc"), Expand(LiteralString("b"), ", ")))
    }
    "one surrounded expansion" in {
      parse("abc[#a#]def") === Sequence(List(FixedString("abc"), Expand(LiteralString("a"), ", "), FixedString("def")))
    }
  }
}