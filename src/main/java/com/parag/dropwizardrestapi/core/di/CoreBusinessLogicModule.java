package com.parag.dropwizardrestapi.core.di;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author Parag Ghosh <paragpratim@gmail.com>
 *
 *         Core business logic module is a generic module extending guice
 *         abstract module. Methods have been declared for class binding and
 *         named class binding.
 * 
 *         Extend this class for application specific guice dependency injection
 *         bindings.
 */
public abstract class CoreBusinessLogicModule extends AbstractModule {

	/**
	 * To bind Implementation class to Interface.
	 * 
	 * @param target
	 * @param toBind
	 */
	protected <T> void bind(Class<T> target, Class<? extends T> toBind) {
		if (null != toBind && !toBind.equals(target)) {
			bind(target).to(toBind);
		}
	}

	/**
	 * To bind Implementation class to Interface using Named annotation.
	 * 
	 * @param target
	 * @param toBind
	 * @param name
	 */
	protected <T> void bind(Class<T> target, Class<? extends T> toBind, String name) {
		if (null != toBind && !toBind.equals(target)) {
			bind(target).annotatedWith(Names.named(name)).to(toBind);
		}
	}
}
