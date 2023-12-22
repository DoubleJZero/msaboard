package msaboard.api.repository;

import msaboard.data.entity.TbBoardInfo;
import msacore.annotation.BatchQuery;
import msacore.constant.BATCH_QUERY_MODE;
import msacore.dto.BatchDto;
import msacore.repository.BatchRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BatchSqlRepositiry extends BatchRepository {

    public BatchSqlRepositiry(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    @BatchQuery(entity = TbBoardInfo.class, mode = BATCH_QUERY_MODE.UPDATE)
    @Override
    public int[] batchUpdate(BatchDto<?> dto){
        return super.batchUpdate(dto);
    }
}
