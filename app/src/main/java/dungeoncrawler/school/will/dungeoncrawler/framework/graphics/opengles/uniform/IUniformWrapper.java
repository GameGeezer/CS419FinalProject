package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;

/**
 * @author William Gervasio
 */
public interface IUniformWrapper {

    public abstract void addListener(ShaderProgram shader);

    public abstract void removeListener(ShaderProgram shader);
}
