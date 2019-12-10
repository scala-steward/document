package es.weso.document
import org.scalatest._
import java.io.StringWriter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class DocumentTest extends AnyFunSpec with Matchers {

  describe(s"Document") {
    it(s"Combine 2 null documents same line") {
      val w: StringWriter = new StringWriter 
      (DocNil :: DocNil).format(1,w)
      w.toString should be("")
    }

    it(s"Combine 2 null documents next line") {
      val w: StringWriter = new StringWriter
      (DocNil :: DocNil).format(1,w)
      w.toString should be("")
    }
  }

  describe("Text document") {
    it("Combine 2 text documents same line") {
      val w: StringWriter = new StringWriter
      (DocText("a") :: DocText("b")).format(1,w)
      w.toString should be("ab")
    }

    it("Combine 2 text documents next line") {
      val w: StringWriter = new StringWriter
      (DocText("a") :/: DocText("b")).format(1,w)
      w.toString should be("a\nb")
    }
  }

  describe("Group document") {
    it("Combine 2 group documents same line") {
      val w: StringWriter = new StringWriter
      (DocGroup(DocText("a")) :: DocGroup(DocText("b"))).format(1,w)
      w.toString should be("ab")
    }

    it("Combine 2 group documents next line") {
      val w: StringWriter = new StringWriter
      (DocGroup(DocText("a")) :/: DocGroup(DocText("b"))).format(1,w)
      w.toString should be("a\nb")
    }
  }

  describe("Nested document") {
    it("Combine 2 nested documents same line") {
      val w: StringWriter = new StringWriter
      (DocNest(1, DocText("a")) :: DocNest(1, DocText("b"))).format(1,w)
      w.toString should be("ab")
    }

    it("Combine 2 nested documents next line") {
      val w: StringWriter = new StringWriter
      (DocNest(1, DocText("a")) :/: DocNest(1, DocText("b"))).format(1,w)
      w.toString should be("a\nb")
    }
  }

  describe("Combine a text document and a string") {
    it("Combine a text document and a string in same line") {
      val w: StringWriter = new StringWriter
      ("a" :: DocText("b")).format(1,w)
      w.toString should be("ab")
    }

    it("Combine a text document and a string in next line") {
      val w: StringWriter = new StringWriter
      ("a" :/: DocText("b")).format(1,w)
      w.toString should be("a\nb")
    }
  }

  describe("Other tests") {
    it("Create an empty text document") {
      val w: StringWriter = new StringWriter
      Document.empty.format(1,w)
      w.toString should be("")
    }

    it("Create a break document") {
      val w: StringWriter = new StringWriter
      Document.break.format(1,w)
      w.toString should be(" ")
    }

    it("Create a text document from a string") {
      val w: StringWriter = new StringWriter
      Document.text("a").format(1,w)
      w.toString should be("a")
    }

    it("Create a document group from a single doc") {
      val w: StringWriter = new StringWriter
      Document.group(DocText("a")).format(1,w)
      w.toString should be("a")
    }

    it("Create a document nest from a single doc") {
      val w: StringWriter = new StringWriter
      Document.nest(1, DocText("a")).format(1,w)
      w.toString should be("a")
    }
  }
}