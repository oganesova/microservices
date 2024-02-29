package com.data_service.dto;

import com.data_service.entity.DataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Objects;

@Data
public class DataDto {

    private Long id;
    private String message;
    public static DataDto mapEntityToDto(DataEntity dataEntity) {
        Objects.requireNonNull(dataEntity, "DTO cannot be null");
        DataDto dataDto = new DataDto();
        dataDto.setId(dataEntity.getId());
        dataDto.setMessage(dataEntity.getMessage());
        return dataDto;
    }

    public static DataEntity mapDtoToEntity(DataDto dataDto) {
        Objects.requireNonNull(dataDto, "DTO cannot be null");
        DataEntity dataEntity= new DataEntity();
        dataEntity.setId(dataDto.getId());
        dataEntity.setMessage(dataDto.getMessage());
        return dataEntity;
    }
    public static DataDto fromKafkaMessage(String kafkaMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(kafkaMessage, DataDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Kafka message to DataDTO", e);
        }
    }
}
