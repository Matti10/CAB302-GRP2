package maze;

public class imageLocation {
    String path; //file path to image
    String name; //name of img
    Coordinate topLeftLocation; //top left cell of image
    Coordinate bottomRightLocation; //bottom right cell of image

    boolean isAccesible; //is the img accesseble in maze

    int size; //size (redundant..)

    /**
     * Create an image object
     * @param path - File path of image
     * @param name - Name of image
     * @param topLeftLocation - top left coord of img
     * @param bottomRightLocation - bottom right coord of img
     * @param size - size of img
     * @param isAccesible - can maze explore into the image?
     */
    imageLocation(String path, String name, Coordinate topLeftLocation,Coordinate bottomRightLocation, int size, boolean isAccesible)
    {
        this.path = path;
        this.name = name;
        this.topLeftLocation = topLeftLocation;
        this.bottomRightLocation = bottomRightLocation;
        this.size = size;
        this.isAccesible = isAccesible;
    }

    /**
     *
     * @return This current
     */
    imageLocation getImage()
    {
        return this;
    }

    /**
     *
     * @return Array containing image coords
     */
    Coordinate[] getImageLocation()
    {
        Coordinate[] arr = new Coordinate[2];
        arr[0] = topLeftLocation;
        arr[1] = bottomRightLocation;
        return arr;
    }
}
