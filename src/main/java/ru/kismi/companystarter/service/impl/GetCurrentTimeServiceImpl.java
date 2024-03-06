package ru.kismi.companystarter.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.kismi.companystarter.service.GetCurrentTimeService;

import java.time.Instant;

@RequiredArgsConstructor
public class GetCurrentTimeServiceImpl implements GetCurrentTimeService {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String getCurrentTime() {
        return objectMapper.writeValueAsString(Instant.now());
    }
}