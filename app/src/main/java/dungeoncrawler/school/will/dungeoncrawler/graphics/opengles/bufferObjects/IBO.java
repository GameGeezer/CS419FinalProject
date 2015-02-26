package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;

import static android.opengl.GLES30.*;

import java.nio.IntBuffer;

import dungeoncrawler.school.will.dungeoncrawler.util.dataTypes.DatatypeUtil;

/**
 * A IBO is used to store data from a int buffer. This is most commonly used to store indices.
 *
 * @author William Gervasio
 */
public final class IBO {

    private final int[] handleBuffer = new int[1];

    /**
     * @param buffer A buffer containing data that needs to be passed to the GPU
     * @param usage  An opengl hint describing how the data may be used
     */
    public IBO(final IntBuffer buffer, final BufferedObjectUsage usage) {

        glGenBuffers(1, handleBuffer, 0);

        bind();

        final int bufferSizeInBytes = buffer.limit() * DatatypeUtil.INTEGER_SIZE_BYTES;

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, bufferSizeInBytes, buffer, usage.ID);

        unbind();
    }

    /**
     * Bind the buffer
     */
    public final void bind() {

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handleBuffer[0]);
    }

    /**
     * Unbind the buffer
     */
    public final void unbind() {

        //zero is reserved and acts as "no object"
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Releases the memory allocated by this buffer
     */
    public final void destroy() {

        glDeleteBuffers(1, handleBuffer, 0);
    }
}