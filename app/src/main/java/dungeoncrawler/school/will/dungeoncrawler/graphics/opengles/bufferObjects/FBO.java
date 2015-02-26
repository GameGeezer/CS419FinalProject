package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects;


import static android.opengl.GLES30.*;

/**
 * @author William Gervasio
 */
public final class FBO {

    private final int[] handleBuffer = new int[1];

    public FBO() {

        glGenFramebuffers(1, handleBuffer, 0);
    }

    public final void bind() {

        glBindFramebuffer(GL_FRAMEBUFFER, handleBuffer[0]);
    }

    public final void unbind() {

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }


    public final void destroy() {

        glDeleteFramebuffers(1, handleBuffer, 0);
    }

    public final boolean checkForErrors() {

        return glCheckFramebufferStatus(GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE;
    }
}
