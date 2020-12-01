package io.quarkiverse.jjwtjackson.deployment;

import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ServiceProviderBuildItem;

class JjwtJacksonProcessor {

    @BuildStep
    void registerForReflection(BuildProducer<ReflectiveClassBuildItem> reflectiveClasses) {
        reflectiveClasses.produce(new ReflectiveClassBuildItem(true, true, DefaultJwtBuilder.class));
    }

    @BuildStep
    void jwtServiceProviderBuildItem(BuildProducer<ServiceProviderBuildItem> serviceProviders) {
        serviceProviders.produce(new ServiceProviderBuildItem(Serializer.class.getName(), JacksonSerializer.class.getName()));
        serviceProviders
                .produce(new ServiceProviderBuildItem(Deserializer.class.getName(), JacksonDeserializer.class.getName()));
    }
}
