package dungeoncrawler.school.will.dungeoncrawler.game;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * @author William Gervasio
 */
public class GameActivity extends Activity {

    private GLSurfaceView renderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        renderView = new GameSurfaceView(this);

        setContentView(renderView);
    }
}
