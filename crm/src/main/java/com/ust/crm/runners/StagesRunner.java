package com.ust.crm.runners;

import com.ust.crm.controller.mappers.StageMapper;
import com.ust.crm.model.Stage;
import com.ust.crm.persistence.StagesRepository;
import com.ust.crm.persistence.entities.StageEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StagesRunner implements CommandLineRunner {
    private final StagesRepository stageRepository;
    private final StageMapper stageMapper = Mappers.getMapper(StageMapper.class);

    @Override
    public void run(String... args) throws Exception {
        StageEntity stage1 = stageMapper.modelToEntity(Stage.builder().name("wait").order(0).build());
        StageEntity stage2 = stageMapper.modelToEntity(Stage.builder().name("exploration meeting").order(1).build());
        StageEntity stage3 = stageMapper.modelToEntity(Stage.builder().name("established goals").order(2).build());
        StageEntity stage4 = stageMapper.modelToEntity(Stage.builder().name("action plan presented").order(3).build());
        StageEntity stage5 = stageMapper.modelToEntity(Stage.builder().name("signed contract").order(4).build());
        StageEntity stage6 = stageMapper.modelToEntity(Stage.builder().name("sale won").order(5).build());
        StageEntity stage7 = stageMapper.modelToEntity(Stage.builder().name("sale lost").order(6).build());

        List<StageEntity> stages = Arrays.asList(stage1, stage2, stage3, stage4, stage5, stage6, stage7);

        stageRepository.saveAll(stages);

    }
}
