package com.parag.dropwizardrestapi.core.testutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.runners.model.InitializationError;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceJUnit5Extension implements TestInstancePostProcessor {

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	public @interface GuiceModules {
		Class<?>[] value();
	}

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		Class<?>[] classes = getModulesFor(testInstance.getClass());
		Injector injector = createInjectorFor(classes);
		injector.injectMembers(testInstance);
	}

	public GuiceJUnit5Extension() {
		super();
	}

	private Injector createInjectorFor(Class<?>[] classes) throws InitializationError {
		Module[] modules = new Module[classes.length];
		for (int i = 0; i < classes.length; i++) {
			try {
				modules[i] = (Module) (classes[i]).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new InitializationError(e);
			}
		}
		return Guice.createInjector(modules);
	}

	private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
		GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
		if (annotation == null)
			throw new InitializationError("Missing @GuiceModules annotation for unit test '" + klass.getName() + "'");
		return annotation.value();
	}
}
