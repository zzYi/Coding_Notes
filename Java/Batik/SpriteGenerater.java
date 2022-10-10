import com.alibaba.fastjson.JSON;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @date 2022/9/26 10:20
 */
public class SpriteGenerater {

    private static final int RATIO = 4;

    public static void main(String[] args) throws IOException, TranscoderException {
        BufferedImageTranscoder transcoder = new BufferedImageTranscoder(BufferedImage.TYPE_INT_ARGB, RATIO);
        File file = new File("D:\\svg");
        File[] files = file.listFiles();
        BufferedImage image;
        TranscoderInput input;
        List<Sprite> sprites = new LinkedList<>();
        // 批量转换
        for (File f : files) {
            if (!f.getName().endsWith(".svg")) {
                continue;
            }
            input = new TranscoderInput(f.toURI().toString());
            try {
                transcoder.transcode(input, null);
            } catch (Exception e) {
                continue;
            }
            image = transcoder.getImage();
            Sprite sprite = new Sprite(RATIO, image);
            sprite.setName(f.getName().split("\\.")[0]);
            sprites.add(sprite);
        }

        // 按照高度排序
        sprites.sort((s1, s2) -> {
            if (s2.getHeight() > s1.getHeight()) {
                return 1;
            } else if (s2.getHeight() == s1.getHeight() && s2.getWidth() > s1.getWidth()) {
                return 1;
            }
            return -1;
        });

        // 打包布局
        SpritePacker packer = new SpritePacker();
        packer.pack(sprites);

        // 拼接图片
        BufferedImage dest = new BufferedImage(packer.getW(), packer.getH(), BufferedImage.TYPE_INT_ARGB);
        Map<String, Sprite> spriteMap = new HashMap<>(sprites.size());
        for (Sprite s : sprites) {
            int[] rgbArray = new int[s.getWidth() * s.getHeight()];
            s.getImage().getRGB(0, 0, s.getWidth(), s.getHeight(), rgbArray, 0, s.getWidth());
            dest.setRGB(s.getX(), s.getY(), s.getWidth(), s.getHeight(), rgbArray, 0, s.getWidth());
            spriteMap.put(s.getName(), s);
        }
        File destf = new File("sprite.png");
        ImageIO.write(dest, "png", destf);
        JSON.toJSON(spriteMap);
    }

}
