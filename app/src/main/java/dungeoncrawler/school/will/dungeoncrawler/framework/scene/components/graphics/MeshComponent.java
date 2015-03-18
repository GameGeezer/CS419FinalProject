package dungeoncrawler.school.will.dungeoncrawler.framework.scene.components.graphics;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.Mesh;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;

/**
 * @author William Gervasio
 */
public class MeshComponent extends RenderComponent {

    private Mesh mesh;

    public MeshComponent(final Mesh mesh, final ShaderProgram shader) {

        super(shader);

        this.mesh = mesh;
    }

    @Override
    public void render(int delta) {

        getShader().bind();
        mesh.draw();
        getShader().unbind();
    }
}
