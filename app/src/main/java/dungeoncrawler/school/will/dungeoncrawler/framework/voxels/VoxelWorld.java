package dungeoncrawler.school.will.dungeoncrawler.framework.voxels;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.Grid3D;

/**
 * @author William Gervasio
 */
public class VoxelWorld {

    private Grid3D<Chunk> grid;

    public final int with, height, depth;

    public VoxelWorld(final int width, final int height, final int depth) {

        this.with = width;
        this.height = height;
        this.depth = depth;

        grid = new Grid3D<>(width, height, depth);
    }

    /**
     * TODO
     * @param file
     */
    public void writeToFile(final File file) {

        try{
            OutputStream stream = new BufferedOutputStream(new FileOutputStream("d"));
           // stream.write();
        } catch(FileNotFoundException e) {

            e.printStackTrace();
        }
    }
}
