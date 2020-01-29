package com.formation.exercice1_junit_form;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


public class MyCompteArgumentSource implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(
				arguments(200, 450),
				arguments(100, 350),
				arguments(-100, 250),
				arguments(1000, 500)
				);
	}

}
