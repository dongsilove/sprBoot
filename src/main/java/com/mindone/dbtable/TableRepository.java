package com.mindone.dbtable;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindone.okch.common.domain.Menu;



public interface TableRepository extends JpaRepository<Menu, String> {

	@Query(value="SELECT TABLE_NAME" + 
			"     , (SELECT COMMENTS FROM USER_TAB_COMMENTS WHERE TABLE_NAME = ATC.TABLE_NAME) T_COMMENT" + 
			"     , COLUMN_ID" + 
			"     , COLUMN_NAME" + 
			"     ,(SELECT COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME = ATC.TABLE_NAME AND COLUMN_NAME = ATC.COLUMN_NAME ) C_COMMENT" + 
			"     , DATA_TYPE ||'('|| DATA_LENGTH ||')' as DATATYPE" + 
			"     , NULLABLE" + 
			"     , DECODE((SELECT MIN(POSITION) KEEP(DENSE_RANK FIRST ORDER BY CONSTRAINT_NAME) FROM USER_CONS_COLUMNS" + 
			"             WHERE TABLE_NAME = ATC.TABLE_NAME AND COLUMN_NAME = ATC.COLUMN_NAME" + 
			"               AND POSITION IS NOT NULL " + 
			"       ), 1, 'PK', 2, 'FK', '') IS_PK" + 
			"     , DATA_DEFAULT" + 
			" FROM ALL_TAB_COLUMNS ATC" + 
			" WHERE ATC.TABLE_NAME NOT LIKE '%BAK'" + 
			"   AND ATC.TABLE_NAME NOT LIKE '%1'" + 
			"   AND ATC.TABLE_NAME NOT LIKE 'MDRT%'"  , nativeQuery = true)
	List<Map<String,Object>> tablelayout() throws Exception;
	
}
//ATC.OWNER = 'UEW2PRO_PTK'" + 
