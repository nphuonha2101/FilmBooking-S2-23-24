package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.LogModel;
import com.filmbooking.repository.mapper.LogMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
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
        Map<String, Object> result = new HashMap<>();
        result.put("username", logModel.getUsername());
        result.put("req_ip", logModel.getReqIP());
        result.put("ip_country", logModel.getIpCountry());
        result.put("log_level", logModel.getLevel());
        result.put("target_table", logModel.getTargetTable());
        result.put("actions", logModel.getAction());
        result.put("is_action_success", logModel.isActionSuccess());
        result.put("before_data", logModel.getBeforeValueJSON());
        result.put("after_data", logModel.getAfterValueJSON());
        result.put("created_at", logModel.getCreatedAt());
        result.put("updated_at", logModel.getUpdatedAt());

        return result;
    }

    public boolean updateLogLevel(LogModel logModel) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "UPDATE logs SET log_level = :log_level WHERE log_id = :log_id";
            handle.createUpdate(sql)
                    .bind("log_id", logModel.getLogID())
                    .bind("log_level", logModel.getLevel())
                    .execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}
