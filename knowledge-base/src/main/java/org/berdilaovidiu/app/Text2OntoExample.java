package org.berdilaovidiu.app;

import org.ontoware.text2onto.algorithm.AlgorithmController;
import org.ontoware.text2onto.algorithm.ComplexAlgorithm;
import org.ontoware.text2onto.algorithm.ControllerException;
import org.ontoware.text2onto.algorithm.combiner.AverageCombiner;
import org.ontoware.text2onto.algorithm.concept.RTFConceptExtraction;
import org.ontoware.text2onto.algorithm.concept.TFIDFConceptExtraction;
import org.ontoware.text2onto.algorithm.instance.ExampleInstanceExtraction;
import org.ontoware.text2onto.algorithm.instance.TFIDFInstanceExtraction;
import org.ontoware.text2onto.algorithm.relation.general.SubcatRelationExtraction;
import org.ontoware.text2onto.algorithm.relation.subtopicOf.SubtopicOfRelationConversion;
import org.ontoware.text2onto.algorithm.relation.subtopicOf.SubtopicOfRelationExtraction;
import org.ontoware.text2onto.algorithm.similarity.ContextSimilarityExtraction;
import org.ontoware.text2onto.algorithm.taxonomic.instanceOf.ContextInstanceClassification;
import org.ontoware.text2onto.algorithm.taxonomic.instanceOf.PatternInstanceClassification;
import org.ontoware.text2onto.algorithm.taxonomic.subclassOf.PatternConceptClassification;
import org.ontoware.text2onto.algorithm.taxonomic.subclassOf.VerticalRelationsConceptClassification;
import org.ontoware.text2onto.algorithm.taxonomic.subclassOf.WordNetConceptClassification;
import org.ontoware.text2onto.corpus.Corpus;
import org.ontoware.text2onto.corpus.CorpusFactory;
import org.ontoware.text2onto.corpus.DocumentFactory;
import org.ontoware.text2onto.ontology.OWLWriter;
import org.ontoware.text2onto.ontology.OntologyWriter;
import org.ontoware.text2onto.pom.POM;
import org.ontoware.text2onto.pom.POMFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/27/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Text2OntoExample implements NewsItemHandler {

    public static void main(String[] args) throws Exception {
        String corpusLocation = "E:/master3/datasources";
        Corpus corpus = CorpusFactory.newCorpus(new File(corpusLocation).toURI());
//        File corpusDirectory = new File(corpusLocation);
//        FilenameFilter filter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        };
//        for (String fileName : corpusDirectory.list(filter)) {
//            File file = new File(fileName);
//
//            AbstractDocument doc = DocumentFactory.newDocument("file:/" + corpusLocation + "/" + file.getName().replaceAll("\\s", "%20"));
//            corpus.addDocument(doc);
//
//        }

        POM pom = POMFactory.newPOM();

        Properties properties = new Properties();
        properties.load(new FileInputStream("E:/utility/text2onto-clean/text2onto.properties"));
        try {
            AlgorithmController ac = new AlgorithmController(corpus, pom, properties);
            //concept extraction
            ComplexAlgorithm conceptExtraction = new ComplexAlgorithm("Concept");
            conceptExtraction.setCombiner(new AverageCombiner());
            ac.addAlgorithm(conceptExtraction);
            ac.addAlgorithmTo(conceptExtraction, new TFIDFConceptExtraction());
            ac.addAlgorithmTo(conceptExtraction, new RTFConceptExtraction());

            //instance
            ComplexAlgorithm instanceExtraction = new ComplexAlgorithm("Instance");
            instanceExtraction.setCombiner(new AverageCombiner());
            ac.addAlgorithm(instanceExtraction);
            ac.addAlgorithmTo(instanceExtraction, new ExampleInstanceExtraction());
            ac.addAlgorithmTo(instanceExtraction, new TFIDFInstanceExtraction());

            //similarity
            ComplexAlgorithm similarity = new ComplexAlgorithm("Similarity");
            similarity.setCombiner(new AverageCombiner());
            ac.addAlgorithm(similarity);
            ac.addAlgorithmTo(similarity, new ContextSimilarityExtraction());

            //concept classification
            ComplexAlgorithm conceptClassification = new ComplexAlgorithm("Subclass Of");
            conceptClassification.setCombiner(new AverageCombiner());
            ac.addAlgorithm(conceptClassification);

            ac.addAlgorithmTo(conceptClassification, new PatternConceptClassification());
            ac.addAlgorithmTo(conceptClassification, new VerticalRelationsConceptClassification());
            ac.addAlgorithmTo(conceptClassification, new WordNetConceptClassification());

            //instance of
            ComplexAlgorithm instanceOf = new ComplexAlgorithm("Instance Of");
            instanceOf.setCombiner(new AverageCombiner());
            ac.addAlgorithm(instanceOf);
            ac.addAlgorithmTo(instanceOf, new ContextInstanceClassification());
            ac.addAlgorithmTo(instanceOf, new PatternInstanceClassification());

            //relation
            ComplexAlgorithm relation = new ComplexAlgorithm("Relation");
            relation.setCombiner(new AverageCombiner());
            ac.addAlgorithm(relation);
            ac.addAlgorithmTo(relation, new SubcatRelationExtraction());

            //subtopic Of
            ComplexAlgorithm subtopicOf = new ComplexAlgorithm("Subtopic Of");
            subtopicOf.setCombiner(new AverageCombiner());
            ac.addAlgorithm(subtopicOf);
            ac.addAlgorithmTo(subtopicOf, new SubtopicOfRelationConversion());
            ac.addAlgorithmTo(subtopicOf, new SubtopicOfRelationExtraction());


            Date firstDate = new Date();

            ac.execute();
            System.out.println("XXXXXXXX " + (new Date().getTime() - firstDate.getTime()));
            corpus.addDocument(DocumentFactory.newDocument(new File("E:/master3/datasources/stocks.txt").toURI()));
            Date secondDate = new Date();
            ac.execute();
            System.out.println("XXXXXXXX " + (new Date().getTime() - secondDate.getTime()));


//            OntologyWriter writer = new OWLWriter(pom);
//            writer.write(new File("E:/master3/datasources/onto.owl").toURI());
            OntologyWriter writer = new OWLWriter(pom);
            writer.write(new File("E:/master3/ontologies/onto.owl").toURI());
        } catch (ControllerException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void handleNews(NewsItem item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
