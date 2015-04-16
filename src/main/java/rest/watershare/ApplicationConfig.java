package rest.watershare;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import data.model.SamplesCollection;

public class ApplicationConfig extends ResourceConfig {

	/**
     * Default constructor
     */

    /*public ApplicationConfig() {
    	this(new SamplesCollection());
    }*/


    /**
     * Main constructor
     * @param addressBook a provided address book
     */
    public ApplicationConfig(/*final SamplesCollection samplesCollection*/) {
    	register(CrossDomainFilter.class);
    	register(WaterShareService.class);
    	register(MOXyJsonProvider.class);
    	register(SamplesCollection.class);
    	register(new AbstractBinder() {

			@Override
			protected void configure() {
				//bind(samplesCollection).to(SamplesCollection.class);
			}});
	}	

}
