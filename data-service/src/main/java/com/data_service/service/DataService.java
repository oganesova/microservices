package com.data_service.service;

import com.data_service.dto.DataDto;
import com.data_service.entity.DataEntity;
import com.data_service.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.data_service.dto.DataDto.mapDtoToEntity;
import static com.data_service.dto.DataDto.mapEntityToDto;

@Service
@Slf4j
public class DataService {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveData(DataDto dataDto) {
        log.info("Saving data: {}", dataDto);

        DataEntity dataEntity = mapDtoToEntity(dataDto);
        dataRepository.save(dataEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void writeToDatabase(DataDto data) {
        log.info("Saving data: {}", data);
        DataEntity dataEntity = mapDtoToEntity(data);
        dataRepository.save(dataEntity);
        log.info("Data saved successfully");
    }


}
