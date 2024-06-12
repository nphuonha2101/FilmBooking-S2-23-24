package com.filmbooking.repository;

import com.filmbooking.model.LogModel;
import com.filmbooking.repository.mapper.LogMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class LogRepository extends AbstractRepository<LogModel>{
    public LogRepository() {
        super(LogModel.class);
    }

    @Override
    RowMapper<LogModel> getRowMapper() {
        return new LogMapper();
    }

    @Override
    public Map<String, Object> mapToRow(LogModel logModel) {
        return Map.ofEntries(
                Map.entry("username", logModel.getUsername()),
                Map.entry("req_ip", logModel.getReqIP()),
                Map.entry("ip_country", logModel.getIpCountry()),
                Map.entry("log_level", logModel.getLevel()),
                Map.entry("target_table", logModel.getTargetTable()),
                Map.entry("action", logModel.getAction()),
                Map.entry("is_action_success", logModel.isActionSuccess()),
                Map.entry("before_data", logModel.getBeforeValueJSON()),
                Map.entry("after_data", logModel.getAfterValueJSON()),
                Map.entry("created_at", logModel.getCreatedAt()),
                Map.entry("updated_at", logModel.getUpdatedAt())
        );
    }
}
