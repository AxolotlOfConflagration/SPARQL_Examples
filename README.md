# SPARQL Examples
### Intro
This project uses Scala and Apache Jena in order to run SPARQL queries.
Queries can either be run using Local Apache Jena Fuseki instance or using external DBpedia SPARQL endpoint.

For demonstration purposes, it is better to use a local server instance for running federated queries.
When running queries through DBpedia endpoint you can't use federated queries.

### Running application
In directory `utils` they are two scripts. First `get_jena.sh` is used to download and unpack the server in `utils` directory.
The second `start_fuseki.sh` is used to run the Fuseki server locally. 
Note that you need to `cd` to directory `<YOUR_PROJECT_DIR>/utils` when running any of these scripts.

Then in the root project folder, you can run `sbt run` to start application.
The first run can take some time because `sbt` needs to pull all dependencies.
If prompted to select the main class, select the option that says `GuiExamples`.
