package dungeoncrawler.school.will.dungeoncrawler.framework.scene.components.graphics;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.IUniformWrapper;
import dungeoncrawler.school.will.dungeoncrawler.framework.scene.Entity;

import java.util.List;

/**
 * A RenderComponent is a EntityComponent that forces a render(int delta) to be implemented.
 *
 * @author William Gervasio
 */
public abstract class RenderComponent extends Entity.EntityComponent {

    private final ShaderProgram shader;

    public RenderComponent(final ShaderProgram shader) {

        this.shader = shader;
    }

    public abstract void render(final int delta);

    @Override
    protected void onAttach() {

        final List<IUniformWrapper> uniformComponents = (List<IUniformWrapper>) (List<?>) getParent().getComponentsOfType(IUniformWrapper.class);

        for(IUniformWrapper uniformComponent : uniformComponents) {

            uniformComponent.addListener(getShader());
        }
    }

    @Override
    protected void onDetach() {

        final List<IUniformWrapper> uniformComponents = (List<IUniformWrapper>) (List<?>) getParent().getComponentsOfType(IUniformWrapper.class);

        for(IUniformWrapper uniformComponent : uniformComponents) {

            uniformComponent.removeListener(getShader());
        }
    }

    @Override
    protected void onComponentAttachedToParent(final Entity.EntityComponent component) {

        if (component instanceof IUniformWrapper) {

            ((IUniformWrapper) component).addListener(getShader());
        }
    }

    @Override
    protected void onComponentDetachedFromParent(final Entity.EntityComponent component) {

        if (component instanceof IUniformWrapper) {

            ((IUniformWrapper) component).removeListener(getShader());
        }
    }

    public ShaderProgram getShader() {

        return shader;
    }
}
