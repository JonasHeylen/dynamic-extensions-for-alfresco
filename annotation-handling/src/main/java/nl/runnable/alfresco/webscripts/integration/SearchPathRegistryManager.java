package nl.runnable.alfresco.webscripts.integration;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.extensions.webscripts.Store;
import org.springframework.util.Assert;

/**
 * Manages the registration and unregistration of {@link Store}s in a {@link SearchPathRegistry}.
 * 
 * @author Laurens Fridael
 * 
 */
public class SearchPathRegistryManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/* Dependencies */

	private SearchPathRegistry searchPathRegistry;

	/* Configuration */

	private List<Store> stores = Collections.emptyList();

	/* Operations */

	public void registerStores() {
		Assert.state(getSearchPathRegistry() != null);

		if (logger.isDebugEnabled()) {
			logger.debug("Registering {} stores with the SearchPathRegistry.", getStores().size());
		}
		for (final Store store : getStores()) {
			getSearchPathRegistry().addStore(store);
		}
	}

	public void unregisterStores() {
		Assert.state(getSearchPathRegistry() != null);

		if (logger.isDebugEnabled()) {
			logger.debug("Unregistering {} stores with the SearchPathRegistry.", getStores().size());
		}
		for (final Store store : getStores()) {
			getSearchPathRegistry().removeStore(store);
		}
	}

	/* Dependencies */

	public void setSearchPathRegistry(final SearchPathRegistry searchPathRegistry) {
		this.searchPathRegistry = searchPathRegistry;
	}

	protected SearchPathRegistry getSearchPathRegistry() {
		return searchPathRegistry;
	}

	/* Configuration */

	public void setStores(final List<Store> stores) {
		Assert.notNull(stores);
		this.stores = stores;
	}

	public List<Store> getStores() {
		return stores;
	}
}
