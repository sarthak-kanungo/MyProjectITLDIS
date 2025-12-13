package com.i4o.dms.itldis.service.pdi.dto;

public interface PdiCheckpointListByModel {

    Long getAggregateId();

    Long getCheckpointId();

    String getAggregate();

    String getPdiCheckpoint();

    String getAggregateSequenceNumber();

    String getCheckpointSequenceNumber();


}
