package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

import static android.opengl.GLES30.*;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.bufferObjects.AttachmentType;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.bufferObjects.FBO;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.bufferObjects.RBO;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.BufferUtil;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.exceptions.GraphicsException;


import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO STILL A WIP
 * Created by Will on 1/23/2015.
 */
public class DeferredRenderer {

    private static final AttachmentType POSITION_BUFFER_BINDING = AttachmentType.COLOR_ATTACHMENT0;
    private static final AttachmentType NORMAL_BUFFER_BINDING = AttachmentType.COLOR_ATTACHMENT1;
    private static final AttachmentType DIFFUSE_BUFFER_BINDING = AttachmentType.COLOR_ATTACHMENT2;
    private static final int SCREEN_BUFFER_BINDING = 3;

    private final Texture positionBuffer;
    private final Texture normalBuffer;
    private final Texture diffuseBuffer;

    private final FBO fbo;
    private final RBO fboDepthBuffer;

    public DeferredRenderer(final int width, final int height) throws GraphicsException {

        glEnable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        positionBuffer = new Texture(width, height, POSITION_BUFFER_BINDING.ID, OGLColorType.RGBA);
        normalBuffer = new Texture(width, height, NORMAL_BUFFER_BINDING.ID, OGLColorType.RGBA);
        diffuseBuffer = new Texture(width, height, DIFFUSE_BUFFER_BINDING.ID, OGLColorType.RGBA);

        fbo = new FBO();
        fboDepthBuffer = new RBO(width, height, AttachmentType.DEPTH_ATTACHMENT);

        fbo.bind();
        fboDepthBuffer.bind();

        positionBuffer.attachToFBO(POSITION_BUFFER_BINDING);
        normalBuffer.attachToFBO(NORMAL_BUFFER_BINDING);
        diffuseBuffer.attachToFBO(DIFFUSE_BUFFER_BINDING);

        final IntBuffer buffer = BufferUtil.createIntBuffer(3);
        buffer.put(0, POSITION_BUFFER_BINDING.ID);
        buffer.put(1, NORMAL_BUFFER_BINDING.ID);
        buffer.put(2, DIFFUSE_BUFFER_BINDING.ID);

        buffer.rewind();
        // Creates an array of buffers that the fragment shader will output to
        glDrawBuffers(3, buffer);

        if (!fbo.checkForErrors()) {

            throw new GraphicsException("Could not compile FBO");
        }

        fboDepthBuffer.unbind();

        fbo.unbind();
    }

    public void beginDrawing() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        fbo.bind();

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    }

    public void endDrawing() {

        fbo.unbind();

        diffuseBuffer.bind();


        diffuseBuffer.unbind();
    }

    public void destroy() {

        positionBuffer.destroy();
        normalBuffer.destroy();
        diffuseBuffer.destroy();
    }

    private Sampler createSampler() {

        Map<Integer, Integer> samplerParameters = new HashMap<>();
        samplerParameters.put(GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        samplerParameters.put(GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        samplerParameters.put(GL_TEXTURE_WRAP_S, GL_REPEAT);
        samplerParameters.put(GL_TEXTURE_WRAP_T, GL_REPEAT);

        return new Sampler(samplerParameters, DIFFUSE_BUFFER_BINDING.ID);
    }
}
