package com.nobroker.smarthome.handler;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class AlexaSkillHandler extends SkillStreamHandler{

    public static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchNobrokerSmartHomeHandler(),
                        new ActivatePartyLightIntentHandler(),
                        new DeactivatePartyLightIntentHandler(),
                        new ActivateFanIntentHandler(),
                        new DeactivateFanIntentHandler()
                )
                .build();
    }

    private static class ActivatePartyLightIntentHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(Predicates.intentName("activate party light"));
        }

        @Override
        public Optional<Response> handle(HandlerInput handlerInput) {
            String speechText = "Party lights activated.";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .build();
        }
    }

    private static class DeactivatePartyLightIntentHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(Predicates.intentName("DeactivatePartyLightIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput handlerInput) {
            // Logic to deactivate party lights
            String speechText = "Party lights deactivated.";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .build();
        }
    }

    private static class ActivateFanIntentHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(Predicates.intentName("ActivateFanIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput handlerInput) {
            // Logic to activate the fan
            String speechText = "Fan activated.";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .build();
        }
    }

    private static class DeactivateFanIntentHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(Predicates.intentName("DeactivateFanIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput handlerInput) {
            // Logic to deactivate the fan
            String speechText = "Fan deactivated.";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .build();

        }
    }

    private static class LaunchNobrokerSmartHomeHandler implements  RequestHandler{

        @Override
        public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(Predicates.intentName("Alexa Hommie"));
        }

        @Override
        public Optional<Response> handle(HandlerInput handlerInput) {
            String speechText = "Hi";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .build();
        }
    }
}
