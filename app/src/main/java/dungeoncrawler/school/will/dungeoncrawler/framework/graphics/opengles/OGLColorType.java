package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

import android.opengl.GLES30;

/**
 * @author William Gervasio
 */
public enum OGLColorType {

    RGB(GLES30.GL_RGB),
    RGB8(GLES30.GL_RGB8),

    RGBA(GLES30.GL_RGBA),
    RGBA4(GLES30.GL_RGBA4),
    RGBA8(GLES30.GL_RGBA8),
    RGBA16F(GLES30.GL_RGBA16F);

    public final int ID;

    private OGLColorType(final int id) {

        ID = id;
    }
}
