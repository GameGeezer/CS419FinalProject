package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;

import static android.opengl.GLES30.*;

/**
 * Render buffer objects are buffers that can be attached toa Frame Buffer Object(FBO). They hold data
 * that can be drawn to the screen.
 *
 * @author William Gervasio
 */
public final class RBO {

    private final int[] handleBuffer = new int[1];
    private final AttachmentType frameBufferAttachmentType;

    public RBO(final int width, final int height, AttachmentType frameBufferAttachmentType) {

        this.frameBufferAttachmentType = frameBufferAttachmentType;

        glGenRenderbuffers(1, handleBuffer, 0);

        glBindRenderbuffer(GL_RENDERBUFFER, handleBuffer[0]);
        // Set the format and dimensions of the buffer
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT24, width, height);
        // Attach this render buffer to a currently bound frame buffer(FBO)
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, frameBufferAttachmentType.ID, GL_RENDERBUFFER, handleBuffer[0]);
        // Unbind this buffer
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

    public final void bind() {
        // Zero is reserved and unbinds the current render buffer
        glBindRenderbuffer(GL_RENDERBUFFER, handleBuffer[0]);
        // Attach this render buffer to a currently bound frame buffer(FBO)
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, frameBufferAttachmentType.ID, GL_RENDERBUFFER, handleBuffer[0]);
    }

    public final void unbind() {
        // Zero is reserved and unbinds the current render buffer
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

    public final void destroy() {

        glDeleteRenderbuffers(1, handleBuffer, 0);
    }
}
