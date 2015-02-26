package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;

import static android.opengl.GLES30.*;

/**
 * Hints for OpenGL
 *
 * @author william gervasio
 */
public enum BufferedObjectUsage {

    STATIC_DRAW(GL_STATIC_DRAW),
    STATIC_READ(GL_STATIC_READ),
    STATIC_COPY(GL_STREAM_DRAW),
    STREAM_DRAW(GL_STREAM_DRAW),
    STREAM_READ(GL_STREAM_READ),
    STREAM_COPY(GL_STREAM_COPY),
    DYNAMIC_DRAW(GL_DYNAMIC_DRAW),
    DYNAMIC_READ(GL_DYNAMIC_READ),
    DYNAMIC_COPY(GL_DYNAMIC_COPY);

    public final int ID;

    private BufferedObjectUsage(final int id) {

        ID = id;
    }
}