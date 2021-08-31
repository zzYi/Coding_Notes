public class WebUncompress{
    private MinioClient client;

    public class void uncompress(){
        String bucketName;
        String path;
        String fileName;

		try (InputStream stream = client.getObject(
				GetObjectArgs.builder()
						.bucket(bucketName)
						.object(path + fileName)
						.build()
		)) {
			ZipArchiveInputStream zis = new ZipArchiveInputStream(new BufferedInputStream(stream));
			ZipArchiveEntry entry = null;
			while ((entry = zis.getNextZipEntry()) != null) {
				client.putObject(PutObjectArgs.builder()
						.bucket("test")
						/**
						 * MIME 类型文件搜索顺序：
						 * MimetypesFileTypeMap 在用户系统的不同位置查找 MIME 类型文件条目。当发出在 MimetypesFileTypeMap 中搜索 MIME 类型的请求时，它将按以下顺序搜索 MIME 类型文件：
						 * 以编程方式添加到 MimetypesFileTypeMap 实例的条目。
						 * 用户主目录中的 .mime.types 文件。
						 * <java.home>/lib/mime.types 文件。
						 * 名为 META-INF/mime.types 的文件或资源。
						 * 名为 META-INF/mimetypes.default 的文件或资源（通常只存在于 activation.jar 文件中）。
						 * 
						 * MIME 类型文件格式：
						 * # 注释以 '#' 开头
						 * # 格式是 <mime 类型> <以空格分隔文件扩展名>
						 * # 例如：
						 * text/plain txt text TXT
						 * # 这将 file.txt、file.text 和 file.TXT 映射到
						 * # mime 类型 "text/plain"
						 */
						.contentType(MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(entry.getName()))
						.object("test/" + entry.getName())
						.stream(zis, entry.getSize(), -1)
						.build());
			}
		}
    }
}