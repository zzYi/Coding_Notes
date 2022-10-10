/**
 * @author zzy
 * @date 2021/7/30 16:09
 */
public class PostGis {

	public static void main(String[] args) throws Exception {

		// 创建postgis数据库连接
		Map<String, Object> params = new HashMap<>();
		params.put("dbtype", "postgis");
		params.put("host", "127.0.0.1");
		params.put("port", 5432);
		params.put("schema", "public");
		params.put("database", "postgres");
		params.put("user", "postgres");
		params.put("passwd", "PASSWORD");
		DataStore dataStore = DataStoreFinder.getDataStore(params);

		// 获取数据库表结构
		SimpleFeatureSource simpleFeature = dataStore.getFeatureSource("table");
		// 创建数据库表结构
		SimpleFeatureType schema = simpleFeature.getSchema();
		SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
		typeBuilder.init(schema);
		typeBuilder.setName("new_table");
		dataStore.createSchema(typeBuilder.buildFeatureType());

		// 获取数据集
		SimpleFeatureCollection features = simpleFeature.getFeatures();

		// 获取数据库表
		JDBCFeatureStore jdbcFeatureStore = (JDBCFeatureStore) dataStore.getFeatureSource("new_table");
		// 添加数据，构建默认事务并在错误时回滚
		Transaction t = new DefaultTransaction();
		SimpleFeatureCollection features = null;
		try {
			features = simpleFeature.getFeatures();
			jdbcFeatureStore.addFeatures(features);
			t.commit();
		} catch (IOException eek) {
			eek.printStackTrace();
			try {
				t.rollback();
			} catch (IOException doubleEeek) {
				// rollback failed?
			}
		} finally {
			t.close();
		}

		// 遍历数据
		SimpleFeatureIterator feature = features.features();
		List<Object> list = new ArrayList<>();
		while (feature.hasNext()) {
			SimpleFeature fea = feature.next();
			list.add(fea.getAttribute("name"));
		}
	}
}