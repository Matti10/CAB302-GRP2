package maze;

public class imageLocation {
    String path;
    String name;
    Coordinate topLeftLocation; //top left cell of image
    Coordinate bottomRightLoction;

    int size;

    imageLocation(String path, String name, Coordinate topLeftLocation,Coordinate bottomRightLoction, int size)
    {
        this.path = path;
        this.name = name;
        this.topLeftLocation = topLeftLocation;
        this.bottomRightLoction = bottomRightLoction;
        this.size = size;
    }

    imageLocation getImageLocation()
    {
        return this;
    }
}
