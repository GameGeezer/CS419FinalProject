package dungeoncrawler.school.will.dungeoncrawler.game;

import static android.opengl.GLES30.*;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.DeferredRenderer;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.exceptions.GraphicsException;

/**
 * @author William Gervasio
 */
public class OGLES30Renderer implements GLSurfaceView.Renderer{

    private DeferredRenderer renderer;

    @Override
    public void onSurfaceCreated(final GL10 gl, final EGLConfig config) {

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(final GL10 gl, final int width, final int height) {

        glViewport(0, 0, width, height);

        try {
            renderer = new DeferredRenderer(width, height);

        } catch (GraphicsException e) {

            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void onDrawFrame(final GL10 gl) {

        renderer.beginDrawing();

        renderer.endDrawing();
    }
}
