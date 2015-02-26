package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles;

import java.nio.ByteBuffer;

import dungeoncrawler.school.will.dungeoncrawler.graphics.Image;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.AttachmentType;

import static android.opengl.GLES30.*;
/**
 * @author William Gervasio
 */
public class Texture {

    private final int[] handleBuffer = new int[1];
    private final int textureUnit, width, height;
    private final OGLColorType colorType;

    public Texture(final int width, final int height, final int textureUnit, final OGLColorType colorType, final ByteBuffer buffer) {

        glGenTextures(1, handleBuffer, 0);
        this.textureUnit = textureUnit;
        this.width = width;
        this.height = height;
        this.colorType = colorType;

        glActiveTexture(textureUnit);
        glBindTexture(GL_TEXTURE_2D, handleBuffer[0]);

        // All RGB bytes are aligned to each other and each component is 1 byte
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        // Upload the texture data and generate mip maps (for scaling)
        glTexImage2D(GL_TEXTURE_2D, 0, colorType.ID, width, height, 0, colorType.ID, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public Texture(final int width, final int height, final int textureUnit, final OGLColorType colorType) {

        this(width, height, textureUnit, colorType, null);
    }

    public Texture(final Image image, final int textureUnit) {

        this(image.getWidth(), image.getHeight(), textureUnit, OGLColorType.RGBA, image.getBuffer());
    }

    public final void attachToFBO(final AttachmentType attachment) {
        // Add this texture as a buffer to the Frame Buffer Objects
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.ID, GL_TEXTURE_2D, handleBuffer[0], 0);
    }

    public void bind() {

        glActiveTexture(textureUnit);

        glBindTexture(GL_TEXTURE_2D, handleBuffer[0]);
    }

    public void unbind()
    {

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void destroy() {

        glDeleteTextures(1, handleBuffer, 0);
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }

    public int getUnit() {

        return textureUnit;
    }

    public OGLColorType getColorType() {

        return colorType;
    }
}
