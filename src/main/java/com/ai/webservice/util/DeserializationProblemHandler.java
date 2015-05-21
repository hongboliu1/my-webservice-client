package com.ai.webservice.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DeserializationProblemHandler extends com.fasterxml.jackson.databind.deser.DeserializationProblemHandler {

	private static final Logger logger = LoggerFactory.getLogger(DeserializationProblemHandler.class);
	@Override
	public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer,
			Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
		logger.error("unknownproperty:" + beanOrClass.getClass().getSimpleName() + "." + propertyName);
		return super.handleUnknownProperty(ctxt, jp, deserializer, beanOrClass, propertyName);
	}
}
