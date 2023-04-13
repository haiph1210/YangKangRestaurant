package com.haiph.orderservice.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.spi.MappingContext;

import java.util.UUID;

public class UUIDConvester extends AbstractConverter<UUID,Integer> {

    @Override
    protected Integer convert(UUID uuid) {
        return uuid.hashCode();
    }
}
