package example


import javax.swing.border.EmptyBorder
import org.apache.jena.fuseki.Fuseki

import scala.swing._
import scala.swing.event.ButtonClicked

object GuiExamples extends SimpleSwingApplication {

  //http://localhost:3030/ds/sparql

  //  private val dbpediaRunner = QueryRunner("http://dbpedia.org/sparql")
  private var dbpediaRunner = QueryRunner("http://localhost:3030/ds/sparql")


  private val globalFond = new Font("Courier", 0, 12)

  val textInput = new TextArea {
    font = globalFond
    text =
      """PREFIX dbo:<http://dbpedia.org/ontology/>
        |PREFIX dbp:<http://dbpedia.org/property/>
        |PREFIX dbpedia:<http://dbpedia.org/resource/>
        |PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        |PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
        |PREFIX tto:<http://example.org/tuto/ontology#>
        |PREFIX ttr:<http://example.org/tuto/resource#>
        |PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>
        |SELECT ?subject ?spouse ?ageDiff where {
        |  SERVICE <http://dbpedia.org/sparql> {
        |    ?subject dbp:spouse ?spouse .
        |    ?subject a dbo:Person .
        |    ?spouse a dbo:Person .
        |    ?subject dbp:birthDate ?subjectAge .
        |    ?spouse dbp:birthDate ?spouseAge .
        |  }
        |  # Computes the age difference
        |  BIND (year(?subjectAge) - ( year(?spouseAge)) AS ?ageDiff )
        |} LIMIT 50
        |""".stripMargin
  }

  private val textOutput = new TextArea {
    font = globalFond
    text =
      """
        |Hello
        |Scala :3
        |""".stripMargin
  }

  private val input = new GridBagPanel {
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.anchor = GridBagPanel.Anchor.West
      c
    }

    border = new EmptyBorder(5, 5, 5, 5)

    //  private val dbpediaRunner = QueryRunner("")
    //    private var dbpediaRunner = QueryRunner("")

    add(new FlowPanel {
      contents += new Button {
        text = "Use DBpedia"
        reactions += {
          case ButtonClicked(_) => dbpediaRunner = new QueryRunner("http://dbpedia.org/sparql")
        }
      }
      contents += new Button {
        text = "Use Local Server"
        reactions += {
          case ButtonClicked(_) => dbpediaRunner = new QueryRunner("http://localhost:3030/ds/sparql")
        }
      }
    }, constraints(0, 0, gridwidth = 4))

    add(new ScrollPane(textInput), constraints(0, 1, gridwidth = 4, weightx = 2.0, weighty = 2.0, fill = GridBagPanel.Fill.Both))

    add(new Button {
      text = "Query TEXT"
      reactions += {
        case ButtonClicked(_) => textOutput.text = dbpediaRunner.query(textInput.text).asTextOrError()
      }
    }, constraints(0, 2))
    add(new Button {
      text = "Query JSON"
      reactions += {
        case ButtonClicked(_) => textOutput.text = dbpediaRunner.query(textInput.text).asJsonOrError()
      }
    }, constraints(1, 2))
    add(new Button {
      text = "Query CSV"
      reactions += {
        case ButtonClicked(_) => textOutput.text = dbpediaRunner.query(textInput.text).asCSVOrError()
      }
    }, constraints(2, 2))
    add(new Button {
      text = "Query XML"
      reactions += {
        case ButtonClicked(_) => textOutput.text = dbpediaRunner.query(textInput.text).asXMLOrError()
      }
    }, constraints(3, 2))
  }

  val output = new GridBagPanel {
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.anchor = GridBagPanel.Anchor.West
      c
    }

    add(new ScrollPane(textOutput), constraints(0, 0, weightx = 2.0, weighty = 2.0, fill = GridBagPanel.Fill.Both))
    add(new Button {
      text = "Clear"
      reactions += {
        case ButtonClicked(_) => textOutput.text = ""
      }
    }, constraints(0, 1))
  }

  override def top: Frame = new MainFrame {
    title = "SPARQL examples"
    contents = new GridPanel(2, 1) {
      hGap = 10
      vGap = 10
      border = new EmptyBorder(10, 10, 10, 10)
      contents += input
      contents += output
    }
    size = new Dimension(800, 600)
  }
}
