import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.image.BufferedImage;

/**
 * @author zzy
 * @date 2022/9/27 15:47
 */
public class BufferedImageTranscoder extends ImageTranscoder {

    private final int type;
    private final int ratio;
    private BufferedImage image;

    public BufferedImageTranscoder(int type) {
        this.type = type;
        ratio = 1;
    }

    public BufferedImageTranscoder(int type, int ratio) {
        this.type = type;
        this.ratio = ratio;
    }

    @Override
    public BufferedImage createImage(int width, int height) {
        return new BufferedImage(width, height, type);
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput output) {
        this.image = img;
    }

    @Override
    protected void setImageSize(float docWidth, float docHeight) {
        super.setImageSize(docWidth * ratio, docHeight * ratio);
    }

    @Override
    protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
        Element root = document.getDocumentElement();
        setDefaultForStop(root);
        super.transcode(document, uri, output);
    }

    private void setDefaultForStop(Element element) {
        if (element.hasChildNodes()) {
            for (Node n = element.getFirstChild(); n != null; n = n.getNextSibling()) {
                if (n instanceof Element) {
                    setDefaultForStop((Element) n);
                }
            }
        }

        if ("stop".equals(element.getNodeName()) &&
                element.getParentNode() != null &&
                "linearGradient".equals(element.getParentNode().getNodeName()) &&
                element.getAttributeNodeNS(null, "offset") == null) {
            element.setAttribute("offset", "0");
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
