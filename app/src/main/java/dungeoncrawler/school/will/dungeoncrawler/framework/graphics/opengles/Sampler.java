package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.opengl.GLES30.*;

/**
 *@author William Gervasio
 */
public class Sampler {

    private final int[] handleBuffer = new int[1];

    private final int[] textureUnits;
    private final Map<Integer, Integer> parameters;

    public Sampler(Map<Integer, Integer> parameters, int... textureUnits) {

        this.textureUnits = textureUnits;
        this.parameters = parameters;

        glGenSamplers(1, handleBuffer, 0);

        for (final int key : parameters.keySet())
            glSamplerParameteri(handleBuffer[0], key, parameters.get(key));
    }

    public void bind() {

        for (final int unit : textureUnits) {

            glBindSampler(unit, handleBuffer[0]);
        }
    }

    public void destroy() {

        glDeleteSamplers(1, handleBuffer, 0);
    }
}
