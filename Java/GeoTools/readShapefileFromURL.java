/**
 * @author zzy
 * @date 2021/8/20 15:09
 */
public class readShapefileFromURL{

    public ContentFeatureCollection readShapefileFromURL() {
        /**
         * 通过http获取shape文件，初始化主文件.shp文件
         * 同一个目录下需要包含shape的其他文件，如：
         * http://xxx.xxx.com/shape/shape_name.shx
         * http://xxx.xxx.com/shape/shape_name.dbf
         * http://xxx.xxx.com/shape/shape_name.prj
         */
        ShapefileDataStore shapefileDataStore = new ShapefileDataStore(new URL("http://xxx.xxx.com/shape/shape_name.shp"));
        // 设置编码
		shapefileDataStore.setCharset(Charset.forName("GBK"));
		ContentFeatureSource featureSource = shapefileDataStore.getFeatureSource();
		ContentFeatureCollection collection = featureSource.getFeatures();
    }

    public void readShapefileItems(ContentFeatureCollection collection) {
        // 读取数据
        SimpleFeatureIterator iterator = collection.features();
        try {
			while (iterator.hasNext()) {
				Feature feature = iterator.next();
				Collection<Property> properties = feature.getProperties();
				for (Property property : properties) {
					System.out.printf(property.getName().toString());
					System.out.printf(property.getValue().toString());
				}
			}
		} finally {
			iterator.close();
		}
    }
}