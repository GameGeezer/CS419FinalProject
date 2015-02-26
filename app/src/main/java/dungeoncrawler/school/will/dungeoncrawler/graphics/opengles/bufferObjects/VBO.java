package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;

import java.nio.FloatBuffer;

import dungeoncrawler.school.will.dungeoncrawler.util.dataTypes.DatatypeUtil;

import static android.opengl.GLES30.*;

/**
 * A VBO is used to store data from a float buffer. This is most commonly used for vertex data: position, normal, color, ect.
 *
 * @author William Gervasio
 */
public final class VBO {

    private final int[] handleBuffer = new int[1];

    /**
     * @param buffer A buffer containing data that needs to be passed to the GPU
     * @param usage  An opengl hint describing how the data may be used
     */
    public VBO(final FloatBuffer buffer, final BufferedObjectUsage usage) {

        glGenBuffers(1, handleBuffer, 0);

        bind();

        final int bufferSizeInBytes = buffer.limit() * DatatypeUtil.FLOAT_SIZE_BYTES;

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, bufferSizeInBytes, buffer, usage.ID);

        unbind();
    }

    /**
     * Binds the buffer
     */
    public final void bind() {

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handleBuffer[0]);
    }

    /**
     * Unbinds the buffer
     */
    public final void unbind() {

        //zero is reserved and acts as "no object"
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
     * Releases the memory allocated by this buffer
     */
    public final void destroy() {

        glDeleteBuffers(1, handleBuffer, 0);
    }
}