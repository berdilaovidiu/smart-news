package org.berdilaovidiu.app;

import org.ontoware.text2onto.algorithm.AlgorithmController;
import org.ontoware.text2onto.algorithm.ControllerException;
import org.ontoware.text2onto.algorithm.concept.TFIDFConceptExtraction;
import org.ontoware.text2onto.corpus.Corpus;
import org.ontoware.text2onto.corpus.CorpusException;
import org.ontoware.text2onto.corpus.CorpusFactory;
import org.ontoware.text2onto.pom.POM;
import org.ontoware.text2onto.pom.POMFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/27/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Text2OntoExample {

    public static void main(String[] args) throws CorpusException, ControllerException {
        Corpus corpus = CorpusFactory.newCorpus("");
        POM pom  = POMFactory.newPOM();

        AlgorithmController ac = new AlgorithmController(corpus, pom);

        //concept extraction
        ac.addAlgorithm(new TFIDFConceptExtraction());

        //concept classification


    }
}
