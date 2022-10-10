import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author zzy
 * @date 2022/9/27 10:57
 */
@Data
public class Sprite {
    @JSONField(serialize = false)
    BufferedImage image;
    @JSONField(serialize = false)
    private String name;
    private int pixelRatio = 1;
    private int width;
    private int height;
    private int x;
    private int y;

    public Sprite(int pixelRatio, BufferedImage image) {
        this.pixelRatio = pixelRatio;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }
}
