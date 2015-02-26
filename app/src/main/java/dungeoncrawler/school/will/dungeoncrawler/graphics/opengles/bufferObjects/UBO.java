package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;

import static android.opengl.GLES30.*;

/**
 * TODO
 * @author William Gervasio
 */
public class UBO {

    private final int[] handleBuffer = new int[1];

    public UBO() {

        glGenBuffers(1, handleBuffer, 0);
    }

    public void bind() {

        glBindBuffer(GL_UNIFORM_BUFFER, handleBuffer[0]);
    }

    public void unbind() {

        glBindBuffer(GL_UNIFORM_BUFFER, 0);
    }

    public void destroy() {
        glDeleteBuffers(1, handleBuffer, 0);

    }
}
