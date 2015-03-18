package dungeoncrawler.school.will.dungeoncrawler.game;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * @author William Gervasio
 */
public class GameSurfaceView extends GLSurfaceView {

    private final OGLES30Renderer mRenderer;

    public GameSurfaceView(final Context context){
        super(context);

        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3);

        mRenderer = new OGLES30Renderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}
