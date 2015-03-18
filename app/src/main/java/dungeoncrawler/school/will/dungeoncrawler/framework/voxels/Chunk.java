package dungeoncrawler.school.will.dungeoncrawler.framework.voxels;

import java.nio.ByteBuffer;

/**
 * @author William Gervasio
 */
public class Chunk {

    public final byte[] chunkData;

    public Chunk() {

        chunkData = new byte[4096];
    }

    public Chunk(final ByteBuffer buffer) {

        chunkData = buffer.array();
    }
}
