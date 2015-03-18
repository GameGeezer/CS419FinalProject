package dungeoncrawler.school.will.dungeoncrawler.framework.scene.components.graphics;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.Image;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.Mesh;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.Texture;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.FloatVectorUniform;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.VectorUniform;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices.StaticVertexAttribute;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices.VertexAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gervasio
 */
public class SpriteComponent extends RenderComponent {

    private final Texture texture;
    private final Mesh mesh;
    public final int cellsWide, cellsHigh;
    public final float texcoordX, texcoordY;

    private final FloatVectorUniform texCoordOffset = new FloatVectorUniform("u_textureCoordinateOffset", VectorUniform.VectorUniformType.VECTOR2);

    public SpriteComponent(final Image image, final ShaderProgram shader, final int cellsWide, final int cellsHigh) {

        super(shader);

        texcoordX = (1.0f / (float) cellsWide);
        texcoordY = (1.0f / (float) cellsHigh);
        this.cellsWide = cellsWide;
        this.cellsHigh = cellsHigh;

        texture = new Texture(image, 0);

        mesh = createMesh(texture.width, texture.height);

        texCoordOffset.addListener(shader);

        setFrame(0, 0);
    }

    public void setFrame(int frameX, int frameY) {

        float fx = texcoordX * (float) frameX;
        float fy = texcoordY * (float) frameY;

        texCoordOffset.setUniformData(fx, fy);
    }

    @Override
    public void render(int delta) {

        getShader().bind();
        texture.bind();
        mesh.draw();
        texture.unbind();
        getShader().unbind();
    }

    private Mesh createMesh(int width, int height) {

        float widthHeightRatio = (float) width / (float) height;
        float xRatio = widthHeightRatio / 2f;
        float yRatio = 1 - xRatio;

        float[] vertices = {
                -xRatio, yRatio, 0f,
                -xRatio, -yRatio, 0f,
                xRatio, -yRatio, 0f,
                xRatio, yRatio, 0f,
        };

        float[] normals = {
                0f, 0f, 1f,
                0f, 0f, 1f,
                0f, 0f, 1f,
                0f, 0f, 1f,
        };

        float[] texCoords = {
                0f, 0f,
                0f, texcoordY,
                texcoordX, texcoordY,
                texcoordX, 0f,
        };

        int[] indices = {0, 1, 2, 0, 2, 3,};

        List<VertexAttribute> attributes = new ArrayList<>();
        attributes.add(new StaticVertexAttribute(3, vertices));
        attributes.add(new StaticVertexAttribute(3, normals));
        attributes.add(new StaticVertexAttribute(2, texCoords));

        return new Mesh(indices, attributes);
    }
}
