package org.berdilaovidiu.app;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;

import java.util.Iterator;


/**
 * <p>
 * Simple example of using the persistent db layer with ontology models.  Assumes
 * that a PostgreSQL database called 'jenatest' has been set up, for a user named ijd.
 * </p>
 *
 * @author Ian Dickinson, HP Labs
 *         (<a  href="mailto:Ian.Dickinson@hp.com" >email</a>)
 * @version CVS $Id: PersistentOntology.java.html,v 1.4 2007/01/17 10:44:23 andy_seaborne Exp $
 */
public class PersistentOntology {

    public void loadDB(ModelMaker maker, String source) {
        // use the model maker to get the base model as a persistent model
        // strict=false, so we get an existing model by that name if it exists
        // or create a new one
        Model base = maker.createModel(source, false);

        // now we plug that base model into an ontology model that also uses
        // the given model maker to create storage for imported models
        OntModel m = ModelFactory.createOntologyModel(getModelSpec(maker), base);

        // now load the source document, which will also load any imports
        m.read(source);
    }

    public void listClasses(ModelMaker maker, String modelID) {
        // use the model maker to get the base model as a persistent model
        // strict=false, so we get an existing model by that name if it exists
        // or create a new one
        Model base = maker.createModel(modelID, false);

        // create an ontology model using the persistent model as base
        OntModel m = ModelFactory.createOntologyModel(getModelSpec(maker), base);

        for (Iterator i = m.listClasses(); i.hasNext(); ) {
            OntClass c = (OntClass) i.next();
            System.out.println("Class " + c.getURI());
        }
    }


    public ModelMaker getRDBMaker(String dbURL, String dbUser, String dbPw, String dbType, boolean cleanDB) {
        try {
            // Create database connection
            IDBConnection conn = new DBConnection(dbURL, dbUser, dbPw, dbType);

            // do we need to clean the database?
            if (cleanDB) {
                conn.cleanDB();
            }

            // Create a model maker object
            return ModelFactory.createModelRDBMaker(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public OntModelSpec getModelSpec(ModelMaker maker) {
        // create a spec for the new ont model that will use no inference over models
        // made by the given maker (which is where we get the persistent models from)
        OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
        spec.setImportModelMaker(maker);

        return spec;
    }
}