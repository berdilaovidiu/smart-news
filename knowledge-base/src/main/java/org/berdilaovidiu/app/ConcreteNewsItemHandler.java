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
import org.ontoware.text2onto.corpus.CorpusException;
import org.ontoware.text2onto.corpus.CorpusFactory;
import org.ontoware.text2onto.corpus.DocumentFactory;
import org.ontoware.text2onto.ontology.OWLWriter;
import org.ontoware.text2onto.ontology.OntologyWriter;
import org.ontoware.text2onto.pom.POM;
import org.ontoware.text2onto.pom.POMFactory;
import org.semanticweb.kaon2.api.KAON2Exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/1/11
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConcreteNewsItemHandler implements NewsItemHandler {

    private Corpus corpus;
    private POM pom;
    private AlgorithmController algorithmController;

    public ConcreteNewsItemHandler() throws CorpusException, IOException {
        initializeCorpus();
        initializePOM();
        initializeController();
    }

    private void initializeController() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("E:/utility/text2onto-clean/text2onto.properties"));

        try {
            algorithmController = new AlgorithmController(corpus, pom, properties);
            //concept extraction
            ComplexAlgorithm conceptExtraction = new ComplexAlgorithm("Concept");
            conceptExtraction.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(conceptExtraction);
            algorithmController.addAlgorithmTo(conceptExtraction, new TFIDFConceptExtraction());
            algorithmController.addAlgorithmTo(conceptExtraction, new RTFConceptExtraction());

            //instance
            ComplexAlgorithm instanceExtraction = new ComplexAlgorithm("Instance");
            instanceExtraction.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(instanceExtraction);
            algorithmController.addAlgorithmTo(instanceExtraction, new ExampleInstanceExtraction());
            algorithmController.addAlgorithmTo(instanceExtraction, new TFIDFInstanceExtraction());

            //similarity
            ComplexAlgorithm similarity = new ComplexAlgorithm("Similarity");
            similarity.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(similarity);
            algorithmController.addAlgorithmTo(similarity, new ContextSimilarityExtraction());

            //concept classification
            ComplexAlgorithm conceptClassification = new ComplexAlgorithm("Subclass Of");
            conceptClassification.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(conceptClassification);

            algorithmController.addAlgorithmTo(conceptClassification, new PatternConceptClassification());
            algorithmController.addAlgorithmTo(conceptClassification, new VerticalRelationsConceptClassification());
            algorithmController.addAlgorithmTo(conceptClassification, new WordNetConceptClassification());

            //instance of
            ComplexAlgorithm instanceOf = new ComplexAlgorithm("Instance Of");
            instanceOf.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(instanceOf);
            algorithmController.addAlgorithmTo(instanceOf, new ContextInstanceClassification());
            algorithmController.addAlgorithmTo(instanceOf, new PatternInstanceClassification());

            //relation
            ComplexAlgorithm relation = new ComplexAlgorithm("Relation");
            relation.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(relation);
            algorithmController.addAlgorithmTo(relation, new SubcatRelationExtraction());

            //subtopic Of
            ComplexAlgorithm subtopicOf = new ComplexAlgorithm("Subtopic Of");
            subtopicOf.setCombiner(new AverageCombiner());
            algorithmController.addAlgorithm(subtopicOf);
            algorithmController.addAlgorithmTo(subtopicOf, new SubtopicOfRelationConversion());
            algorithmController.addAlgorithmTo(subtopicOf, new SubtopicOfRelationExtraction());
        } catch (ControllerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void initializePOM() {
        pom = POMFactory.newPOM();
    }

    private void initializeCorpus() throws CorpusException {
        String corpusLocation = "E:/master3/datasources";
        corpus = CorpusFactory.newCorpus(new File(corpusLocation).toURI());
    }

    private void execute() throws ControllerException {
        algorithmController.execute();
    }

    private void persist() {
        OntologyWriter writer = null;
        try {
            writer = new OWLWriter(pom);
            writer.write(new File("E:/master3/ontologies/onto.owl").toURI());
        } catch (KAON2Exception e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleNews(NewsItem item) {
        //TODO  give text to a terms extraction engine
        try {
            System.out.println("URI : " + item.getURI());
            corpus.addDocument(DocumentFactory.newDocument(item.getURI()));
        } catch (CorpusException e) {
            e.printStackTrace();
        }
        //TODO get relations between terms
        //TODO add triples to existing ontology
        try {
            execute();
        } catch (ControllerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
