import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author zzy
 * @date 2021/8/6 11:30
 */
@Service
public class DataSource {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	@SneakyThrows
	public void demo(){
        // 获取table表所有列信息
		ResultSet rsColimns = dataSource.getConnection().getMetaData().getColumns(null,"%","table","%");
		while (rsColimns.next()){
            /** 
             * TABLE_CAT String => table catalog (may be null)
             * TABLE_SCHEM String => table schema (may be null)
             * TABLE_NAME String => table name
             * COLUMN_NAME String => column name
             * DATA_TYPE int => SQL type from java.sql.Types
             * TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
             * COLUMN_SIZE int => column size.
             *      BUFFER_LENGTH is not used.
             * DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
             * NUM_PREC_RADIX int => Radix (typically either 10 or 2)
             * NULLABLE int => is NULL allowed.
             *      columnNoNulls - might not allow NULL values
             *      columnNullable - definitely allows NULL values
             *      columnNullableUnknown - nullability unknown
             * REMARKS String => comment describing column (may be null)
             * COLUMN_DEF String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
             * SQL_DATA_TYPE int => unused
             * SQL_DATETIME_SUB int => unused
             * CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
             * ORDINAL_POSITION int => index of column in table (starting at 1)
             * IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
             *      YES --- if the column can include NULLs
             *      NO --- if the column cannot include NULLs
             *      empty string --- if the nullability for the column is unknown
             * SCOPE_CATALOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
             * SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
             * SCOPE_TABLE String => table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
             * SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
             * IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
             *      YES --- if the column is auto incremented
             *      NO --- if the column is not auto incremented
             *      empty string --- if it cannot be determined whether the column is auto incremented
             * IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
             *      YES --- if this a generated column
             *      NO --- if this not a generated column
             *      empty string --- if it cannot be determined whether this is a generated column
             */
			String columnName = rsColimns.getString("COLUMN_NAME");
			String typeName = rsColimns.getString("TYPE_NAME");
			String columnSize = rsColimns.getString("COLUMN_SIZE");
			String remarks = rsColimns.getString("REMARKS");
		}
	}
}